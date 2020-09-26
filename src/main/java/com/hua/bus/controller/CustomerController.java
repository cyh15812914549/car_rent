package com.hua.bus.controller;


import com.hua.bus.entity.Customer;
import com.hua.bus.service.CustomerService;
import com.hua.bus.utils.ExprotCustomerUtils;
import com.hua.bus.vo.CustomerVo;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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

    /**
     * 导出客户数据
     */
    @RequestMapping("/exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response){
        List<Customer> customers = customerService.queryAllCustomerList(customerVo);
        for (Customer customer : customers) {
            System.out.println(customer.getIdentity());
        }
        String fileName="客户数据.xls";
        String sheetName="客户数据";
        ByteArrayOutputStream bos = ExprotCustomerUtils.exportCustomer(customers,sheetName);
        //处理文件名乱码
        try {
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

