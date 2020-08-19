package cn.kn.service;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.ThreeField;
import cn.kn.dao.entity.table.Maps;
import cn.kn.dao.mapper.ExpansionFactoryMapper;
import cn.kn.dao.mapper.HandlePropertiesMapper;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/8/14 16:42
 * @Description
 */
@RestController
public class Demo02 {

    private ExpansionFactoryMapper expansionFactoryMapper;
    private HandlePropertiesMapper handlePropertiesMapper;

    public Demo02(ExpansionFactoryMapper expansionFactoryMapper, HandlePropertiesMapper handlePropertiesMapper) {
        this.expansionFactoryMapper = expansionFactoryMapper;
        this.handlePropertiesMapper = handlePropertiesMapper;
    }

    @GetMapping(value = "demo02")
    public void demo02() {
        try {
            ReadExcel readExcel = new ReadExcel();
            List<ThreeField> excelValues = readExcel.readExcelThreeField();
            for (ThreeField str : excelValues) {
                //第一步根据编码获得属性和编码的id
                List<Maps> maps = expansionFactoryMapper.getByIdAndName(str.getCode());
                //采购组
                setTaskAndCode(str.getPurchase(), Integer.parseInt(maps.get(0).getID()), Integer.parseInt(maps.get(0).getTASKBILL()));
                //计划交货时间
                setTaskAndCode(str.getTime(), Integer.parseInt(maps.get(1).getID()), Integer.parseInt(maps.get(1).getTASKBILL()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //修改属性值,传三个条件修改的值,属性id,任务单id
    void setTaskAndCode(String value, Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesID = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.updateTaskProperties(value, properties, taskBill);
            handlePropertiesMapper.updateCodePropType(value, properties, taskPropertiesID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
