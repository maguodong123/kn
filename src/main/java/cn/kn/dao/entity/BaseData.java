package cn.kn.dao.entity;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/5/27 15:29
 * @Description
 */
public class BaseData {

    private String id;
    private String superId;
    private String name;
    private String value;

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("superId", superId)
                .append("name", name)
                .append("value", value)
                .toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuperId() {
        return superId;
    }

    public void setSuperId(String superId) {
        this.superId = superId;
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
