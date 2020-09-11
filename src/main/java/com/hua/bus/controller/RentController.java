package com.hua.bus.controller;


import com.hua.bus.entity.Customer;
import com.hua.bus.service.CustomerService;
import com.hua.bus.service.RentService;
import com.hua.bus.vo.CustomerVo;
import com.hua.bus.vo.RentVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    private CustomerService customerService;

    @RequestMapping("/checkCustomerExist")
    public ResultObj checkCustomerExist(RentVo rentVo){
        Customer customer = customerService.getById(rentVo.getIdentity());
        if (customer != null){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }
}

