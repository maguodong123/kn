package cn.kn.dao.entity.table;

import java.io.Serializable;

public class CodePropType implements Serializable {
    private Integer codePropTypeID;
    private Integer prop;
    private String value;
    private Integer codeID;
    private Integer taskProp;

    @Override
    public String toString() {
        return "CodePropType{" +
                "codePropTypeID=" + codePropTypeID +
                ", prop=" + prop +
                ", value='" + value + '\'' +
                ", codeID=" + codeID +
                ", taskProp=" + taskProp +
                '}';
    }

    public Integer getCodePropTypeID() {
        return codePropTypeID;
    }

    public void setCodePropTypeID(Integer codePropTypeID) {
        this.codePropTypeID = codePropTypeID;
    }

    public Integer getProp() {
        return prop;
    }

    public void setProp(Integer prop) {
        this.prop = prop;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCodeID() {
        return codeID;
    }

    public void setCodeID(Integer codeID) {
        this.codeID = codeID;
    }

    public Integer getTaskProp() {
        return taskProp;
    }

    public void setTaskProp(Integer taskProp) {
        this.taskProp = taskProp;
    }
}
