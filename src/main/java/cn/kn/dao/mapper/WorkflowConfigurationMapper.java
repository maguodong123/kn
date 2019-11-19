package cn.kn.dao.mapper;

import cn.kn.dao.entity.AuditProps;
import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.ViewProp;

import java.util.List;

public interface WorkflowConfigurationMapper {

    List<ProcessModel> getProcessModel();

    List<ViewProp> getTaskEventName(Integer ruleID, Integer billID, String taskEventName, String viewName, String processID);

    List<Integer> getActReAuditView(String taskEventName, Integer viewID, Integer ruleID, Integer billID, String processID);

    void deleteActReAuditView(Integer viewID, Integer billID, String processID);

    List<Integer> getActReAuditProps(String taskEventName, Integer viewID, Integer billID, String processID);

    void deleteActReAuditProps(Integer viewID, Integer billID, String processID);

    Integer insertActReAuditView(String viewName, Integer viewID, Integer ruleID, Integer billID, String processID);

    List<AuditProps> getAuditProps(Integer viewID, Integer billID, String taskEventName, String processID);

    //插入对应的属性操作权限
    Integer insertActReAuditPropsStorageView(String viewName, Integer billID, Integer viewID, Integer propID, Integer isNotNull, String processID);

    //单独处理一条MRP的，这是仓储视图里面的，就配置一条字段
    AuditProps getAuditPropsMRP(Integer viewID, Integer billID, String taskEventName, String processID);

    //这张表主要是配置流程视图与接口的对接配置
    void insertActReAuditInterFaceTaskEvent(String processID, Integer billID, String taskEventName, Integer interfaceID);

}
