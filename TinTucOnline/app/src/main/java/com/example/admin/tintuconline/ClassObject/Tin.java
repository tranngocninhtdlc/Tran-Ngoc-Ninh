package com.example.admin.tintuconline.ClassObject;



// Dùng để lưu trữ tên báo và url của tin đó

public class Tin {

    int icon;
    String tenTin;
    String urlTin;

    public Tin(int icon, String tenTin) {
        this.icon = icon;
        this.tenTin = tenTin;
    }

    public Tin(int icon, String tenTin, String urlTin) {
        this.icon = icon;
        this.tenTin = tenTin;
        this.urlTin = urlTin;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTenTin() {
        return tenTin;
    }

    public void setTenTin(String tenTin) {
        this.tenTin = tenTin;
    }
}
