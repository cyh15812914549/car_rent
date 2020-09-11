package com.hua.bus.vo;

import com.hua.bus.entity.Car;
import com.hua.bus.entity.Rent;
import lombok.Data;

/**
 * @author cyh
 * @date 2020/9/3 20:11
 */
@Data
public class RentVo extends Rent {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

}
