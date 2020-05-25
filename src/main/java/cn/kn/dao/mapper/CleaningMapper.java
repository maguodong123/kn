package cn.kn.dao.mapper;

import cn.kn.dao.entity.table.TaskProperties;

import java.util.List;

//编码与旧编码的映射
public interface CleaningMapper {
    //查询当前编码的最新任务单
    Integer getCodeDataLibraryTask(String code);
    //根据任务单,和要查找的属性字段返回结果
    List<TaskProperties> getTaskPropertiesList(Integer task,String propertiesName);
    //查询编码表的ID
    Integer getMDMDataLibraryID(String code);
    //修改b_oldcode表的MDL字段
    void setOldCode(Integer codeID,String oldCode);
    //修改编码关联表
    void setCodeCorrelation(Integer codeID,String oldCode);

    //单独查询2700特殊采购类的
    Integer getPropID(Integer task);

}
