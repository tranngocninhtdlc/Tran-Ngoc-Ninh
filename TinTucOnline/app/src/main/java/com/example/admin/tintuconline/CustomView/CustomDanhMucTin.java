package com.example.admin.tintuconline.CustomView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.tintuconline.ClassObject.Tin;
import com.example.admin.tintuconline.R;

import java.util.List;


public class CustomDanhMucTin extends ArrayAdapter<Tin> {

    Context context;
    int resource;
    List<Tin> objects;

    public CustomDanhMucTin(@NonNull Context context, int resource, @NonNull List<Tin> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder;
        if (convertView == null){
            viewholder = new Viewholder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dstin,parent,false);
            viewholder.mIcon = convertView.findViewById(R.id.imageview_icon);
            viewholder.tenDanhMuc = convertView.findViewById(R.id.textview_tendanhmuc);

            convertView.setTag(viewholder);

        }else {
            viewholder = (Viewholder) convertView.getTag();
        }

        Tin danhMucTin = objects.get(position);
        viewholder.mIcon.setImageResource(danhMucTin.getIcon());
        viewholder.tenDanhMuc.setText(danhMucTin.getTenTin());
        return convertView;
    }

    public class Viewholder{
        ImageView mIcon;
        TextView tenDanhMuc;
    }

}
