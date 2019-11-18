package cn.kn.dao.entity;

import java.io.Serializable;

abstract class BaseEntity implements Serializable {
    private String Code;
    private String Value;
    private String Name;
    private Integer View;
    private Integer Bill;
    private Integer Rule;
    private Integer Task;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "Code='" + Code + '\'' +
                ", Value='" + Value + '\'' +
                ", Name='" + Name + '\'' +
                ", View=" + View +
                ", Bill=" + Bill +
                ", Rule=" + Rule +
                ", Task=" + Task +
                '}';
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getView() {
        return View;
    }

    public void setView(Integer view) {
        View = view;
    }

    public Integer getBill() {
        return Bill;
    }

    public void setBill(Integer bill) {
        Bill = bill;
    }

    public Integer getRule() {
        return Rule;
    }

    public void setRule(Integer rule) {
        Rule = rule;
    }

    public Integer getTask() {
        return Task;
    }

    public void setTask(Integer task) {
        Task = task;
    }
}
