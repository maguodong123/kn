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

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/3/23 13:09
 * @Description 背景：
 * 2020年康尼扩充广州工厂，分别是2800，和2890.其中2800复制2100工厂。视图最多11个。2890复制2790就行。因为有过程序
 * 这里就不再多说
 * 为了优化程序加快性能，我决定在这里优化一下程序。减少步骤。首先换成switch减少逻辑判断。看看能否把删除的sql一次性删除完
 */
@RestController
public class CompleteViewSteam {
    private WorkflowConfigurationMapper wcm;
    private final Logger logger = LoggerFactory.getLogger(Supplement.class);

    public CompleteViewSteam(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    private String processId = "正式-2800工厂流程:1:49255408";
    private String factoryCode = "2800工厂-";

    private int mrpViewId;


    @GetMapping(value = "completeViewSteam")
    public void completeViewSteam() {
        logger.info("流程配置程序开始运行!");
        String[] view = {"工厂一般存储视图", "仓储视图", "销售视图", "质检视图", "采购视图",
                "会计成本视图", "工作计划视图", "MRP视图", "MRP工艺", "维护BOM", "维护工艺"};
        //单据流程中的工厂视图名字
        String viewName;
        //属性名字
        String propName;
        //B_DATAVIEW表中的SAP视图名字，配置接口用的
        String dataViewName;
        List<AuditProps> auditProps;
        AuditProps auditProps1;

        try {
            //第一步:查询出要配置的流程模型下的所有单据
            List<ProcessModel> processModels = wcm.getProcessModel("正式物资编码流程");
            //第二步:循环所有单据,并循环对所有单据做出配置处理
            for (ProcessModel processModel : processModels) {
                int bill = processModel.getId();
                int rule = processModel.getDataRule();

                //第三步开始组合流程视图
                for (String factoryView : view) {
                    viewName = factoryCode.concat(factoryView);
                    dataViewName = processModel.getName().concat("SAP").concat(factoryCode).concat(factoryView);

                    //第四步:根据详细的条件查询出对应工厂下的视图并存入一个list集合
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

                    //第八步:插入属性,因为对应的视图属性不同所以需要单独进行判断.这里采用switch
                    switch (viewName) {
                        case "2800工厂-工厂一般存储视图":
                            auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                            if (auditProps.size() != 5) {
                                throw new CustomException(ResultEnum.QuantityError);
                            }
                            for (AuditProps auditProp : auditProps) {
                                propName = auditProp.getPropName();
                                if ("库存确定组".equals(propName)) {
                                    wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 1, processId);
                                    continue;
                                }
                                wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                            }
                            break;
                        case "2800工厂-仓储视图":
                            auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                            if (auditProps.size() != 2) {
                                throw new CustomException(ResultEnum.QuantityError);
                            }
                            for (AuditProps auditProp : auditProps) {
                                wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                            }
                            //仓储视图里面要维护MRP视图的一个字段，所以要单独插入一条属性
                            auditProps1 = wcm.getAuditPropsMRP(mrpViewId, bill, viewName, processId);
                            wcm.insertActReAuditPropsStorageView(viewName, bill, mrpViewId, auditProps1.getPropId(), 0, processId);
                            break;
                        case "2800工厂-销售视图":
                            break;
                        case "2800工厂-质检视图":
                            break;
                        case "2800工厂-采购视图":
                            break;
                        case "2800工厂-会计成本视图":
                            break;
                        case "2800工厂-工作计划视图":
                            break;
                        case "2800工厂-MRP视图":
                            auditProps = wcm.getAuditProps(viewId, bill, viewName, processId);
                            if (auditProps.size() != 35) {
                                throw new CustomException(ResultEnum.QuantityError);
                            }
                            for (AuditProps auditProp : auditProps) {
                                propName = auditProp.getPropName();
                                if ("MRP类型".equals(propName) ||
                                        "MRP控制者".equals(propName) ||
                                        "批量大小".equals(propName) ||
                                        "安全库存".equals(propName) ||
                                        "期间标识".equals(propName) ||
                                        "采购类型".equals(propName) ||
                                        "浮动的计划边际码".equals(propName) ||
                                        "可用性检查的检查组".equals(propName) ||
                                        "对于独立和集中需求的相关需求标识".equals(propName)) {
                                    wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 1, processId);
                                    continue;
                                }
                                wcm.insertActReAuditPropsStorageView(viewName, bill, viewId, auditProp.getPropId(), 0, processId);
                            }
                            break;
                        default:
                            break;
                    }
                    insertFaceTaskEvent(viewName, bill);
                }
            }
        } catch (Exception e) {
            logger.error("发现异常!已经捕获处理");
        }

    }


    /**
     * 这是配置接口的地方
     *
     * @param viewName 流程中的工厂视图名
     * @param bill     单据
     */
    private void insertFaceTaskEvent(String viewName, Integer bill) {
        switch (viewName) {
            case "2800工厂-MRP视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 394);
                break;
            case "2800工厂-仓储视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 395);
                break;
            case "2800工厂-会计成本视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 396);
                break;
            case "2800工厂-工作计划视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 397);
                break;
            case "2800工厂-工厂一般存储视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 398);
                break;
            case "2800工厂-质检视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 400);
                break;
            case "2800工厂-采购视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 401);
                break;
            case "2800工厂-销售视图":
                wcm.insertActReAuditInterFaceTaskEvent(processId, bill, viewName, 402);
                break;
            default:
                break;
        }
    }


    /**
     * 查询SAP视图的ID
     *
     * @param rule         模型ID
     * @param bill         单据ID
     * @param viewName     流程终的视图名字
     * @param dataViewName 拼接好的单据名视图名字
     * @return 视图ID或者代号
     */
    private Integer processingView(Integer rule, Integer bill, String viewName, String dataViewName) {
        try {
            List<ViewProp> viewProp = wcm.getTaskEventName(rule, bill, viewName, dataViewName, processId);
            if (viewProp.isEmpty()) {
                return 1;
            }
            int viewId = viewProp.get(0).getViewID();
            int ruleId = viewProp.get(0).getRuleID();
            String mrpView = "MRP视图";
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
            if (viewName.equals((factoryCode.concat("仓储视图")))) {
                wcm.insertActReAuditView(viewName, mrpViewId, ruleId, bill, processId);
            }
            return viewId;
        } catch (Exception e) {
            logger.error("系统程序运行异常!");
        }
        return 2;
    }


}
