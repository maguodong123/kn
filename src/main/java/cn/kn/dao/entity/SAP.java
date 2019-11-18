package cn.kn.dao.entity;

public class SAP {
    Integer id;
    String oldcode;
    Long factory;
    String MRPcg;
    String MRPts;
    String CG;
    String JH;
    String CC;
    String ZJ;
    String KJ;

    @Override
    public String toString() {
        return "SAP{" +
                "id=" + id +
                ", oldcode='" + oldcode + '\'' +
                ", factory=" + factory +
                ", MRPcg='" + MRPcg + '\'' +
                ", MRPts='" + MRPts + '\'' +
                ", CG='" + CG + '\'' +
                ", JH='" + JH + '\'' +
                ", CC='" + CC + '\'' +
                ", ZJ='" + ZJ + '\'' +
                ", KJ='" + KJ + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldcode() {
        return oldcode;
    }

    public void setOldcode(String oldcode) {
        this.oldcode = oldcode;
    }

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public String getMRPcg() {
        return MRPcg;
    }

    public void setMRPcg(String MRPcg) {
        this.MRPcg = MRPcg;
    }

    public String getMRPts() {
        return MRPts;
    }

    public void setMRPts(String MRPts) {
        this.MRPts = MRPts;
    }

    public String getCG() {
        return CG;
    }

    public void setCG(String CG) {
        this.CG = CG;
    }

    public String getJH() {
        return JH;
    }

    public void setJH(String JH) {
        this.JH = JH;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getZJ() {
        return ZJ;
    }

    public void setZJ(String ZJ) {
        this.ZJ = ZJ;
    }

    public String getKJ() {
        return KJ;
    }

    public void setKJ(String KJ) {
        this.KJ = KJ;
    }
}
