package cn.kn.dao.entity;

import java.io.Serializable;

public class DataRule implements Serializable {
    private Integer id;
    private String name;
    private Integer enable;

    @Override
    public String toString() {
        return "DataRule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
