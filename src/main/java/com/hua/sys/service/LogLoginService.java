package com.hua.sys.service;

import com.hua.sys.entity.LogLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.LogLoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-08-26
 */
public interface LogLoginService extends IService<LogLogin> {

    /**
     * 查询所有日志
     * @param logLoginVo
     * @return
     */
    DataGridView queryAllLogLogin(LogLoginVo logLoginVo);
    /**
     * 添加日志
     * @param logLoginVo
     */
    void addLogLogin(LogLoginVo logLoginVo);
    /**
     * 根据id删除日志
     * @param logLoginid
     */
    void deleteLogLogin(Integer logLoginid);
    /**
     * 批量删除日志
     */
    void deleteBatchLogLogin(Integer [] ids);
}
