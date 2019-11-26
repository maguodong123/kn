package cn.kn.dao.excel;

import java.io.Serializable;

public class ExcelSAP implements Serializable {

    private Integer taskBill;
    private String dataName;
    private String code;

    @Override
    public String toString() {
        return "ExcelSAP{" +
                "taskBill=" + taskBill +
                ", dataName='" + dataName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public Integer getTaskBill() {
        return taskBill;
    }

    public void setTaskBill(Integer taskBill) {
        this.taskBill = taskBill;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
