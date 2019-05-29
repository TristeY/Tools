package com.better.tools.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.better.tools.model.WriteExcelTestModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whyme
 * Date 2019/5/27
 */
public class ExcelUtil {

    public void  writeExcel() {
        //获取excel写入实例
        OutputStream excelFile = null;
        try {
            excelFile = new FileOutputStream("/data/sdcard/excel/one.excel");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelWriter excelWriter = EasyExcelFactory.getWriter(excelFile);

        //创建sheet
        Sheet sheet = new Sheet(1,0,WriteExcelTestModel.class);
        sheet.setSheetName("First");

        //写入数据
        excelWriter.write(createModelList(),sheet);
        excelWriter.finish();

        //TODO Exception优化
        try {
            excelFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<WriteExcelTestModel> createModelList(){
     List<WriteExcelTestModel> modelList = new ArrayList<>();
     for (int i = 0;i <100 ;i++){
         WriteExcelTestModel model = WriteExcelTestModel.builder().name("su "+ 1 ).password("pw " + i).age(i + "years old").build();
         modelList.add(model);
    }
     return modelList;
    }
}
