package cn.kn.dao.entity;

public class ProcessModel extends BaseEntity{
    private Integer Id;
    private String SV;
    private Integer Enable;

    @Override
    public String toString() {
        return "ProcessModel{" +
                "Id=" + Id +
                ", SV='" + SV + '\'' +
                ", Enable=" + Enable +
                '}';
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getSV() {
        return SV;
    }

    public void setSV(String SV) {
        this.SV = SV;
    }

    public Integer getEnable() {
        return Enable;
    }

    public void setEnable(Integer enable) {
        Enable = enable;
    }
}
