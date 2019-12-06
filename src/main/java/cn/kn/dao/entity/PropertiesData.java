package cn.kn.dao.entity;


import java.io.Serializable;

/**
 *根据属性字段propertiesid和任务单id,查询出重复字段的详细信息
 *重复字段的id,单据id,属性id,propvalue重复的字段值
 *重复的字段名称,视图id,视图名称
 *好与查询出来的做对比
 */
public class PropertiesData implements Serializable {
    private Integer taskprid;//重复字段的id,删除用这个作为条件
    private Integer taskprbill;//单据id
    private Integer taskprties;//属性id
    private String taskprvalue;//propvalue重复的字段值
    private String propname;//重复的字段名称
    private Integer lviewdataview;//视图id
    private String dataname;//视图名称

    @Override
    public String toString() {
        return "PropertiesData{" +
                "taskprid=" + taskprid +
                ", taskprbill=" + taskprbill +
                ", taskprties=" + taskprties +
                ", taskprvalue='" + taskprvalue + '\'' +
                ", propname='" + propname + '\'' +
                ", lviewdataview=" + lviewdataview +
                ", dataname='" + dataname + '\'' +
                '}';
    }

    public Integer getTaskprid() {
        return taskprid;
    }

    public Integer getTaskprbill() {
        return taskprbill;
    }

    public Integer getTaskprties() {
        return taskprties;
    }

    public String getTaskprvalue() {
        return taskprvalue;
    }

    public String getPropname() {
        return propname;
    }

    public Integer getLviewdataview() {
        return lviewdataview;
    }

    public String getDataname() {
        return dataname;
    }
}
