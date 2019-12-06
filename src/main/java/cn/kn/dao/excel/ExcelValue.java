package cn.kn.dao.excel;

import java.io.Serializable;

public class ExcelValue implements Serializable {
    private String taskBill;
    private String properties;
    private String value;

    @Override
    public String toString() {
        return "ExcelValue{" +
                "taskBill=" + taskBill +
                ", properties=" + properties +
                ", value='" + value + '\'' +
                '}';
    }

    public String getTaskBill() {
        return taskBill;
    }

    public void setTaskBill(String taskBill) {
        this.taskBill = taskBill;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
