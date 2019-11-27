package cn.kn.dao.excel;

import java.io.Serializable;

public class ExcelValue implements Serializable {
    private Integer taskBill;
    private Integer properties;
    private String value;

    @Override
    public String toString() {
        return "ExcelValue{" +
                "taskBill=" + taskBill +
                ", properties=" + properties +
                ", value='" + value + '\'' +
                '}';
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
