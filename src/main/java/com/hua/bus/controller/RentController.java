package com.hua.bus.controller;


import com.hua.bus.entity.Car;
import com.hua.bus.entity.Customer;
import com.hua.bus.service.CarService;
import com.hua.bus.service.CustomerService;
import com.hua.bus.service.RentService;
import com.hua.bus.vo.CustomerVo;
import com.hua.bus.vo.RentVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.utils.RandomUtils;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    /**
     * 根据身份证号检查是否含有客户
     * @param rentVo
     * @return
     */
    @RequestMapping("/checkCustomerExist")
    public ResultObj checkCustomerExist(RentVo rentVo){
        Customer customer = customerService.getById(rentVo.getIdentity());
        if (customer != null){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单数据
     */
    @RequestMapping("/initRentFrom")
    public RentVo initRentFrom(RentVo rentVo){
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
        rentVo.setBegindate(new Date());
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());

        return rentVo;
    }

    /**
     * 保存出租单信息
     */
    @RequestMapping("/saveRent")
    public ResultObj saveRent(RentVo rentVo){
        try {
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
            rentService.save(rentVo);

            //更改车辆的出租状态
            Car car = new Car();
            car.setCarnumber(rentVo.getCarnumber());
            car.setIsrenting(SysConstast.RENT_CAR_TRUE);
            carService.updateById(car);

            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}

