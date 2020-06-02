package cn.kn.service;

import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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

    @GetMapping(value = "demo")
    public void demo() {
        String processId = "正式2700工厂采购类型流程:2:46920304";

        List<String> lists = wcm.getLists();

        for (String str : lists) {
            int bill = Integer.parseInt(str);
            //先删除错误的采购类型
//            wcm.deleteActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图");
            //采购类型插入2次,采购,MRP
            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图", 345);
            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-采购视图", 352);
            //MRP视图
            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-MRP视图", 345);
        }


    }


}
