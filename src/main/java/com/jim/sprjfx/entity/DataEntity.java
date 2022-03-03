package com.jim.sprjfx.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// get set 方法
@Data
// 有参构造
@AllArgsConstructor
// 无参构造
@NoArgsConstructor
public class DataEntity {
    private int dataNo;
    private String datatype;
}
