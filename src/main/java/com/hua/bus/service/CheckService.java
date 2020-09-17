package com.hua.bus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hua.bus.entity.Check;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-09-17
 */
public interface CheckService extends IService<Check> {

    /**
     * 根据出租单号加载检查单的表单数据
     */
    Map<String,Object> initCheckFormData(String rentid);
}
