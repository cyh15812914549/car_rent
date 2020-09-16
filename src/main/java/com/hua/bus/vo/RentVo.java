package com.hua.bus.vo;

import com.hua.bus.entity.Car;
import com.hua.bus.entity.Rent;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cyh
 * @date 2020/9/3 20:11
 */
@Data
public class RentVo extends Rent {

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

}
