package cn.kn.dao.entity;

import java.io.Serializable;

abstract class BaseEntity implements Serializable {
    private String code;
    private String value;
    private String name;
    private Integer viewID;
    private Integer billID;
    private Integer ruleID;
    private Integer taskID;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", viewID=" + viewID +
                ", billID=" + billID +
                ", ruleID=" + ruleID +
                ", taskID=" + taskID +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getViewID() {
        return viewID;
    }

    public void setViewID(Integer viewID) {
        this.viewID = viewID;
    }

    public Integer getBillID() {
        return billID;
    }

    public void setBillID(Integer billID) {
        this.billID = billID;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }
}
