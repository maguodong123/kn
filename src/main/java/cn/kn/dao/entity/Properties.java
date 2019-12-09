package cn.kn.dao.entity;

import java.io.Serializable;

/**
 * 属性名和属性ID
 */
public class Properties implements Serializable {
    private String propertiesName;
    private Integer propertiesID;

    @Override
    public String toString() {
        return "Properties{" +
                "propertiesName='" + propertiesName + '\'' +
                ", propertiesID=" + propertiesID +
                '}';
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public Integer getPropertiesID() {
        return propertiesID;
    }

    public void setPropertiesID(Integer propertiesID) {
        this.propertiesID = propertiesID;
    }
}
