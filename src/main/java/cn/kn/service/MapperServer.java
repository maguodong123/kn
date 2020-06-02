package cn.kn.service;

import cn.kn.dao.entity.Values;
import cn.kn.dao.excel.ExcelCode;
import cn.kn.dao.mapper.HandlePropertiesMapper;
import cn.kn.dao.mapper.SelectDataMapper;
import cn.kn.utility.excel.CreateExcel;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/13 14:03
 * @Description
 */
@RestController
public class MapperServer {


    private HandlePropertiesMapper handlePropertiesMapper;

    private SelectDataMapper selectDataMapper;

    public MapperServer(HandlePropertiesMapper handlePropertiesMapper, SelectDataMapper selectDataMapper) {
        this.handlePropertiesMapper = handlePropertiesMapper;
        this.selectDataMapper = selectDataMapper;
    }

    @GetMapping(value = "mapperServer")
    public void mapperServer() throws IOException {
        String str;
        String task;
        String oldCodes;
        String[] array;
        Values values;
        String codeId;
        try {
            //第一步读取excel表格中的编码数据并且一次性查询
            ReadExcel readExcel = new ReadExcel();
            //没有经过处理的,映射关系
            List<ExcelCode> untreated = readExcel.readExcelMap();

            //经过重新映射处理过的对应关系
            List<ExcelCode> processed = new ArrayList<>();
            for (ExcelCode excelCode : untreated) {
                ExcelCode excelCodes = new ExcelCode();
                excelCodes.setCode(excelCode.getCode());
                str = excelCode.getOldCode();
                array = str.split(";");
                //如何还生成了二次编码,就在第一次生成的旧编码栏里面存储新的编码
                if (array.length == 2) {
                    oldCodes = array[1];
                    task = selectDataMapper.getTask(oldCodes);
                    values = selectDataMapper.getOneValues(task);
                    setTaskAndCode(excelCode.getCode().concat(";"), Integer.parseInt(values.getPROPERTIES()), Integer.parseInt(task));
                    //同时便编码表里面得旧编码换成新编码得
                    selectDataMapper.updateMDM_DATALIBRAY(oldCodes, excelCode.getCode().concat(";"));
                }
                excelCodes.setOldCode(array[0]);
                processed.add(excelCodes);
            }

            //处理完毕后就没用了清空就行
            untreated.clear();
            //现在开始循环处理重新梳理过的映射关系
            for (ExcelCode excelCode : processed) {
                task = selectDataMapper.getTask(excelCode.getCode());
                values = selectDataMapper.getOneValues(task);
                //这个是改任务单表和编码属性表的数据
                setTaskAndCode(excelCode.getOldCode().concat(";"), Integer.parseInt(values.getPROPERTIES()), Integer.parseInt(task));
                //现在开始改旧编码的两张关联表
                codeId = selectDataMapper.getCodeId(excelCode.getCode());
                //先改旧编码表的关联关系
                selectDataMapper.updateOldCode(codeId, excelCode.getOldCode());
                //再处理关联表得关系
                int count = selectDataMapper.updateOldcode2lib(codeId, excelCode.getOldCode());
                //如果count等于0证明没有修改成功,则插入一条新的记录
                if (count == 0) {
                    selectDataMapper.insertOldcode2lib(excelCode.getOldCode(), codeId);
                }
                //最后修改编码表得oldCode字段
                selectDataMapper.updateMDM_DATALIBRAY(excelCode.getCode(), excelCode.getOldCode().concat(";"));
            }
            //最后往excel表格里面记录一下数据
//            CreateExcel.exportExcelPaper(processed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTaskAndCode(String value, Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesId = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.updateTaskProperties(value, properties, taskBill);
            handlePropertiesMapper.updateCodePropType(value, properties, taskPropertiesId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
