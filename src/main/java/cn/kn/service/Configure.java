package cn.kn.service;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/6/19 15:51
 * @Description 康尼2020深化应用2.5包装字段数据维护配置业务类
 * 1.视图节点
 * 2.视图属性
 * 3.传输接口
 */
@RestController
public class Configure {

    @Resource
    WorkflowConfigurationMapper wcm;


    /**
     * 这个方法一次性完成
     */
    @RequestMapping(value = "configureArray", method = RequestMethod.GET)
    public void configureArray() {
        try {
            //初始化数据：任务单id，模型id，视图id
            int billId;
            int ruleId;
            int viewId;
            //属性数组
            Integer[] prop;
            List<ProcessModel> processModels;
            List<Process> processList = getProcess();
            for (Process process : processList) {
                //第一步:查询出要配置的流程模型下的所有单据,正式2100工厂采购类型:11:49280125

                if ("正式-2100工厂流程:18:62743804".equals(process.getProcessId()) || "正式-2200工厂流程:15:62743816".equals(process.getProcessId())) {
                    processModels = wcm.getProcessModel("正式物资编码流程");
                } else {
                    processModels = wcm.getProcessModel(process.getProcessId());
                }
                //第二步:循环所有单据,并循环对所有单据做出配置处理
                for (ProcessModel processModel : processModels) {
                    billId = processModel.getId();
                    ruleId = processModel.getDataRule();
                    //查出对应的MRP视图ID
                    viewId = wcm.getViewId(billId, ruleId, process.getProcessId(), process.getMrpName());
                    if (viewId == 0) {
                        continue;
                    }
                    //这个地方插入视图
//                    wcm.deleteActReAuditViewOne(process.getViewName(), viewId, billId, process.getProcessId());
                    wcm.insertActReAuditView(process.getViewName(), viewId, ruleId, billId, process.getProcessId());
                    //查出要插入的属性
                    prop = wcm.getPropId(viewId, billId, process.getProcessId(), process.getViewName());
                    //循环属性插入
                    for (Integer str : prop) {
//                        wcm.deleteActReAuditProps(viewId, billId, process.getProcessId());
                        wcm.insertActReAuditPropsStorageView(process.getViewName(), billId, viewId, str, 0, process.getProcessId());
                    }
                    //配置传输接口后就大功告成了
                    wcm.insertActReAuditInterFaceTaskEvent(process.getProcessId(), billId, process.getViewName(), process.getInterfaceId());
                }
            }
            System.out.println("===========================peizhiwancheng123132=================");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 更换流程版本六步走
     */
    @RequestMapping(value = "sixSteps", method = RequestMethod.GET)
    public void sixSteps() {
        try {
            String newProcess = "正式2100工厂采购类型:12:63390604";
            String oldProcess = "正式2100工厂采购类型:11:62743808";
            wcm.processUpdateTask(newProcess, oldProcess);
            wcm.processUpdateApplicant(newProcess, oldProcess);
            wcm.processUpdateProp(newProcess, oldProcess);
            wcm.processUpdateView(newProcess, oldProcess);
            wcm.processUpdateFace(newProcess, oldProcess);
            wcm.processUpdateExit(newProcess, oldProcess);
            System.out.println("完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 这个方法只能一次配置一个工厂
     */
//    @RequestMapping(value = "configure", method = RequestMethod.GET)
//    public void configure() {
//        try {
//            //初始化数据
//            int billId, ruleId, viewId;
//            String processId = "正式2100工厂采购类型:11:49280125";
//            Integer[] prop;
//            String viewName = "2100工厂-维护包装数据";
//            //第一步:查询出要配置的流程模型下的所有单据,正式2100工厂采购类型:11:49280125
//            List<ProcessModel> processModels = wcm.getProcessModel("正式2100工厂采购类型:11:49280125");
//            //第二步:循环所有单据,并循环对所有单据做出配置处理
//            for (ProcessModel processModel : processModels) {
//                billId = processModel.getId();
//                ruleId = processModel.getDataRule();
//                //查出对应的MRP视图ID
//                viewId = wcm.getViewId(billId, ruleId, processId);
//                if (viewId == 0) {
//                    continue;
//                }
//                //查出要插入的属性
//                prop = wcm.getPropId(viewId, billId, processId);
//                //循环属性插入
//                for (Integer str : prop) {
//                    wcm.insertActReAuditPropsStorageView(viewName, billId, viewId, str, 0, processId);
//                }
//                //配置传输接口后就大功告成了
//                wcm.insertActReAuditInterFaceTaskEvent(processId, billId, viewName, 427);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    public List<Process> getProcess() {
        Process process1 = new Process();
        process1.setProcessId("正式-2100工厂流程:18:62743804");
        process1.setViewName("2100工厂-维护包装数据");
        process1.setMrpName("2100工厂-MRP视图");
        process1.setInterfaceId(11);
        Process process2 = new Process();
        process2.setProcessId("正式2100工厂采购类型:11:62743808");
        process2.setViewName("2100工厂-维护包装数据");
        process2.setMrpName("2100工厂-MRP视图");
        process2.setInterfaceId(11);
        Process process3 = new Process();
        process3.setProcessId("正式-2200工厂流程:15:62743816");
        process3.setViewName("2200工厂-维护包装数据");
        process3.setMrpName("2200工厂-MRP视图");
        process3.setInterfaceId(23);
        Process process4 = new Process();
        process4.setProcessId("正式2200工厂采购类型流程:8:62743812");
        process4.setViewName("2200工厂-维护包装数据");
        process4.setMrpName("2200工厂-MRP视图");
        process4.setInterfaceId(23);
        List<Process> processList = new ArrayList<>(4);
        processList.add(process1);
        processList.add(process2);
        processList.add(process3);
        processList.add(process4);
        return processList;
    }
}


class Process {
    String processId;
    String viewName;
    String mrpName;
    Integer interfaceId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("processId", processId)
                .append("viewName", viewName)
                .append("mrpName", mrpName)
                .append("interfaceId", interfaceId)
                .toString();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getMrpName() {
        return mrpName;
    }

    public void setMrpName(String mrpName) {
        this.mrpName = mrpName;
    }

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }
}
