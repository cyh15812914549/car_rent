package com.hua.sys.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyh
 * @date 2020/8/14 17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataGridView {

    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;

    public DataGridView(Object data) {
        super();
        this.data = data;
    }

    public DataGridView(Long count,Object data) {
        super();
        this.count = count;
        this.data = data;
    }
}
