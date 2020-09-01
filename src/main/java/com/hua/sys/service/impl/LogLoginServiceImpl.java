package com.hua.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.sys.entity.LogLogin;
import com.hua.sys.mapper.LogLoginMapper;
import com.hua.sys.service.LogLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.LogLoginVo;
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
 * @since 2020-08-26
 */
@Service
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin> implements LogLoginService {

    @Autowired
    private LogLoginMapper logLoginMapper;

    @Override
    public DataGridView queryAllLogLogin(LogLoginVo logLoginVo) {
        IPage<LogLogin> page = new Page<LogLogin>(logLoginVo.getPage(),logLoginVo.getLimit());
        //构造模糊查询
        QueryWrapper<LogLogin> queryWrapper = new QueryWrapper<>();
        //降序
        queryWrapper.orderByDesc("logintime");
        if (StringUtils.isNotEmpty(logLoginVo.getLoginname()) || StringUtils.isNotEmpty(logLoginVo.getLoginip())){
            queryWrapper.and(i -> i.like("loginname",logLoginVo.getLoginname()).like("loginip",logLoginVo.getLoginip()));
        }
        if (logLoginVo.getStartTime() != null){
            queryWrapper.and(i->i.ge("logintime",logLoginVo.getStartTime()));
        }
        if (logLoginVo.getEndTime() != null){
            queryWrapper.and(i->i.le("logintime",logLoginVo.getEndTime()));
        }
        page = logLoginMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public void addLogLogin(LogLoginVo logLoginVo) {
        this.logLoginMapper.insert(logLoginVo);
    }

    @Override
    public void deleteLogLogin(Integer logInfoid) {
        this.logLoginMapper.deleteById(logInfoid);
    }

    @Override
    public void deleteBatchLogLogin(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteLogLogin(integer);
        }
    }
}
