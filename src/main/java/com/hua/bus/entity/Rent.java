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
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_rent")
@ApiModel(value="Rent对象", description="")
public class Rent implements Serializable {

    private static final long serialVersionUID=1L;

    private String rentid;

    private Double price;

    private LocalDateTime begindate;

    private LocalDateTime returndate;

    private Integer rentflag;

    private String identity;

    private String carnumber;

    private String opername;

    private LocalDateTime createtime;


}
