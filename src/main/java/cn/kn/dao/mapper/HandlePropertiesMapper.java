package cn.kn.dao.mapper;

import cn.kn.dao.entity.table.CodePropType;
import cn.kn.dao.entity.table.TaskProperties;
import cn.kn.dao.entity.table.ViewProps;

import java.util.List;

public interface HandlePropertiesMapper {

    //获取任务单属性表全部
    List<TaskProperties> getTaskPropertiesList(Integer properties, Integer taskBill);
    //单独获取任务单属性表的id
    Integer getTaskPropertiesID(Integer properties,Integer taskBill);
    //获取编码属性表全部
    List<CodePropType> getCodePropTypeList(Integer taskPropID);
    //获取编码属性表ID
    Integer getCodePropTypeID(Integer taskPropertiesID);
    //单条删除任务单属性表全部
    void deleteTaskProperties(Integer taskPropertiesID);
    //单条删除编码属性表全部d
    void deleteCodePropType(Integer taskPropertiesID);
    //根据三个精确条件关联查询出相对任务单的ID
    List<Integer> getRelationalQueryTaskPropertiesID(Integer taskBill,String dataName,String code);
    //修改任务单属性表value
    void updateTaskProperties(String value, Integer properties, Integer taskBill);
    //修改编码属性表value
    void updateCodePropType(String value, Integer prop, Integer taskProp);

    List<ViewProps> getViewProps(Integer taskBill,String viewName,String propName);



}