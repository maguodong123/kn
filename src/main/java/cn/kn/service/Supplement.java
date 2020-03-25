package cn.kn.service;


import cn.kn.dao.entity.AuditProps;
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
 * 上面的个我只是刷完了MRP视图和申请人视图,还有对应的属性。剩下的6个视图和属性都没刷,现在我决定。缺少啥刷啥
 */
@RestController
public class Supplement {//补充
    private WorkflowConfigurationMapper wcm;
    private final Logger logger = LoggerFactory.getLogger(Supplement.class);

    public Supplement(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    private String processId = "正式2700工厂采购类型流程:2:46920304";
    private Integer saleID;

    @GetMapping(value = "supplement")
    public void supplement() {
        String factoryCode = "2700工厂-";
        String[] view = {"工厂一般存储视图", "仓储视图", "销售视图", "采购视图", "会计成本视图", "质检视图"};
        String propName;
        String taskEvent;
        String viewName;
        AuditProps auditProps;
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
                    viewName = ruleNames[0] + "SAP" + taskEvent;
                    //调用处理插入流程视图表的方法
                    Integer viewID = processingView(bill, rule, taskEvent, viewName);
                    if (viewID == 1) {
                        continue;
                    }
                    //现在开始插入视图属性
                    //先删除
                    List<Integer> propCount = wcm.getActReAuditProps(taskEvent, viewID, bill, processId);
                    if (!propCount.isEmpty()) {
                        wcm.deleteActReAuditProps(viewID, bill, processId);
                    }
                    List<Properties> propertiesList = wcm.getProperties(viewID, bill, taskEvent, processId);

                    if (taskEvent.equals("2700工厂-工厂一般存储视图")) {
                        for (Properties properties : propertiesList) {
                            propName = properties.getPropertiesName();
                            if (propName.equals("库存确定组")) {
                                wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }
                    }

                    if (taskEvent.equals("2700工厂-仓储视图")) {
                        for (Properties properties : propertiesList) {
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }

                    }

                    if (taskEvent.equals("2700工厂-销售视图")) {
                        for (Properties properties : propertiesList) {
                            propName = properties.getPropertiesName();
                            if (propName.equals("销售组织") ||
                                    propName.equals("分销渠道") ||
                                    propName.equals("产品组") ||
                                    propName.equals("税类别") ||
                                    propName.equals("物料的税分类") ||
                                    propName.equals("项目类别组") ||
                                    propName.equals("运输组") ||
                                    propName.equals("装载组") ||
                                    propName.equals("可用性检查")) {
                                wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }
                    }

                    if (taskEvent.equals("2700工厂-采购视图")) {
                        for (Properties properties : propertiesList) {
                            propName = properties.getPropertiesName();
                            if (propName.equals("采购组")) {
                                wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }
                    }

                    if (taskEvent.equals("2700工厂-质检视图")) {
                        for (Properties properties : propertiesList) {
                            propName = properties.getPropertiesName();
                            if (propName.equals("检验类型") ||
                                    propName.equals("是否激活质检") ||
                                    propName.equals("是否自动质检")) {
                                wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }
                    }

                    if (taskEvent.equals("2700工厂-会计成本视图")) {
                        for (Properties properties : propertiesList) {
                            propName = properties.getPropertiesName();
                            if (propName.equals("评估分类") ||
                                    propName.equals("项目库存评估分类") ||
                                    propName.equals("价格确定方式") ||
                                    propName.equals("价格控制") ||
                                    propName.equals("用QS的成本估算") ||
                                    propName.equals("物料源") ||
                                    propName.equals("利润中心") ||
                                    propName.equals("成本核算批量")) {
                                wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(taskEvent, bill, viewID, properties.getPropertiesID(), 0, processId);
                        }
                        //这是插入单独的销售视图属性
                        auditProps = wcm.getAuditPropsSale(saleID, bill, viewName, processId);//1
                        wcm.insertActReAuditPropsStorageView(viewName, bill, saleID, auditProps.getPropId(), 1, processId);
                    }
                    //调用接口方法配置接口
                    insertFaceTaskEvent(taskEvent, bill);
                }
            } catch (Exception e) {
                logger.error("发现异常!已经捕获处理");
            }
        }
        logger.info("程序运行完毕!");
    }


    //这是配置接口的地方
    private void insertFaceTaskEvent(String taskEventName, Integer bill) {
        try {
            if (taskEventName.equals("2700工厂-工厂一般存储视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 347);
                return;
            }
            if (taskEventName.equals("2700工厂-仓储视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 346);
                return;
            }
            if (taskEventName.equals("2700工厂-销售视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 350);
                return;
            }
            if (taskEventName.equals("2700工厂-采购视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 349);
                return;
            }
            if (taskEventName.equals("2700工厂-会计成本视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 348);
                return;
            }
            if (taskEventName.equals("2700工厂-质检视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 349);
                return;
            }
            //如果都不是那就是MRP视图了
            wcm.insertActReAuditInterFaceTaskEvent(processId, bill, "2700工厂-MRP视图", 345);
        } catch (Exception e) {
            logger.error(ResultEnum.InsertError.getMsg());
        }
    }

    //处理流程视图,根据返回的数字确定情况
    private Integer processingView(Integer bill, Integer rule, String taskEvent, String viewName) {
        try {
            //先获取对应的视图ID
            Integer viewID = wcm.getDataViewMRP(viewName);
            //先删除再插入
            wcm.deleteActReAuditViewEvent(taskEvent, viewID, bill, processId);
            wcm.insertActReAuditView(taskEvent, viewID, rule, bill, processId);
            if (taskEvent.equals("2700工厂-销售视图")) {
                saleID = viewID;
            }
            if (taskEvent.equals("2700工厂-会计成本视图")) {
                //这个节点有两个视图,类似与仓储视图
                wcm.deleteActReAuditViewEvent(taskEvent, viewID, bill, processId);
                wcm.deleteActReAuditViewEvent(taskEvent, saleID, bill, processId);
                wcm.insertActReAuditView(taskEvent, viewID, rule, bill, processId);
                wcm.insertActReAuditView(taskEvent, saleID, rule, bill, processId);
            }
            return viewID;
        } catch (Exception e) {
            logger.error("发现异常!已经捕获处理");
        }
        return 1;
    }


}
