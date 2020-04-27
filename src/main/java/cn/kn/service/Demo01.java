package cn.kn.service;

import cn.kn.dao.excel.ExcelCode;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/2/27 18:27
 * @Description
 */
@RestController
public class Demo01 {

    @GetMapping(value = "renming")
    public void renming() throws IOException {
        //1读取excel表格中新旧编码keyValue值
        ReadExcel readExcel = new ReadExcel();
        List<ExcelCode> excelMaps = readExcel.readExcelMap();
        //2从第一个键值对开始循环
        for (ExcelCode excelCode : excelMaps) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(excelCode.getCode() + "_" + excelCode.getOldCode());
            System.out.println(stringBuilder.toString());
        }

    }

}
