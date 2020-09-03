package com.hua.sys.vo;

import com.hua.sys.entity.News;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cyh
 * @date 2020/9/1 14:14
 */
@Data
public class NewsVo extends News {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //接收多个id
    private Integer[] ids;
}
