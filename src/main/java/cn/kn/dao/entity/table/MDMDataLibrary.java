package cn.kn.dao.entity.table;

//对应MDM_DATALIBRAY编码表
public class MDMDataLibrary {
    private Integer codeID;
    private String code;
    private Integer dataID;//这个就是比如物料项目供应商之类的ID懂了吧
    private Integer task;
    private Integer dataRule;
    private Integer oldCode;//旧编码

    @Override
    public String toString() {
        return "MDMDataLibrary{" +
                "codeID=" + codeID +
                ", code='" + code + '\'' +
                ", dataID=" + dataID +
                ", task=" + task +
                ", dataRule=" + dataRule +
                ", oldCode=" + oldCode +
                '}';
    }

    public Integer getCodeID() {
        return codeID;
    }

    public void setCodeID(Integer codeID) {
        this.codeID = codeID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDataID() {
        return dataID;
    }

    public void setDataID(Integer dataID) {
        this.dataID = dataID;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getDataRule() {
        return dataRule;
    }

    public void setDataRule(Integer dataRule) {
        this.dataRule = dataRule;
    }

    public Integer getOldCode() {
        return oldCode;
    }

    public void setOldCode(Integer oldCode) {
        this.oldCode = oldCode;
    }
}
