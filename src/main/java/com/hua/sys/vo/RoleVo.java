package com.hua.sys.vo;

import com.hua.sys.entity.Role;
import lombok.Data;

/**
 * @author cyh
 * @date 2020/8/12 15:22
 */
@Data
public class RoleVo extends Role {

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


