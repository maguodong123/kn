package cn.kn.service;

import cn.kn.dao.excel.ExcelValue;
import cn.kn.dao.mapper.HandlePropertiesMapper;
import cn.kn.utility.excel.ReadExcel;
import cn.kn.utility.exceptionhandling.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 这个类主要是对B_Taskproperties和Mdm_Proptype这两张表里面的数据做查改删操作的。一般不做新增操作
 * B_Taskproperties这是任务单属性表，包括历史和最新的属性都会存储在这张表里面。所以垃圾数据很多
 * Mdm_Proptype编码属性表,只存对应编码的最新数据
 */
@RestController
public class HandleProperties {
    private static HandlePropertiesMapper handlePropertiesMapper;
    private final Logger logger = LoggerFactory.getLogger(HandleProperties.class);

    public HandleProperties(HandlePropertiesMapper handlePropertiesMapper) {
        this.handlePropertiesMapper = handlePropertiesMapper;
    }


    @GetMapping(value = "handleProperties")
    public void handleProperties() throws IOException {
//        ReadExcel readExcel = new ReadExcel();
//        List<ExcelValue> excelValues = readExcel.readExcelValue();
//        for (ExcelValue value : excelValues) {
//            setTaskAndCode(value.getValue(), Integer.parseInt(value.getProperties()), Integer.parseInt(value.getTaskBill()));
//        }
        //这是根据那个很长的关联查询批量删除视图的
//        List<ExcelSAP> excelSAPS = readExcel.readExcelSAP();
//        for (ExcelSAP sap : excelSAPS) {
//            List<Integer> integers = hpm.getRelationalQueryTaskPropertiesID(sap.getTaskBill(),sap.getDataName(),sap.getCode());
//            for (int i=0;i<integers.size();i++){
//                deleteTaskAndCodeID(integers.get(i));
//            }
//        }
//        List<ExcelMDM> excelMDMS = readExcel.readExcelMap();
//        for (ExcelMDM excelMDM:excelMDMS){
//            deleteTaskAndCode(excelMDM.getKey(),excelMDM.getValue());
//        }
        int[] ints = {5484333,5484034};






    }


    //修改属性值,传三个条件修改的值,属性id,任务单id
    static void setTaskAndCode(String value, Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesID = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.updateTaskProperties(value, properties, taskBill);
            handlePropertiesMapper.updateCodePropType(value, properties, taskPropertiesID);
        } catch (Exception e) {
            e.toString();
        }
    }

    //删两张表,只需要ID作为条件即可
    private void deleteTaskAndCodeID(Integer taskPropertiesID) {
        try {
            handlePropertiesMapper.deleteCodePropType(taskPropertiesID);
            handlePropertiesMapper.deleteTaskProperties(taskPropertiesID);
        } catch (Exception e) {
            logger.info(ResultEnum.DeleteError.getMsg());
        }
    }

    //同时删除任务单表和属性表的方法
    private void deleteTaskAndCode(Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesID = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.deleteCodePropType(taskPropertiesID);
            handlePropertiesMapper.deleteTaskProperties(taskPropertiesID);
        } catch (Exception e) {
            logger.info(ResultEnum.DeleteError.getMsg());
        }
    }


}
