package cn.kn.service;

import cn.kn.dao.entity.AuditProps;
import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.ViewProp;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import cn.kn.utility.exceptionhandling.CustomException;
import cn.kn.utility.exceptionhandling.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 扩充工厂口配置单据流程的程序。
 * 第一个版本是把2260个单据全部刷上权限:已实现
 * 第二个版本是完成流程后传送接口:已实现
 * 最后一个版本解决多视图的问题:待议
 */
@RestController
public class WorkflowConfigurationService {

    private WorkflowConfigurationMapper wcm;

    public WorkflowConfigurationService(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    private final Logger logger = LoggerFactory.getLogger(WorkflowConfigurationService.class);
    private int mrpViewId;
    private String factoryCode = "2790工厂-";
    private String processId = "正式-2790工厂流程:2:48168447";
    private String mrpView = "MRP视图";

    @GetMapping(value = "workflowConfiguration")
    public void workflowConfiguration() {//138404  ==>14万条SQL，6小时左右
        logger.info("流程配置程序开始运行!");
        List<AuditProps> auditProps;
        AuditProps auditProps1;
        String sortView = "工厂一般存储视图";
        String storageView = "仓储视图";
        List<String> factory = new ArrayList<>();
        factory.add(sortView);
        factory.add(mrpView);
        factory.add(storageView);
        String viewName;
        String propName;
        String dataViewName;

        //第一步:查询出要配置的流程模型下的所有单据
        List<ProcessModel> processModels = wcm.getProcessModel("正式物资编码流程");

        //第二步:循环所有单据,并循环对所有单据做出配置处理
        for (ProcessModel processModel : processModels) {
            int bill = processModel.getId();
            int rule = processModel.getDataRule();

            //第三步:每个单据要配置三个工厂,循环三个工厂
            for (String s : factory) {
                viewName = factoryCode.concat(s);
                dataViewName = processModel.getName() + "SAP" + factoryCode + s;

                int viewId = processingView(rule, bill, viewName, dataViewName);
                if (viewId == 1 || viewId == 2) {
                    continue;
                }

                //第七步:流程视图属性表删除
                List<Integer> propCount;
                propCount = wcm.getActReAuditProps(viewName, viewId, bill, processId);
                if (!propCount.isEmpty()) {
                    wcm.deleteActReAuditProps(viewId, bill, processId);
                }

                //第八步:插入属性,因为对应的视图属性不同所以需要单独进行判断
                if ((factoryCode + "工厂一般存储视图").equals(viewName)) {//5
                    try {
                        auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                        if (auditProps.size() != 5) {
                            throw new CustomException(ResultEnum.QuantityError);
                        }
                        for (AuditProps auditProp : auditProps) {
                            propName = auditProp.getPropName();
                            if (propName.equals("库存确定组")) {
                                wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                        }
                    } catch (Exception e) {
                        logger.error(ResultEnum.QuantityError.getMsg());
                    }
                }

                if ((factoryCode + mrpView).equals(viewName)) {//35
                    try {
                        auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                        if (auditProps.size() != 35) {
                            throw new CustomException(ResultEnum.QuantityError);
                        }
                        for (AuditProps auditProp : auditProps) {
                            propName = auditProp.getPropName();
                            if (propName.equals("MRP类型") ||
                                    propName.equals("MRP控制者") ||
                                    propName.equals("批量大小") ||
                                    propName.equals("安全库存") ||
                                    propName.equals("期间标识") ||
                                    propName.equals("采购类型") ||
                                    propName.equals("浮动的计划边际码") ||
                                    propName.equals("可用性检查的检查组") ||
                                    propName.equals("对于独立和集中需求的相关需求标识")) {
                                wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 1, processId);
                                continue;
                            }
                            wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                        }
                    } catch (Exception e) {
                        logger.error(ResultEnum.QuantityError.getMsg());
                    }
                }

                if ((factoryCode + "仓储视图").equals(viewName)) {//2
                    try {
                        auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                        if (auditProps.size() != 2) {
                            throw new CustomException(ResultEnum.QuantityError);
                        }
                        for (AuditProps auditProp : auditProps) {
                            wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                        }
                        auditProps1 = wcm.getAuditPropsMRP(mrpViewId, bill, viewName, processId);//1
                        wcm.insertActReAuditPropsStorageView(viewName, bill, mrpViewId, auditProps1.getPropId(), 0, processId);
                    } catch (Exception e) {
                        logger.error(ResultEnum.QuantityError.getMsg());
                    }
                }
                insertFaceTaskEvent(viewName, bill);
            }
        }
        logger.info("程序运行完毕!");
    }

    private void insertFaceTaskEvent(String viewName, Integer bill) {
        try {
            if (viewName.equals("2790工厂-MRP视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 382);
            }
            if (viewName.equals("2790工厂-仓储视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 363);
            }
            if (viewName.equals("2790工厂-工厂一般存储视图")) {
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 362);
            }
        } catch (Exception e) {
            logger.error(ResultEnum.InsertError.getMsg());
        }
    }

    private Integer processingView(Integer rule, Integer bill, String viewName, String dataViewName) {
        //第四步:根据详细的条件查询出对应工厂下的视图并存入一个list集合
        try {
            List<ViewProp> viewProp = wcm.getTaskEventName(rule, bill, viewName, dataViewName, processId);
            if (viewProp.isEmpty()) {
                return 1;
            }
            int viewId = viewProp.get(0).getViewID();
            int ruleId = viewProp.get(0).getRuleID();
            if (viewName.equals((factoryCode + mrpView))) {
                mrpViewId = viewProp.get(0).getViewID();
            }
            //第六步:流程视图表ActReAuditView,先查是否存在。存在循环删，不存在直接插入
            List<Integer> viewCount;
            viewCount = wcm.getActReAuditView(viewName, viewId, ruleId, bill, processId);
            if (!viewCount.isEmpty()) {
                wcm.deleteActReAuditView(viewId, bill, processId);
            }
            wcm.insertActReAuditView(viewName, viewId, ruleId, bill, processId);
            if (viewName.equals((factoryCode + "仓储视图"))) {
                wcm.insertActReAuditView(viewName, mrpViewId, ruleId, bill, processId);
            }
            return viewId;
        } catch (Exception e) {
            logger.error("系统程序运行异常!");
        }
        return 2;
    }

}
