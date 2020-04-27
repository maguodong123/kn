package cn.kn.dao.mapper;

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/4/27 21:20
 * @Description
 */
public interface SHyongdaMapper {

    /**
     * 获取所有作废数据的任务单号
     * @return
     */
    List<String> getWhole();

    /**
     * 根据任务单号获取编码属性
     * @param taskId 任务单id
     * @return 属性id
     */
    String getCodeProp(String taskId);


    void deleteb_propcode(String taskId,String cr);
    void deleteL_DATALIBRAY2TASK(String taskId);
    void deleteL_DATALIBRAY2TASK1(String taskId);
    void deleteMDM_PROPTYPE(String taskId);
    void deleteMDM_VIEWCODE(String taskId);
    void deleteL_RUTASK2TASKBILL(String taskId);
    void deleteMDM_DATALIBRAY(String taskId);
    void deleteb_taskproperties(String taskId);
    void deleteb_taskbill(String taskId);


}
