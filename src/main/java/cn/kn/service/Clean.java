package cn.kn.service;

import cn.kn.dao.mapper.SelectDataMapper;
import cn.kn.utility.excel.ReadExcel;
import cn.kn.utility.excel.WriteExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map map = new HashMap();
        for (String value : excelValues) {
            String oldcode = sd.getOldCode(value);
            map.put(value, oldcode);
            break;
        }
        WriteExcel.writeExcel(map);
    }


}
