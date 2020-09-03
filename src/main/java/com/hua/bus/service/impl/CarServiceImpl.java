package com.hua.bus.service.impl;

import com.hua.bus.entity.Car;
import com.hua.bus.mapper.CarMapper;
import com.hua.bus.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
