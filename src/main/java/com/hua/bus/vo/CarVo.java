package com.hua.bus.vo;

import com.hua.bus.entity.Car;
import lombok.Data;

/**
 * @author cyh
 * @date 2020/9/3 20:11
 */
@Data
public class CarVo extends Car {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    // 接收多个id
    private String[] ids;
}
