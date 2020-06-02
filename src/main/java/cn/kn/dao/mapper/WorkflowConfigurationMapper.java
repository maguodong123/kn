package cn.kn.dao.mapper;

import cn.kn.dao.entity.*;

import java.util.List;

/**
 * @author Administrator
 */
public interface WorkflowConfigurationMapper {


    /**
     * 这是查询ACT_RE_MODEL这张表里面的所有流程,然后存储到一个集合中,传入查询的流程名字
     * @param processName 流程的名字:流程管理→流程模型→名称
     * @return ProcessModel
     */
    List<ProcessModel> getProcessModel(String processName);

    /**
     * 获取属性名称和属性ID
     * @param viewID
     * @param billID
     * @param taskEventName
     * @param processID
     * @return
     */
    List<Properties> getProperties(Integer viewID, Integer billID, String taskEventName, String processID);

    //删除流程属性,这次要查询的属性多一个
    void deleteActReAuditPropsAndTaskEvent(String taskEventName, Integer viewID, Integer billID, Integer propID, String processID);

    void deleteActReAuditView(Integer viewID, Integer billID, String processID);

    void deleteActReAuditViewEvent(String taskEventName,Integer viewID, Integer billID, String processID);

    //删除流程视图,详细的属性查询
    void deleteActReAuditViewOne(String taskEventName, Integer viewID, Integer billID, String processID);

    void deleteActReAuditProps(Integer viewID, Integer billID, String processID);

    //配置流程视图与接口的对接配置在插入之前先删除,避免重复
    void deleteActReAuditInterFaceTaskEvent(String processID, Integer billID, String taskEventName);



    List<ViewProp> getTaskEventName(Integer ruleID, Integer billID, String taskEventName, String viewName, String processID);

    List<Integer> getActReAuditView(String taskEventName, Integer viewID, Integer ruleID, Integer billID, String processID);

    List<Integer> getActReAuditProps(String taskEventName, Integer viewID, Integer billID, String processID);

    List<AuditProps> getAuditProps(Integer viewID, Integer billID, String taskEventName, String processID);



    //插入act_re_auditview表
    Integer insertActReAuditView(String viewName, Integer viewID, Integer ruleID, Integer billID, String processID);

    //插入act_re_auditview表单独的申请人SQL
    Integer insertActReAuditViewOne(String viewName, Integer viewID, Integer ruleID, Integer billID, Integer isNotNull, String processID);

    //插入对应的属性操作权限
    Integer insertActReAuditPropsStorageView(String taskEvent, Integer billID, Integer viewID, Integer propID, Integer isNotNull, String processID);

    //单独处理一条MRP的，这是仓储视图里面的，就配置一条字段
    AuditProps getAuditPropsMRP(Integer viewID, Integer billID, String taskEventName, String processID);

    //单独处理一条销售视图的，这是会计成本视图里面的，就配置一条字段
    AuditProps getAuditPropsSale(Integer viewID, Integer billID, String taskEventName, String processID);

    //这张表主要是配置流程视图与接口的对接配置
    void insertActReAuditInterFaceTaskEvent(String processID, Integer billID, String taskEventName, Integer interfaceID);

    //插入更新规范,2790扩充工厂临时加了一张mrp视图
    void insertSpecification(String sql);


    List<String> getLists();

    Integer selectSpecification(Integer ruleID, Integer viewID);

    void deleteSpecification(Integer ruleID, Integer viewID);

    //获取所有已经启用得模型id
    List<DataRule> getDataRule();

    //根据单据视图名获取视图id集合
    List<Integer> getViewID(String viewName);

    //根据单据视图名获取视图id单个
    Integer getViewIDOne(String viewName);

    //根据一系例得条件获得该视图得判断条件
    Integer getAuditViewIsHeck(Integer viewID, Integer ruleID, Integer billID);

    Bill getBill(Integer ruleID, String ruleName);

    //获取MRP视图ID,条件就是NAME全名
    Integer getDataViewMRP(String name);



}
