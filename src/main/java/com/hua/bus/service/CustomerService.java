package com.hua.bus.service;

import com.hua.bus.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.bus.vo.CustomerVo;
import com.hua.sys.utils.DataGridView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 查询所有日志
     * @param customerVo
     * @return
     */
    DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 查询所有日志
     * @param customerVo
     * @return
     */
    List<Customer> queryAllCustomerList(Customer customer);

    /**
     * 根据id删除客户
     * @param identity
     */
    void deleteCustomer(String identity);
    /**
     * 批量删除客户
     * @param identitys
     */
    void deleteBatchCustomer(String [] identitys);
}
