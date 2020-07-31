package cn.kn.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/4/27 21:20
 * @Description
 */
public interface SHyongdaMapper {


    /**
     * 获取制造商id
     *
     * @param manufacturer 制造商
     * @return 制造商id
     */
    String getSuperId(String manufacturer);


    /**
     * 插入
     *
     * @param name      车系归类
     * @param value     车系归类编码
     * @param codevalue 级次
     * @param superid   父级id
     * @return 插入数据的id
     */
    void insertBaseData(@Param("name") String name,
                          @Param("value") String value,
                          @Param("codevalue") String codevalue,
                          @Param("superid") String superid);


    /**
     * 获取所有作废数据的任务单号
     *
     * @return
     */
    List<String> getWhole();

    /**
     * 根据任务单号获取编码属性
     *
     * @param taskId 任务单id
     * @return 属性id
     */
    String getCodeProp(String taskId);


    void deleteb_propcode(String taskId, String cr);

    void deleteL_DATALIBRAY2TASK(String taskId);

    void deleteL_DATALIBRAY2TASK1(String taskId);

    void deleteMDM_PROPTYPE(String taskId);

    void deleteMDM_VIEWCODE(String taskId);

    void deleteL_RUTASK2TASKBILL(String taskId);

    void deleteMDM_DATALIBRAY(String taskId);

    void deleteb_taskproperties(String taskId);

    void deleteb_taskbill(String taskId);


}
