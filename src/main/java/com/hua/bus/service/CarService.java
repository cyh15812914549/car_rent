package com.hua.bus.service;

import com.hua.bus.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.bus.vo.CarVo;
import com.hua.sys.utils.DataGridView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
public interface CarService extends IService<Car> {

    /**
     * 查询所有车辆
     * @param carVo
     * @return
     */
    DataGridView queryAllCar(CarVo carVo);

    /**
     * 根据id删除车辆
     * @param carnumber
     */
    void deleteCar(String carnumber);
    /**
     * 批量删除车辆
     * @param carnumber
     */
    void deleteBatchCar(String [] carnumber);
}
