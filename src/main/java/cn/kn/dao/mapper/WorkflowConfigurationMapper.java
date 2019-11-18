package cn.kn.dao.mapper;

import cn.kn.dao.entity.AuditProps;
import cn.kn.dao.entity.ProcessModel;
import cn.kn.dao.entity.ViewProp;

import java.util.List;

public interface WorkflowConfigurationMapper {

    List<ProcessModel> getProcessModel();

    List<ViewProp> getTaskEventName(Integer Rule, Integer Bill, String TaskEventNAME, String ViewName, String processId);

    List<Integer> getActReAuditView(String TaskEventNAME,Integer View,Integer Rule,Integer Bill,String processId);

    void deleteActReAuditView(Integer View,Integer Bill,String processId);

    List<Integer> getActReAuditProps(String TaskEventNAME,Integer View,Integer Bill,String processId);

    void deleteActReAuditProps(Integer View,Integer Bill,String processId);

    Integer insertActReAuditView(String ViewName,Integer View,Integer Rule,Integer Bill,String processId);

    List<AuditProps> getAuditProps(Integer View, Integer Bill, String TaskEventNAME,String processId);

    //插入对应的属性操作权限
    Integer insertActReAuditPropsStorageView(String ViewName,Integer Bill,Integer View,Integer propId,Integer IsNotNull,String processId);

    //单独处理一条MRP的，这是仓储视图里面的，就配置一条字段
    AuditProps getAuditPropsMRP(Integer View,Integer Bill,String TaskEventNAME,String processId);
}
