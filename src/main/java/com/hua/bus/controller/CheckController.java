package com.hua.bus.controller;


import com.hua.bus.entity.Car;
import com.hua.bus.entity.Rent;
import com.hua.bus.service.CarService;
import com.hua.bus.service.CheckService;
import com.hua.bus.service.RentService;
import com.hua.bus.vo.CheckVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private CarService carService;

    /**
     * 根据出租单号查询出租单信息
     */
    @RequestMapping("/checkRentExist")
    public Rent checkRentExist(String rentid){
        return rentService.getById(rentid); //1.null   2.返回对象
    }

    /**
     * 根据出租单号加载检查单的表单数据
     */
    @RequestMapping("/initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return checkService.initCheckFormData(rentid);
    }

    /**
     * 保存检查单数据
     */
    @RequestMapping("/saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try {
            checkService.save(checkVo);
            //更改出租单的状态
            Rent rent = rentService.getById(checkVo.getRentid());
            rent.setRentflag(SysConstast.RENT_BACK_TRUE);
            rentService.updateById(rent);
            //更改汽车的状态
            Car car = carService.getById(rent.getCarnumber());
            car.setIsrenting(SysConstast.RENT_CAR_FALSE);
            carService.updateById(car);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}

