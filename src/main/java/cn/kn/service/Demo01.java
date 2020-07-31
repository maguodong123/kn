package cn.kn.service;

import cn.kn.dao.entity.Mpas;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
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
    private WorkflowConfigurationMapper wcm;

    public Demo01(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }


    /**
     * 大批量推送数据,先从excel读取code然后插入到PUSHFACE表里
     */
    @GetMapping(value = "demo2")
    public void demo2() throws IOException {
        //第一步读取excel表格中的编码数据并且一次性查询
        ReadExcel readExcel = new ReadExcel();
        List<String> excelValues = readExcel.readExcelString();
        for (String str : excelValues) {
            wcm.insertPush(str);
        }
        System.out.println("插入完成");
    }


    @GetMapping(value = "demo1")
    public void demo1() {
        /*
        临时生成略称程序
         */
        String abbreviation;
        String name = "";
        String software = "";
        String model = "";
        String[] task = {"2162995"};
        for (String str : task) {
            List<Mpas> maps = wcm.getProp(str);
            for (Mpas map : maps) {
                if ("名称".equals(map.getName())) {
                    name = map.getValue();
                }
                if ("软件型号".equals(map.getName())) {
                    software = map.getValue();
                }
                if ("控制板型号".equals(map.getName())) {
                    model = map.getValue();
                }
            }
            abbreviation = name + " " + model + " " + software;
            String id = wcm.getPropertiesId(str);
            wcm.updatePropValue(abbreviation, id);
            wcm.updateTask(abbreviation, str);
        }
        System.out.println("修改执行完毕");
    }


//    @GetMapping(value = "demo")
//    public void demo() {
//        String processId = "正式2700工厂采购类型流程:2:46920304";
//        List<String> lists = wcm.getLists();
//        for (String str : lists) {
//            int bill = Integer.parseInt(str);
//            //先删除错误的采购类型
////            wcm.deleteActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图");
//            //采购类型插入2次,采购,MRP
//            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图", 345);
//            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图", 352);
//            //MRP视图
//            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-MRP视图", 345);
//        }
//    }


}
