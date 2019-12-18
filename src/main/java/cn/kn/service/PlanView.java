package cn.kn.service;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.Properties;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import cn.kn.utility.exceptionhandling.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 变更采购类型
 * 主要是解决12月6号康尼李品变更2700工厂错误的问题ChangePurchaseType
 * 上面的个我只是刷完了MRP视图和申请人视图,还有对应的属性。设下的6个视图和属性都没刷,现在我决定。缺少啥刷啥
 */
@RestController
public class PlanView {//补充
    private WorkflowConfigurationMapper wcm;
    private final Logger logger = LoggerFactory.getLogger(PlanView.class);

    public PlanView(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    private String processId = "正式2700工厂采购类型流程:2:46920304";

    @GetMapping(value = "planView")
    public void planView() {
        String factoryCode = "2700工厂";
        String[] view = {"维护工艺", "维护BOM"};
        String taskEvent;
        String viewName;
        logger.info("流程配置程序开始运行!");
        //第一步:查询出要配置的流程模型下的所有单据
        List<ProcessModel> processModels = wcm.getProcessModel("正式2700工厂采购类型流程");
        for (ProcessModel processModel : processModels) {
            try {
                int bill = processModel.getId();
                int rule = processModel.getDataRule();
                String[] ruleNames = processModel.getName().split("-2700工厂变更采购类型");
                //现在开始组合配置工厂
                for (String s : view) {
                    taskEvent = factoryCode + s;
                    viewName = ruleNames[0] + "视图";
                    //调用处理插入流程视图表的方法
                    //先获取对应视图的ID
                    Integer viewID = wcm.getDataViewMRP(viewName);
                    processingView(bill, rule, taskEvent, viewID);
                }
            } catch (Exception e) {
                logger.error("发现异常!已经捕获处理");
            }
        }
        logger.info("程序运行完毕!");
    }

    //处理流程视图,根据返回的数字确定情况
    private void processingView(Integer bill, Integer rule, String taskEvent, Integer viewID) {
        try {
            //先删除再插入
            if (taskEvent.equals("2700工厂维护工艺")) {
                wcm.deleteActReAuditViewEvent(taskEvent, viewID, bill, processId);
                wcm.insertActReAuditView(taskEvent, viewID, rule, bill, processId);
            }
            if (taskEvent.equals("2700工厂维护BOM")) {
                //这个节点有两个视图,类似与仓储视图
                wcm.deleteActReAuditViewEvent(taskEvent, viewID, bill, processId);
                wcm.insertActReAuditView(taskEvent, viewID, rule, bill, processId);
            }
        } catch (Exception e) {
            logger.error("发现异常!已经捕获处理");
        }
    }


}
