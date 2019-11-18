package cn.kn.dao.entity;

public class ViewProp extends BaseEntity{
    private String ViewName;
    private Integer ID;
    private String PropName;
    private Integer PropId;

    @Override
    public String toString() {
        return "ViewProp{" +
                "ViewName='" + ViewName + '\'' +
                ", ID=" + ID +
                ", PropName='" + PropName + '\'' +
                ", PropId=" + PropId +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public String getViewName() {
        return ViewName;
    }

    public void setViewName(String viewName) {
        ViewName = viewName;
    }

    public String getPropName() {
        return PropName;
    }

    public void setPropName(String propName) {
        PropName = propName;
    }

    public Integer getPropId() {
        return PropId;
    }

    public void setPropId(Integer propId) {
        PropId = propId;
    }
}
