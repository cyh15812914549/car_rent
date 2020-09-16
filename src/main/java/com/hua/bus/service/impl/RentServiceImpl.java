package com.hua.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.bus.entity.Rent;
import com.hua.bus.mapper.RentMapper;
import com.hua.bus.service.RentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.bus.vo.RentVo;
import com.hua.sys.utils.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RentServiceImpl extends ServiceImpl<RentMapper, Rent> implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        IPage<Rent> page = new Page<Rent>(rentVo.getPage(),rentVo.getLimit());
        //构造模糊查询
        QueryWrapper<Rent> queryWrapper = new QueryWrapper<>();
        //降序
        queryWrapper.orderByDesc("createtime");
        if (StringUtils.isNotEmpty(rentVo.getRentid()) || StringUtils.isNotEmpty(rentVo.getCarnumber())
                || StringUtils.isNotEmpty(rentVo.getIdentity())){
            queryWrapper.and(i -> i.like("rentid ",rentVo.getRentid())
                    .like("carnumber",rentVo.getCarnumber())
                    .like("identity",rentVo.getIdentity()));
        }
        //抽取出来是为了防止%null%出现
        if (rentVo.getRentflag() != null){
            queryWrapper.eq("rentflag",rentVo.getRentflag());
        }
        if (rentVo.getStartTime() != null){
            queryWrapper.and(i->i.ge("createtime",rentVo.getStartTime()));
        }
        if (rentVo.getEndTime() != null){
            queryWrapper.and(i->i.le("createtime",rentVo.getEndTime()));
        }
        page = rentMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}
