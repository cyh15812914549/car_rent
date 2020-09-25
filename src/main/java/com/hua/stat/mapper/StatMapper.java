package com.hua.stat.mapper;

import com.hua.stat.entity.BaseEntity;

import java.util.List;

/**
 * @author cyh
 * @date 2020/9/18 15:12
 */
public interface StatMapper {

    List<BaseEntity> queryCustomerAreaStat();

    /**
     * 查询业务员年度业绩
     */
    List<BaseEntity> queryOpernameYearGradeStat(String year);

    /**
     * 加载公司年度统计数据
     */
    List<Double> queryCompanyYearGradeStat(String year);
}
