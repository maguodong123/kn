package cn.kn.dao.mapper;

import cn.kn.dao.entity.SAP;
import cn.kn.dao.entity.TaskProperties;

import java.util.List;

public interface SelectViewMapper {

    void insterSAP(Integer ID, String OLDCODE,
                   String FACTORY, String MRPCG,
                   String MRPTS, String CG,
                   String JH, String CC,
                   String ZJ, String KJ);

    /*
    根据编码和视图名称作为条件查询出该视图的B_TASKPROPERTIES四项属性
    B_TASKPROPERTIES.id,B_TASKPROPERTIES.TASKBILL,B_TASKPROPERTIES.PROPERTIES,B_TASKPROPERTIES.PROPVALUE
     */
    List<TaskProperties> getTaskProperties(String code, String name);


    List<TaskProperties> getTaskPropertiesName(String code, String name, String propname);

    List<TaskProperties> getTaskPropertiesMRP(String code, String name, String cg, String ts);

    List<SAP> getSap(String oldcode, Long factory);

    Integer getTaskPropertiesID(Integer properties, Integer taskBill);

    void setProperties(String value, Integer properties, Integer taskBill);

    void setMdmPropType(String value, Integer prop, Integer taskProp);

    String getMdmPropTypeValue(Integer prop, Integer taskProp);

    List<SAP> getSap();
}
