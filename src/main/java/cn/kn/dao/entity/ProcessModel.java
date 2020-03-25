package cn.kn.dao.entity;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class ProcessModel implements Serializable {
    private Integer id;
    private String version;
    private String name;
    private Integer enable;
    private Integer dataRule;

    @Override
    public String toString() {
        return "ProcessModel{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                ", dataRule=" + dataRule +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public Integer getDataRule() {
        return dataRule;
    }

    public void setDataRule(Integer dataRule) {
        this.dataRule = dataRule;
    }
}
