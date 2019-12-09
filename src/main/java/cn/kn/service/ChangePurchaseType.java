package cn.kn.service;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.Properties;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 变更采购类型
 * 主要是解决12月6号康尼李品变更2700工厂错误的问题
 */
@RestController
public class ChangePurchaseType {

    private WorkflowConfigurationMapper wcm;
    private final Logger logger = LoggerFactory.getLogger(ChangePurchaseType.class);

    public ChangePurchaseType(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    @GetMapping(value = "changePurchaseType")
    public void changePurchaseType() {
        //先写一下思路和步骤
        logger.info("程序开始运行!");
        //第一步:查询出要配置的流程模型下的所有单据
        List<ProcessModel> processModels = wcm.getProcessModel("正式2700工厂采购类型流程");
        String ruleName;
        String propName;
        String ruleNameSAP;
        String factoryCode = "2700工厂-MRP视图";
        String processId = "正式2700工厂采购类型流程:2:46920304";
        //第二步:循环所有单据,并循环对所有单据做出配置处理
        for (ProcessModel processModel : processModels) {
            try {
                int bill = processModel.getId();
                int rule = processModel.getDataRule();
                String[] ruleNames = processModel.getName().split("-2700工厂变更采购类型");
                ruleName = ruleNames[0] + "视图";//截取的模型名字加视图两个字
                ruleNameSAP = ruleNames[0] + "SAP" + factoryCode;
                int viewID = wcm.getViewIDOne(ruleName);

                //查询MRP视图的ID
                int mrpID = wcm.getDataViewMRP(ruleNameSAP);
                wcm.deleteActReAuditView(mrpID, bill, processId);
                wcm.insertActReAuditView(factoryCode, mrpID, rule, bill, processId);

                //这里还要插入申请人的视图节点2张
                //这是那张基础单据加视图名字的
                wcm.deleteActReAuditViewOne("申请人", viewID, bill, processId);
                wcm.insertActReAuditViewOne("申请人", viewID, rule, bill, 0, processId);
                //这张插入申请人MRP视图
                wcm.deleteActReAuditViewOne("申请人", mrpID, bill, processId);
                wcm.insertActReAuditViewOne("申请人", mrpID, rule, bill, 1, processId);

                //开始循环配置MRP视图属性字段
                //第七步:流程视图属性表删除
                List<Integer> propCount;
                propCount = wcm.getActReAuditProps(factoryCode, mrpID, bill, processId);
                if (!propCount.isEmpty()) {
                    wcm.deleteActReAuditProps(mrpID, bill, processId);
                }
                List<Properties> propertiesList = wcm.getProperties(mrpID, bill, factoryCode, processId);
                for (Properties properties : propertiesList) {
                    propName = properties.getPropertiesName();
                    if (propName.equals("采购类型") || propName.equals("特殊采购类")) {
                        //插入的是流程属性视图
                        //现在开始插入申请人的权限,2个一个模型视图和MRP视图
                        wcm.deleteActReAuditPropsAndTaskEvent("申请人", mrpID, bill, properties.getPropertiesID(), processId);
                        if (propName.equals("采购类型")) {
                            wcm.insertActReAuditPropsStorageView("申请人", bill, mrpID, properties.getPropertiesID(), 1, processId);
                            continue;
                        }
                        wcm.insertActReAuditPropsStorageView("申请人", bill, mrpID, properties.getPropertiesID(), 0, processId);
                        continue;
                    }
                    if (propName.equals("MRP类型") ||
                            propName.equals("MRP控制者") ||
                            propName.equals("批量大小") ||
                            propName.equals("期间标识") ||
                            propName.equals("浮动的计划边际码") ||
                            propName.equals("可用性检查的检查组") ||
                            propName.equals("对于独立和集中需求的相关需求标识")) {
                        wcm.insertActReAuditPropsStorageView(factoryCode, bill, mrpID, properties.getPropertiesID(), 1, processId);
                        continue;
                    }
                    wcm.insertActReAuditPropsStorageView(factoryCode, bill, mrpID, properties.getPropertiesID(), 0, processId);
                }
            } catch (Exception e) {
                logger.error("发现异常!已经捕获处理");
            }
        }
        logger.info("程序运行完毕!");
    }


}
