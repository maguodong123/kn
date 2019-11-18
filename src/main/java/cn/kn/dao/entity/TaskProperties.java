package cn.kn.dao.entity;

import java.io.Serializable;

public class TaskProperties implements Serializable {

    private Integer ID;
    private Integer TASKBILL;
    private Integer PROPERTIES;
    private String PROPVALUE;
    private String NAME;

    @Override
    public String toString() {
        return "TaskProperties{" +
                "ID=" + ID +
                ", TASKBILL=" + TASKBILL +
                ", PROPERTIES=" + PROPERTIES +
                ", PROPVALUE='" + PROPVALUE + '\'' +
                ", NAME='" + NAME + '\'' +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTASKBILL() {
        return TASKBILL;
    }

    public void setTASKBILL(Integer TASKBILL) {
        this.TASKBILL = TASKBILL;
    }

    public Integer getPROPERTIES() {
        return PROPERTIES;
    }

    public void setPROPERTIES(Integer PROPERTIES) {
        this.PROPERTIES = PROPERTIES;
    }

    public String getPROPVALUE() {
        return PROPVALUE;
    }

    public void setPROPVALUE(String PROPVALUE) {
        this.PROPVALUE = PROPVALUE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
