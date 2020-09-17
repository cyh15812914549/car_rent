package com.hua.bus.service;

import com.hua.bus.entity.Rent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.bus.vo.RentVo;
import com.hua.sys.utils.DataGridView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
public interface RentService extends IService<Rent> {

    /**
     * 查询所有车辆
     * @param rentVo
     * @return
     */
    DataGridView queryAllRent(RentVo rentVo);
}
