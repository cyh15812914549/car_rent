package com.hua.stat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyh
 * @date 2020/9/18 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private String name;
    private Double value;
}
