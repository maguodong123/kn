package cn.kn.dao.entity.table;

import java.io.Serializable;
//对应B_Taskproperties这张表，顺便加上了两个关联的字段.这是哪个很常用的SQL关联查出来的
public class TaskProperties implements Serializable {
    private Integer taskPropertiesID;
    private Integer taskBill;
    private Integer properties;
    private String propValue;
    private Integer propGroup;
    private String baseDataName;
    private String propertiesName;
    private String dataViewName;

    @Override
    public String toString() {
        return "TaskProperties{" +
                "taskPropertiesID=" + taskPropertiesID +
                ", taskBill=" + taskBill +
                ", properties=" + properties +
                ", propValue='" + propValue + '\'' +
                ", propGroup=" + propGroup +
                ", baseDataName='" + baseDataName + '\'' +
                ", propertiesName='" + propertiesName + '\'' +
                ", dataViewName='" + dataViewName + '\'' +
                '}';
    }

    public Integer getTaskPropertiesID() {
        return taskPropertiesID;
    }

    public void setTaskPropertiesID(Integer taskPropertiesID) {
        this.taskPropertiesID = taskPropertiesID;
    }

    public Integer getTaskBill() {
        return taskBill;
    }

    public void setTaskBill(Integer taskBill) {
        this.taskBill = taskBill;
    }

    public Integer getProperties() {
        return properties;
    }

    public void setProperties(Integer properties) {
        this.properties = properties;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public Integer getPropGroup() {
        return propGroup;
    }

    public void setPropGroup(Integer propGroup) {
        this.propGroup = propGroup;
    }

    public String getBaseDataName() {
        return baseDataName;
    }

    public void setBaseDataName(String baseDataName) {
        this.baseDataName = baseDataName;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getDataViewName() {
        return dataViewName;
    }

    public void setDataViewName(String dataViewName) {
        this.dataViewName = dataViewName;
    }
}
