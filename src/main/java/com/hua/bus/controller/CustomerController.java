package com.hua.bus.controller;


import com.hua.bus.service.CustomerService;
import com.hua.bus.vo.CustomerVo;
import com.hua.sys.utils.DataGridView;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 加载客户列表返回DataGridView
     */
    @RequestMapping("/loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        return customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加客户
     */
    @RequestMapping("/addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo) {
        try {
            customerVo.setCreatetime(new Date());
            customerService.save(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改客户
     */
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo) {
        try {
            customerService.updateById(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除客户
     */
    @RequestMapping("/deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo) {
        try {
            customerService.deleteCustomer(customerVo.getIdentity());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     */
    @RequestMapping("/deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo) {
        try {
            customerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

