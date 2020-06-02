package cn.kn.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/5/27 15:38
 * @Description
 */
public class ExportExcel {

    private String oneCode = "";
    private String ontName = "";
    private String twoCode = "";
    private String twoName = "";
    private String threeCode = "";
    private String threeName = "";
    private String ruleName = "";
    private String rule = "";

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("oneCode", oneCode)
                .append("ontName", ontName)
                .append("twoCode", twoCode)
                .append("twoName", twoName)
                .append("threeCode", threeCode)
                .append("threeName", threeName)
                .append("ruleName", ruleName)
                .append("rule", rule)
                .toString();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getOneCode() {
        return oneCode;
    }

    public void setOneCode(String oneCode) {
        this.oneCode = oneCode;
    }

    public String getOntName() {
        return ontName;
    }

    public void setOntName(String ontName) {
        this.ontName = ontName;
    }

    public String getTwoCode() {
        return twoCode;
    }

    public void setTwoCode(String twoCode) {
        this.twoCode = twoCode;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }

    public String getThreeCode() {
        return threeCode;
    }

    public void setThreeCode(String threeCode) {
        this.threeCode = threeCode;
    }

    public String getThreeName() {
        return threeName;
    }

    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
