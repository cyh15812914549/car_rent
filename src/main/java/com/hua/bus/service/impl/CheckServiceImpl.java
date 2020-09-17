package com.hua.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hua.bus.entity.Car;
import com.hua.bus.entity.Check;
import com.hua.bus.entity.Customer;
import com.hua.bus.entity.Rent;
import com.hua.bus.mapper.CarMapper;
import com.hua.bus.mapper.CheckMapper;
import com.hua.bus.mapper.CustomerMapper;
import com.hua.bus.mapper.RentMapper;
import com.hua.bus.service.CheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.utils.RandomUtils;
import com.hua.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-09-17
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements CheckService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;


    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = rentMapper.selectById(rentid);
        //查询客户
        Customer customer = customerMapper.selectById(rent.getIdentity());
        //查询车辆
        Car car = carMapper.selectById(rent.getCarnumber());

        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());

        Map<String,Object> map = new HashMap<>();

        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);
        return map;
    }
}
