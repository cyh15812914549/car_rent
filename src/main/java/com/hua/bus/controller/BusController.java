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
}
