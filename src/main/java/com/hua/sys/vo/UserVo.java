package com.hua.sys.vo;

import com.hua.sys.entity.User;
import lombok.Data;

/**
 * @author cyh
 * @date 2020/7/31 17:21
 */
@Data
public class UserVo extends User {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 接收多个角色id
     */
    private Integer[] ids;
}
