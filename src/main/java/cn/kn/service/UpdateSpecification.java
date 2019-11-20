package cn.kn.service;


import cn.kn.dao.entity.Bill;
import cn.kn.dao.entity.DataRule;
import cn.kn.dao.mapper.WorkflowConfigurationMapper;
import cn.kn.utility.exceptionhandling.CustomException;
import cn.kn.utility.exceptionhandling.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这个类主要是更新2790扩充工厂之后造成得流程权限配置完成之后。唯独MRP视图不显示得问题
 */
@RestController
public class UpdateSpecification {
    private final Logger logger = LoggerFactory.getLogger(UpdateSpecification.class);
    private WorkflowConfigurationMapper wcm;

    public UpdateSpecification(WorkflowConfigurationMapper wcm) {
        this.wcm = wcm;
    }


    @GetMapping(value = "updateSpecification")
    public void updateSpecification() {
        logger.info("程序开始运行!");
        Bill bill;
        String viewName;
        //第一步:查出所有得模型id，去掉停用得
        List<DataRule> ruleIDs = wcm.getDataRule();
        for (DataRule ruleID : ruleIDs) {
            try {
                //第二步:根据模型id查询所有单据
                bill = wcm.getBill(ruleID.getId(), ruleID.getName());
                if (bill == null) {
                    continue;
                }
                int billID = bill.getBillID();
                //第三步:根据单据查询视图id,这里要做一次特殊处理。如果是2个或者2个以上得视图。要先查出到底是启用了哪个视图再决定
                viewName = bill.getBillName() + "SAP2790工厂-MRP视图";
                int integer = processingView(viewName, ruleID.getId(), billID);
                if (integer == 2) {
                    continue;
                }
                //第四步:先查存在先删除，在插入
                Integer integer1 = wcm.selectSpecification(ruleID.getId(), integer);
                if (integer1 != 0) {
                    wcm.deleteSpecification(ruleID.getId(), integer);
                }
                //第五都:根据上述条件插入新的规范,并测试
                String sql = "insert into B_STANDARDVERSION  values" +
                        " (1," + bill.getVersion() + ", STANDARDS(" + integer + ")," + ruleID.getId() + "," + integer + ",1)";
                wcm.insertSpecification(sql);
            } catch (Exception e) {
                logger.error("程序运行错误");
            }
        }
        logger.info("程序运行完毕!");
    }

    //需要对2个以上得视图进行一次特殊处理
    private Integer processingView(String viewName, Integer ruleID, Integer billID) {
        try {
            List<Integer> viewID = wcm.getViewID(viewName);
            if (viewID.isEmpty()) {
                throw new CustomException(ResultEnum.EmptySet);
            } else if (viewID.size() == 1) {
                return viewID.get(0);
            } else {
                for (Integer integer : viewID) {
                    int isHeck = wcm.getAuditViewIsHeck(integer, ruleID, billID);
                    if (isHeck == 1) {
                        return integer;
                    }
                }
            }
        } catch (CustomException e) {
            logger.error(ResultEnum.EmptySet.getMsg());
        }
        return 2;
    }


}
