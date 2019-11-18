package cn.kn.dao.excel;

public class ExcelMDM extends ExcelMap{
    private String factory;
    private String viewName;

    @Override
    public String toString() {
        return "ExcelMDM{" +
                "factory='" + factory + '\'' +
                ", viewName='" + viewName + '\'' +
                '}';
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
