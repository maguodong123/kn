package cn.kn.dao.entity;

public class Bill extends BaseEntity{
    private String billName;
    private String ruleName;
    private Integer enable;//开关,1启用,0停用
    private Integer version;

    @Override
    public String toString() {
        return "Bill{" +
                "billName='" + billName + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", enable=" + enable +
                ", version=" + version +
                '}';
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
