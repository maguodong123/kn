package cn.kn.dao.excel;

import java.io.Serializable;

public abstract class ExcelMap implements Serializable {
    private Integer key;
    private Integer value;
    private String factory;
    private String code;
    private String viewName;

    @Override
    public String toString() {
        return "ExcelMap{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", factory='" + factory + '\'' +
                ", code='" + code + '\'' +
                ", viewName='" + viewName + '\'' +
                '}';
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
