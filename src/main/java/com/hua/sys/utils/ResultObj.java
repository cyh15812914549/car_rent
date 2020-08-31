package com.hua.sys.utils;

import com.hua.sys.constast.SysConstast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyh
 * @date 2020/8/18 11:29
 */
@Data
@NoArgsConstructor
public class ResultObj {
    private Integer code=0;
    private String msg;

    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.DELETE_ERROR);

    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.DISPATCH_ERROR);

    /**
     * 状态码0
     */
    public static final ResultObj STATUS_TRUE=new ResultObj(SysConstast.CODE_SUCCESS);
    /**
     * 状态码-1
     */
    public static final ResultObj STATUS_FALSE=new ResultObj(SysConstast.CODE_ERROR);



    private ResultObj(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private ResultObj(Integer code) {
        super();
        this.code = code;
    }
}
