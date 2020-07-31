package cn.kn.dao.excel;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/7/20 10:58
 * @Description
 */
public class Vehicle {

    String manufacturer;
    String classification;
    String code;
    String vehicle;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("manufacturer", manufacturer)
                .append("classification", classification)
                .append("code", code)
                .append("vehicle", vehicle)
                .toString();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
