package cn.kn.dao.excel;

import java.io.Serializable;

public class ExcelCode implements Serializable {
    private String code;
    private String oldCode;

    @Override
    public String toString() {
        return "ExcelCode{" +
                "code='" + code + '\'' +
                ", oldCode='" + oldCode + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }
}
