package com.hua.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.bus.entity.Car;
import com.hua.bus.mapper.CarMapper;
import com.hua.bus.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.bus.vo.CarVo;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.AppFileUtils;
import com.hua.sys.utils.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CarMapper carMapper;


    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        IPage<Car> page = new Page<Car>(carVo.getPage(),carVo.getLimit());
        //构造模糊查询
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(carVo.getCarnumber()) || StringUtils.isNotEmpty(carVo.getCartype())
                || StringUtils.isNotEmpty(carVo.getColor()) || StringUtils.isNotEmpty(carVo.getDescription())){
            queryWrapper.and(i -> i.like("carnumber ",carVo.getCarnumber())
                    .like("cartype",carVo.getCartype())
                    .like("color",carVo.getColor())
                    .like("description",carVo.getDescription()));
        }
        //抽取出来是为了防止%null%出现
        if (carVo.getIsrenting() != null){
            queryWrapper.eq("isrenting",carVo.getIsrenting());
        }
        page = carMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteCar(String carnumber) {
        //先获取要删除的数据
        Car car = carMapper.selectById(carnumber);
        //如果不是默认图片就删除
        if (!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
            AppFileUtils.removeFileByPath(car.getCarimg());
        }
        carMapper.deleteById(carnumber);
    }

    @Override
    public void deleteBatchCar(String[] carnumber) {
        for (String c : carnumber) {
            deleteCar(c);
        }
    }
}
