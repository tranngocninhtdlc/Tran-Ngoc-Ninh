package com.example.admin.tintuconline.ClassObject;

import java.io.Serializable;


// Dùng để lưu trữ danh mục Tin và rss theo danh mục

public class DanhMucTin implements Serializable{

    String tendanhmuc;
    String urlDanhMuc;

    public DanhMucTin(String tendanhmuc, String urlDanhMuc) {
        this.tendanhmuc = tendanhmuc;
        this.urlDanhMuc = urlDanhMuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getUrlDanhMuc() {
        return urlDanhMuc;
    }

    public void setUrlDanhMuc(String urlDanhMuc) {
        this.urlDanhMuc = urlDanhMuc;
    }
}
