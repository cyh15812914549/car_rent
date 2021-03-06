package com.hua.stat.service.impl;

import com.hua.stat.entity.BaseEntity;
import com.hua.stat.mapper.StatMapper;
import com.hua.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cyh
 * @date 2020/9/18 15:11
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;

    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return  statMapper.queryCustomerAreaStat();
    }

    @Override
    public List<BaseEntity> loadOpernameYearGradeStatList(String year) {
        return statMapper.queryOpernameYearGradeStat(year);
    }

    @Override
    public List<Double> loadCompanyYearGradeStatList(String year) {
        return statMapper.queryCompanyYearGradeStat(year);
    }
}
