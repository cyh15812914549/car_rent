package com.hua.bus.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hua.bus.entity.Car;
import com.hua.bus.service.CarService;
import com.hua.bus.vo.CarVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.AppFileUtils;
import com.hua.sys.utils.DataGridView;
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
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 加载车辆列表返回DataGridView
     */
    @RequestMapping("/loadAllCar")
    public DataGridView loadAllCar(CarVo carVo) {
        return carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     * @param carVo
     * @return
     */
    @RequestMapping("/addCar")
    public ResultObj addCar(CarVo carVo){
        try {
            //如果不是默认图片就去掉图片的_temp的后缀
            if (!carVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            carService.save(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改车辆
     */
    @RequestMapping("/updateCar")
    public ResultObj updateCar(CarVo carVo){
        try {
            String carimg = carVo.getCarimg();
            if (carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)){
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                //把原来的删除
                Car car = carService.getById(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            carService.updateById(carVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆
     */
    @RequestMapping("/deleteCar")
    public ResultObj deleteCar(CarVo carVo) {
        try {
            carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     */
    @RequestMapping("/deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo) {
        try {
            carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

