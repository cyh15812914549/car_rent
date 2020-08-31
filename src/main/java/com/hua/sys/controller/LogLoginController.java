package com.hua.sys.controller;


import com.hua.sys.service.LogLoginService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.vo.LogLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-08-26
 */
@RestController
@RequestMapping("/log-login")
public class LogLoginController {

    @Autowired
    private LogLoginService logLoginService;

    /**
     * 加载日志列表返回DataGridView
     */
    @RequestMapping("/loadAllLogLogin")
    public DataGridView loadAllLogLogin(LogLoginVo logLoginVo) {
        return logLoginService.queryAllLogLogin(logLoginVo);
    }

    /**
     * 删除日志
     */
    @RequestMapping("/deleteLogLogin")
    public ResultObj deleteLogLogin(LogLoginVo logLoginVo) {
        try {
            logLoginService.deleteLogLogin(logLoginVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     */
    @RequestMapping("/deleteBatchLogLogin")
    public ResultObj deleteBatchLogLogin(LogLoginVo logLoginVo) {
        try {
            logLoginService.deleteBatchLogLogin(logLoginVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

