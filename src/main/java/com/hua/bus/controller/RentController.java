package com.hua.bus.controller;


import com.hua.bus.entity.Car;
import com.hua.bus.entity.Customer;
import com.hua.bus.entity.Rent;
import com.hua.bus.service.CarService;
import com.hua.bus.service.CustomerService;
import com.hua.bus.service.RentService;
import com.hua.bus.utils.ExprotRentUtils;
import com.hua.bus.vo.CustomerVo;
import com.hua.bus.vo.RentVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    /**
     * 查询出租单列表
     */
    @RequestMapping("/loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo) {
        return rentService.queryAllRent(rentVo);
    }
    /**
     * 修改出租单
     */
    @RequestMapping("/updateRent")
    public ResultObj updateRent(RentVo rentVo){
        try {
            rentService.updateById(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除出租单
     */
    @RequestMapping("/deleteRent")
    public ResultObj deleteRent(RentVo rentVo) {
        try {
            //先把车辆修改为未出租
            Rent rent = rentService.getById(rentVo.getRentid());
            Car car = new Car();
            car.setCarnumber(rent.getCarnumber());
            car.setIsrenting(SysConstast.RENT_CAR_FALSE);
            carService.updateById(car);

            rentService.removeById(rentVo.getRentid());

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 导出出租单数据
     */
    @RequestMapping("/exportRent")
    public ResponseEntity<Object> exportRent(String rentid){
        //根据出租单号查询出租单信息
        Rent rent = rentService.getById(rentid);
        //根据身份证号查询客户信息
        Customer customer = customerService.getById(rent.getIdentity());
        String fileName = customer.getCustname() + "-的出租单.xls";
        String sheetName = customer.getCustname() + "出租单";

        ByteArrayOutputStream bos = ExprotRentUtils.exportRent(rent,customer,sheetName);
        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

