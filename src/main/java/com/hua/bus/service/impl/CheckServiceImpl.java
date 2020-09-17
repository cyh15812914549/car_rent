package com.hua.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.hua.bus.vo.CheckVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.RandomUtils;
import com.hua.sys.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private CheckMapper checkMapper;


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

    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        IPage<Check> page = new Page<Check>(checkVo.getPage(),checkVo.getLimit());
        //构造模糊查询
        QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
        //降序
        queryWrapper.orderByDesc("createtime");
        if (StringUtils.isNotEmpty(checkVo.getCheckid()) || StringUtils.isNotEmpty(checkVo.getRentid())
                || StringUtils.isNotEmpty(checkVo.getCheckdesc()) || StringUtils.isNotEmpty(checkVo.getProblem())){
            queryWrapper.and(i -> i.like("checkid",checkVo.getCheckid())
                    .like("rentid",checkVo.getRentid())
                    .like("checkdesc",checkVo.getCheckdesc())
                    .like("problem",checkVo.getProblem()));
        }
        //抽取出来是为了防止%null%出现
        if (checkVo.getStartTime() != null){
            queryWrapper.and(i->i.ge("createtime",checkVo.getStartTime()));
        }
        if (checkVo.getEndTime() != null){
            queryWrapper.and(i->i.le("createtime",checkVo.getEndTime()));
        }
        page = checkMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}
