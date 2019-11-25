package cn.kn.dao.mapper;

import cn.kn.dao.entity.table.CodePropType;
import cn.kn.dao.entity.table.TaskProperties;

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
    //单条删除编码属性表全部
    void deleteCodePropType(Integer taskPropertiesID);



}