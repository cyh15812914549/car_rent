package com.hua.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cyh
 * @date 2020/9/3 20:35
 */
@Controller
@RequestMapping("/bus")
public class BusController {

    /**
     * 跳转到客户管理的页面
     */
    @RequestMapping("/toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到车辆管理的页面
     */
    @RequestMapping("/toCarManager")
    public String toCarManager(){
        return "business/car/carManager";
    }

    /**
     * 跳转到车辆管理的页面
     */
    @RequestMapping("/toRentCarManager")
    public String toRentCarManager(){
        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租单管理的页面
     */
    @RequestMapping("/toRentManager")
    public String toRentManager(){
        return "business/rent/rentManager";
    }
}
