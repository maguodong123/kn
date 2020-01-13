package cn.kn.service;

import cn.kn.dao.excel.ExcelCode;
import cn.kn.dao.mapper.SelectDataMapper;
import cn.kn.utility.excel.CreateExcel;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/6 10:07
 * @Description
 */
@RestController
public class Clean {

    @Autowired
    SelectDataMapper sd;

    @GetMapping(value = "clean")
    public void clean() throws IOException {
        //第一步读取excel表格中的编码数据并且一次性查询
        ReadExcel readExcel = new ReadExcel();
        List<String> excelValues = readExcel.readExcelString();
        List<ExcelCode> excelCodes = new ArrayList<>();
        for (String value : excelValues) {
            ExcelCode excelCode = new ExcelCode();
            excelCode.setCode(value);
            excelCode.setOldCode(sd.getOldCode(value));
            excelCodes.add(excelCode);
        }
        CreateExcel.exportExcelPaper(excelCodes);
    }

}
