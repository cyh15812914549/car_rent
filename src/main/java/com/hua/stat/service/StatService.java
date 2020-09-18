package com.hua.stat.service;

import com.hua.stat.entity.BaseEntity;

import java.util.List;

/**
 * @author cyh
 * @date 2020/9/18 15:04
 */
public interface StatService {

    /**
     * 查询客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();


    /**
     * 业务员年度统计数据
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);


    /**
     * 加载年度统计数据
     */
    List<Double> loadCompanyYearGradeStatList(String year);
}
