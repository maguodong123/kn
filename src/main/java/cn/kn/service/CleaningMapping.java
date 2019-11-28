package cn.kn.service;

import cn.kn.dao.entity.table.TaskProperties;
import cn.kn.dao.excel.ExcelCode;
import cn.kn.dao.mapper.CleaningMapper;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

//清洗映射关系关联修复
@RestController
public class CleaningMapping {
    private CleaningMapper cleaningMapper;

    public CleaningMapping(CleaningMapper cleaningMapper) {
        this.cleaningMapper = cleaningMapper;
    }

    @GetMapping(value = "cleaningMapping")
    public void cleaningMapping() throws IOException {
        //1读取excel表格中新旧编码keyValue值
        ReadExcel readExcel = new ReadExcel();
        List<ExcelCode> excelMaps = readExcel.readExcelMap();
        //2从第一个键值对开始循环
        for (ExcelCode excelCode : excelMaps) {
            try {
                //3首先查编码对应最新的任务单
                Integer task = cleaningMapper.getCodeDataLibraryTask(excelCode.getCode());
                Integer codeID = cleaningMapper.getMDMDataLibraryID(excelCode.getCode());
                //4查找任务单属性
                List<TaskProperties> taskProperties = cleaningMapper.getTaskPropertiesList(task, "旧编码");
                String value = excelCode.getOldCode() + ";";
                //5先修改两张属性表
                HandleProperties.setTaskAndCode(value, taskProperties.get(0).getProperties(), task);
                //6然后修改旧编码表的关联关系
                cleaningMapper.setOldCode(codeID, excelCode.getOldCode());
                //最后修改关联关系表
                cleaningMapper.setCodeCorrelation(codeID, excelCode.getOldCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
