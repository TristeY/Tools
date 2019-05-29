package com.better.tools.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by whyme
 * Date 2019/5/27
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteExcelTestModel extends BaseRowModel {

    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    @ExcelProperty(value = "密码", index = 1)
    private  String password;

    @ExcelProperty(value = "年龄", index = 2)
    private String age;
}
