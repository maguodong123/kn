package cn.kn.dao.entity;

import java.io.Serializable;

public class CRM implements Serializable {
    private String ziCode;//子项目编号
    private String ziName;//子项目名称
    private String zhuCode;//主项目号
    private String productName;//产品名称
    private String productModel;//产品型号
    private String generalList;//总列数
    private String companyCode;//公司代码
    private String companyName;//公司名称
    private String profit;//利润中心代码
    private String project;//利润中心名称
    private String regionCode;//项目地区代码
    private String regionName;//项目地区名称
    private String otherCode;//其他分类代码
    private String otherName;//其它分类名称
    private String customer;//所属客户
    private String CustomerCode;//客户编码
    private String factory;//工厂

    @Override
    public String toString() {
        return "CRM{" +
                "ziCode='" + ziCode + '\'' +
                ", ziName='" + ziName + '\'' +
                ", zhuCode='" + zhuCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productModel='" + productModel + '\'' +
                ", generalList='" + generalList + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", profit='" + profit + '\'' +
                ", project='" + project + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", otherCode='" + otherCode + '\'' +
                ", otherName='" + otherName + '\'' +
                ", customer='" + customer + '\'' +
                ", CustomerCode='" + CustomerCode + '\'' +
                ", factory='" + factory + '\'' +
                '}';
    }

    public String getZiCode() {
        return ziCode;
    }

    public void setZiCode(String ziCode) {
        this.ziCode = ziCode;
    }

    public String getZiName() {
        return ziName;
    }

    public void setZiName(String ziName) {
        this.ziName = ziName;
    }

    public String getZhuCode() {
        return zhuCode;
    }

    public void setZhuCode(String zhuCode) {
        this.zhuCode = zhuCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getGeneralList() {
        return generalList;
    }

    public void setGeneralList(String generalList) {
        this.generalList = generalList;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOtherCode() {
        return otherCode;
    }

    public void setOtherCode(String otherCode) {
        this.otherCode = otherCode;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
