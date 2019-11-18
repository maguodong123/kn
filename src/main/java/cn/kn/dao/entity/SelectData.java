package cn.kn.dao.entity;

import java.io.Serializable;

public class SelectData implements Serializable {

    private String code;//编码
    private Integer viewid;//视图id
    private String viewname;//视图名称
    private String propertiessname;//属性字段字段名称
    private Integer propertiesid;//属性字段id
    private Integer count;//重复的次数

    @Override
    public String toString() {
        return "SelectData{" +
                "code='" + code + '\'' +
                ", viewid=" + viewid +
                ", viewname='" + viewname + '\'' +
                ", propertiessname='" + propertiessname + '\'' +
                ", propertiesid=" + propertiesid +
                ", count=" + count +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getViewid() {
        return viewid;
    }

    public void setViewid(Integer viewid) {
        this.viewid = viewid;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public String getPropertiessname() {
        return propertiessname;
    }

    public void setPropertiessname(String propertiessname) {
        this.propertiessname = propertiessname;
    }

    public Integer getPropertiesid() {
        return propertiesid;
    }

    public void setPropertiesid(Integer propertiesid) {
        this.propertiesid = propertiesid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
