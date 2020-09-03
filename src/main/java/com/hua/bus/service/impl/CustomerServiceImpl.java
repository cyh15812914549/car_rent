package com.hua.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.bus.entity.Customer;
import com.hua.bus.mapper.CustomerMapper;
import com.hua.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.bus.vo.CustomerVo;
import com.hua.sys.utils.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-09-03
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page = new Page<Customer>(customerVo.getPage(),customerVo.getLimit());
        //构造模糊查询
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        //降序
        queryWrapper.orderByDesc("createtime");
        if (StringUtils.isNotEmpty(customerVo.getIdentity()) || StringUtils.isNotEmpty(customerVo.getCustname())
            || StringUtils.isNotEmpty(customerVo.getPhone()) || StringUtils.isNotEmpty(customerVo.getCareer())
            || StringUtils.isNotEmpty(customerVo.getAddress())){
            queryWrapper.and(i -> i.like("identity ",customerVo.getIdentity())
                    .like("custname",customerVo.getCustname())
                    .like("phone",customerVo.getPhone())
                    .like("career",customerVo.getCareer())
                    .like("address",customerVo.getAddress())
                    .eq(customerVo.getSex() != null,"sex",customerVo.getSex()));
        }
        page = customerMapper.selectPage(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteById(identity);
    }

    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity : identitys) {
            deleteCustomer(identity);
        }
    }


}
