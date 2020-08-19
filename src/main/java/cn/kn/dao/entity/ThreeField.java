package cn.kn.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/8/14 17:06
 * @Description
 */
public class ThreeField {

    String code;
    String purchase;
    String time;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("purchase", purchase)
                .append("time", time)
                .toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
