package com.example.admin.tintuconline.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

//import com.example.admin.tintuconline.R;
import com.example.admin.tintuconline.ClassObject.OfflineRSSItem;
import com.example.admin.tintuconline.ClassObject.Post;
import com.example.admin.tintuconline.Database.DBOfflineRSSItem;
import com.example.admin.tintuconline.Database.DBPosts;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;

public class ContentDetailsPosts extends AppCompatActivity {

    WebView wvContentDetails;
    AppCompatImageView imgSave, imgShare;

    DBPosts dbPosts;
    OfflineRSSItem offlineRSSItem;
    DBOfflineRSSItem dbOfflineRSSItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details_posts);

        wvContentDetails = findViewById(R.id.wv_content_details);
        imgSave = findViewById(R.id.imgSave);
        imgShare = findViewById(R.id.imgShare);

        final Intent intent = getIntent();
        String nd = intent.getStringExtra("noidungg");

        wvContentDetails.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;}</style></head><body>" + nd + "</body></html>", "text/html", "UTF-8");

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ContentDetailsPosts.this);
                builder.setTitle("Bạn muốn lưu tin?");
                builder.setMessage("Tin sẽ được lưu lại để đọc khi không có mạng");
                builder.setNegativeButton("Thôi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbPosts = new DBPosts(ContentDetailsPosts.this);
                        dbOfflineRSSItem = new DBOfflineRSSItem(ContentDetailsPosts.this);
                        Post post = (Post) intent.getSerializableExtra("post");


                        String title = post.getPost_title();
                        String description = post.getPost_desc();
                        String content = post.getPost_content();
                        String urlImg = post.getPost_thumb();

                        offlineRSSItem = new OfflineRSSItem(title,description,content,urlImg);

                        int post_id = post.getPost_id();
                        String post_title = post.getPost_title();
                        String post_desc = post.getPost_desc();
                        String post_thumb = post.getPost_thumb();
                        String post_content = post.getPost_content();
                        int category_id = post.getCategory_id();

                        long ok = dbPosts.addPost(post);

                        long okk = dbOfflineRSSItem.Insert(offlineRSSItem);
                        if (okk > 0) {
                            Toast.makeText(ContentDetailsPosts.this, "Đã lưu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.create().show();
            }
        });

    }
}
