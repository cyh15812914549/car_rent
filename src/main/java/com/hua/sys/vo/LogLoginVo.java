package com.hua.sys.vo;

import com.hua.sys.entity.LogLogin;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cyh
 * @date 2020/8/26 15:15
 */
@Data
public class LogLoginVo extends LogLogin {

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
