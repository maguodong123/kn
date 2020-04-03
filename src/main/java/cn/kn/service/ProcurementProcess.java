package cn.kn.service;

import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import cn.kn.utility.exceptionhandling.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/3/25 16:39
 * @Description 专门用来刷接口采购类型的
 */
@RestController
public class ProcurementProcess {
    private WorkflowConfigurationMapper wcm;
    private final Logger logger = LoggerFactory.getLogger(Supplement.class);
    private String processId = "正式2800工厂采购类型流程:1:55396904";


    public ProcurementProcess(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }

    @GetMapping(value = "procurementProcess")
    public void procurementProcess() {
        String factoryCode = "2800工厂-";
        String[] view = {"工厂一般存储视图", "仓储视图", "销售视图", "质检视图", "采购视图",
                "会计成本视图", "工作计划视图", "MRP视图"};
        logger.info("流程配置程序开始运行!");
        String taskEvent;
        List<ProcessModel> processModels = wcm.getProcessModel("正式2800工厂采购类型流程");
        for (ProcessModel processModel : processModels) {
            int bill = processModel.getId();
            //现在开始组合配置工厂
            for (String s : view) {
                taskEvent = factoryCode + s;
                insertFaceTaskEvent(taskEvent, bill);
            }
        }
        logger.info("程序运行完毕!");
    }


    /**
     * 这是配置接口的地方
     *
     * @param taskEventName 工厂+视图名
     * @param bill          任务单id
     */
    private void insertFaceTaskEvent(String taskEventName, Integer bill) {
        try {
            switch (taskEventName) {
                case "2800工厂-MRP视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 391);
                    break;
                case "2800工厂-仓储视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 392);
                    break;
                case "2800工厂-会计成本视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 393);
                    break;
                case "2800工厂-工作计划视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 394);
                    break;
                case "2800工厂-工厂一般存储视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 395);
                    break;
                case "2800工厂-质检视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 397);
                    break;
                case "2800工厂-销售视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 399);
                    break;
                case "2800工厂-采购视图":
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 398);
                    wcm.insertActReAuditInterFaceTaskEvent(processId, bill, taskEventName, 391);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(ResultEnum.InsertError.getMsg());
        }
    }


}
