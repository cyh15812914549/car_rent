package com.hua.sys.vo;

import com.hua.sys.entity.Menu;
import lombok.Data;

/**
 * @author cyh
 * @date 2020/8/12 15:22
 */
@Data
public class MenuVo extends Menu {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
}


