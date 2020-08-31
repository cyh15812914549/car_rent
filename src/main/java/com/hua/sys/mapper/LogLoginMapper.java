package com.hua.sys.mapper;

import com.hua.sys.entity.LogLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyh
 * @since 2020-08-26
 */
public interface LogLoginMapper extends BaseMapper<LogLogin> {

    /**
     * 查询日志
     */
    List<LogLogin> queryAllLogLogin(LogLogin logLogin);
}
