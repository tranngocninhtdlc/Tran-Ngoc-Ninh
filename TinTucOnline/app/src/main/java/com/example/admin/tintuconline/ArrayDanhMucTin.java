package com.example.admin.tintuconline;

import com.example.admin.tintuconline.ClassObject.Tin;

import java.util.ArrayList;


public class ArrayDanhMucTin {

    public ArrayList<Tin> DanhMucTin(){
        ArrayList<Tin> DMTin = new ArrayList<>();
        DMTin.add(new Tin(R.drawable.vnexpress,"VNExpress"));
        return DMTin;
    }

}
