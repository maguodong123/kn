package cn.kn.service;

import cn.kn.dao.entity.BaseData;
import cn.kn.dao.entity.ExportExcel;
import cn.kn.dao.entity.table.Maps;
import cn.kn.dao.mapper.ExpansionFactoryMapper;
import cn.kn.utility.excel.CreateExcel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/5/27 15:25
 * @Description
 */
@RestController
public class Materiel {

    ExpansionFactoryMapper expansionFactoryMapper;

    public Materiel(ExpansionFactoryMapper expansionFactoryMapper) {
        this.expansionFactoryMapper = expansionFactoryMapper;
    }

    @RequestMapping(value = "getMateriel", method = RequestMethod.GET)
    public void getMateriel() {
        String regex = " ";
        List<BaseData> baseDataList = expansionFactoryMapper.getOneList();
        //第一步循环35个大类
        List<ExportExcel> lists = new LinkedList<>();
        Maps maps = new Maps();
        int count = 0;
        CreateExcel createExcel = new CreateExcel();
        ExportExcel exportExcel = new ExportExcel();
        try {
            for (BaseData baseData : baseDataList) {
                String[] str = baseData.getName().split(regex);
                exportExcel.setOneCode(str[0]);
                exportExcel.setOntName(str[1]);
                List<BaseData> oneSuper = expansionFactoryMapper.getSuperList(baseData.getId());
                for (BaseData oneSupers : oneSuper) {
                    String[] oneStr = oneSupers.getName().split(regex);
                    exportExcel.setTwoCode(oneStr[0]);
                    exportExcel.setTwoName(oneStr[1]);
                    //这一层就是最小的类了
                    List<BaseData> twoSuper = expansionFactoryMapper.getSuperList(oneSupers.getId());
                    for (BaseData three : twoSuper) {
                        String[] twoStr = three.getName().split(regex);
                        exportExcel.setThreeCode(twoStr[0]);
                        exportExcel.setThreeName(twoStr[1]);
                        List<String> ruleId = expansionFactoryMapper.getRuleId(three.getId());
                        for (String s : ruleId) {
                            maps = expansionFactoryMapper.getRule(s);
                            exportExcel.setRuleName(maps.getKey());
                            exportExcel.setRule(maps.getValue());
                            lists.add(count, exportExcel);
                            count++;
                            exportExcel = new ExportExcel();
                        }
                    }
                }
            }
            createExcel.exportExcelPaper(lists);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
