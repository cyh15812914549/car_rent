package com.hua.bus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_check")
@ApiModel(value="Check对象", description="")
public class Check implements Serializable {

    private static final long serialVersionUID=1L;

    private String checkid;

    private LocalDateTime checkdate;

    private String checkdesc;

    private String problem;

    private Double paymoney;

    private String opername;

    private String rentid;

    private LocalDateTime createtime;


}
