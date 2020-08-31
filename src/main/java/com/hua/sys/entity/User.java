package com.hua.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyh
 * @since 2020-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userid;

    private String loginname;

    private String identity;

    private String realname;

    @ApiModelProperty(value = "0女1男")
    private Integer sex;

    private String address;

    private String phone;

    private String pwd;

    private String position;

    @ApiModelProperty(value = "1，超级管理员,2，系统用户")
    private Integer type;

    private Integer available;


}
