package com.example.admin.tintuconline.ClassObject;

import com.example.admin.tintuconline.ClassObject.Tin;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;



// Dùng để hiển thị danh sách tin tức

public class DanhSachTin {

    public ArrayList<Tin> LayDanhSachTin(){
        ArrayList<Tin> DanhMucTin = new ArrayList<>();
        DanhMucTin.add(new Tin(R.drawable.vnexpress,"VNExpress"));
        DanhMucTin.add(new Tin(R.drawable.tuoitre,"Tuổi Trẻ"));
        return DanhMucTin;
    }

}
