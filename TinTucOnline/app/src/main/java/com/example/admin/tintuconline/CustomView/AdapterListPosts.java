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
import com.example.admin.tintuconline.R;

import com.example.admin.tintuconline.ClassObject.Post;

import java.util.List;

public class AdapterListPosts extends ArrayAdapter<Post> {

    @NonNull Context context;
    int resource;
    @NonNull List<Post> objects;

    public AdapterListPosts(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);

            viewHolder.ivAnh = convertView.findViewById(R.id.iv_anh);
            viewHolder.tvTieude = convertView.findViewById(R.id.tv_tieude);
            viewHolder.tvMota = convertView.findViewById(R.id.tv_mota);


            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post post = objects.get(position);

        if (post.getPost_thumb() !=null){
            viewHolder.ivAnh.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(post.getPost_thumb()).into(viewHolder.ivAnh);
        }else {
        }

        if (post.getPost_title() != null){
            viewHolder.tvTieude.setText(post.getPost_title());
        }else {
            viewHolder.tvTieude.setText("Không có dữ liệu!!!");
        }

        if (post.getPost_desc()!=null){
            viewHolder.tvMota.setText(post.getPost_desc());
        }else {
            viewHolder.tvMota.setText("Không có dữ liệu!!!");
        }
        return convertView;
    }

    public class ViewHolder{
        ImageView ivAnh;
        TextView tvTieude;
        TextView tvMota;
    }

}
