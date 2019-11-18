/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.kn.dao.entity;

import java.io.Serializable;

/**
 *
 * @author liqingfeng
 */
public class AuditProps implements Serializable {

    private String billId;//单据ID
    private String taskEventName;//节点ID
    private String viewId;// 视图ID
    private String isnotnull;//是否必填
    private String ishave;//操作权限
    private String propName;
    private Integer propId;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getTaskEventName() {
        return taskEventName;
    }

    public void setTaskEventName(String taskEventName) {
        this.taskEventName = taskEventName;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getIsnotnull() {
        return isnotnull;
    }

    public void setIsnotnull(String isnotnull) {
        this.isnotnull = isnotnull;
    }

    public String getIshave() {
        return ishave;
    }

    public void setIshave(String ishave) {
        this.ishave = ishave;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Integer getPropId() {
        return propId;
    }

    public void setPropId(Integer propId) {
        this.propId = propId;
    }
}
