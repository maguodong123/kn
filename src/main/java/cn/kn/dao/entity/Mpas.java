package cn.kn.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/7/21 15:24
 * @Description
 */
public class Mpas {

    String name;
    String value;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("value", value)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
