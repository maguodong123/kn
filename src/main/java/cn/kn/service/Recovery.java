package cn.kn.service;

import cn.kn.dao.entity.Values;
import cn.kn.dao.mapper.HandlePropertiesMapper;
import cn.kn.dao.mapper.SelectDataMapper;
import cn.kn.utility.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/9 14:36
 * @Description
 */
@RestController
public class Recovery {
    @Autowired
    SelectDataMapper sd;

    @Autowired
    HandlePropertiesMapper handlePropertiesMapper;

    @GetMapping(value = "recovery")
    public void recovery() throws IOException {
        String name = "邢万锦";
        String factory = "物资2200工厂-MRP视图";
        String task;
        String str;

        ReadExcel readExcel = new ReadExcel();
        List<String> excelValues = readExcel.readExcelString();
        Map<String, String> map = new HashMap<>(30);
        try {
            for (String code : excelValues) {
                task = sd.getTask(code);
                str = sd.getDataInterface(code, name, factory);
                String[] array = str.split(",");
                for (String s : array) {
                    if (s.split(":").length > 1) {
                        String[] mapper = s.split(":");
                        map.put(mapper[0], mapper[1]);
                    }
                }
                List<Values> values = sd.getValues(task);
                //键找值遍历
                Set<String> set1 = map.keySet();
                for (Values value1 : values) {
                    for (String key : set1) {
                        if ("计划交货时间".equals(value1.getNAME()) || "收货处理时间".equals(value1.getNAME())) {
                            continue;
                        }
                        if (value1.getNAME().equals(key)) {
                            setTaskAndCode(map.get(key), Integer.parseInt(value1.getPROPERTIES()), Integer.parseInt(task));
                        }
                    }
                }

                map.clear();
                values.clear();
                //然后在处理黄敏的重新覆盖掉
                String huangstr = sd.getDataInterfaceError(code, "黄敏", factory);
                String[] arrays = huangstr.split(",");
                for (String s : arrays) {
                    if (s.split(":").length > 1) {
                        String[] mapper = s.split(":");
                        map.put(mapper[0], mapper[1]);
                    }
                }
                Set<String> set2 = map.keySet();
                Values jihuajiaohuo = sd.getOneValues(task);
                Values shouhuochuli = sd.getTwoValues(task);
                for (String key : set2) {
                    if ("计划交货时间".equals(key)) {
                        setTaskAndCode(map.get(key), Integer.parseInt(jihuajiaohuo.getPROPERTIES()), Integer.parseInt(task));
                    }
                    if ("收货处理时间".equals(key)) {
                        setTaskAndCode(map.get(key), Integer.parseInt(shouhuochuli.getPROPERTIES()), Integer.parseInt(task));
                    }
                }
                map.clear();
                set1.clear();
                set2.clear();
            }
            System.out.println("程序循环完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTaskAndCode(String value, Integer properties, Integer taskBill) {
        try {
            Integer taskPropertiesID = handlePropertiesMapper.getTaskPropertiesID(properties, taskBill);
            handlePropertiesMapper.updateTaskProperties(value, properties, taskBill);
            handlePropertiesMapper.updateCodePropType(value, properties, taskPropertiesID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
