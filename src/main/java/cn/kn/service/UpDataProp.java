package cn.kn.service;

import cn.kn.dao.excel.ExcelMDM;
import cn.kn.dao.mapper.SelectViewMapper;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UpDataProp {
    @Autowired
    SelectViewMapper sv;

    @RequestMapping(value = "upDataProp", method = RequestMethod.GET)
//    public void upDataProp() throws IOException {
//        System.err.println("程序开始执行!");
//        ReadExcel readExcel = new ReadExcel();
//        List<ExcelMDM> excelMaps = readExcel.readExcelMap();
//
//        for (ExcelMDM listData:excelMaps){
//            Integer taskBill = listData.getKey();
//            Integer properties = listData.getValue();
////            Integer taskProperTiesID = sv.getTaskPropertiesID(properties, taskBill);
////            String value = sv.getMdmPropTypeValue(properties,taskProperTiesID);
////            upDataTaskProperties(value,properties,taskBill);
//            upData("",properties,taskBill);
//        }
//    }



    private void upDataTaskProperties(String value, Integer properties, Integer taskBill){
        sv.setProperties(value, properties, taskBill);
    }


    private void upData(String value, Integer properties, Integer taskBill){
        Integer taskProperTiesID = sv.getTaskPropertiesID(properties, taskBill);
        sv.setProperties(value, properties, taskBill);
        sv.setMdmPropType(value, properties, taskProperTiesID);
    }







}
