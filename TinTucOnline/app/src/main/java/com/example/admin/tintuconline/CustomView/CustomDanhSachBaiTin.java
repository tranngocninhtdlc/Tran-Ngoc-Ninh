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

import com.bumptech.glide.Glide;
import com.example.admin.tintuconline.ClassObject.ItemsRss;
import com.example.admin.tintuconline.R;

import java.util.List;



public class CustomDanhSachBaiTin extends ArrayAdapter<ItemsRss> {

    @NonNull
    Context context;
    int resource;
    @NonNull
    List<ItemsRss> objects;

    public CustomDanhSachBaiTin(@NonNull Context context, int resource, @NonNull List<ItemsRss> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dsbaitin, parent, false);
            viewHolder.anhbaitin = convertView.findViewById(R.id.img_anhbaitin);
            viewHolder.tieudebaitin = convertView.findViewById(R.id.tv_tieudebaitin);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemsRss itemsRss = objects.get(position);
        viewHolder.anhbaitin.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(itemsRss.getUrlImg()).into(viewHolder.anhbaitin);
        viewHolder.tieudebaitin.setText(itemsRss.getTitle());

        return convertView;
    }

    public class ViewHolder {
        ImageView anhbaitin;
        TextView tieudebaitin;
    }

}
