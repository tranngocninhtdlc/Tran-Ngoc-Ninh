package com.example.admin.tintuconline.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.tintuconline.ClassObject.Post;
import com.example.admin.tintuconline.CustomView.AdapterListPosts;
import com.example.admin.tintuconline.Link.Links;
import com.example.admin.tintuconline.MyTask.ReadPosts;
import com.example.admin.tintuconline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyPost extends AppCompatActivity {

    Links link;
    ListView lvDataPost;
    AdapterListPosts adapterListPosts;
    ArrayList<Post> posts;
    ReadPosts readPosts;

    Toolbar toolbarTrangchu;
    NavigationView navigationview;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        lvDataPost = findViewById(R.id.lv_data_post);
        toolbarTrangchu = findViewById(R.id.toolbar_trangchu);
        navigationview =findViewById(R.id.navigationview);
        drawer = findViewById(R.id.drawer);

        link = new Links();
        posts = new ArrayList<>();

        readPosts = new ReadPosts();
        String jsonPost = null;
        try {

            for (int i = 0; i < jsonPost.length(); i++){
                JSONArray jsonPostArray = new JSONArray(jsonPost);
                JSONObject jsonPostObject = jsonPostArray.getJSONObject(i);

                int post_id = jsonPostObject.getInt("post_id");
                String post_title = jsonPostObject.getString("post_title");
                String post_desc = jsonPostObject.getString("post_desc");
                String post_thumb = jsonPostObject.getString("post_thumb");
                String post_content = jsonPostObject.getString("post_content");
                int category_id = jsonPostObject.getInt("category_id");

                Post post = new Post(post_id,post_title,post_desc,post_thumb,post_content,category_id);
                posts.add(post);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapterListPosts = new AdapterListPosts(MyPost.this, R.layout.layout_post, posts);
        lvDataPost.setAdapter(adapterListPosts);
        lvDataPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyPost.this, i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyPost.this,ContentDetailsPosts.class);

                int post_id = posts.get(i).getPost_id();
                String post_title = posts.get(i).getPost_title();
                String post_desc = posts.get(i).getPost_desc();
                String post_thumb = posts.get(i).getPost_thumb();
                String post_content = posts.get(i).getPost_content();
                int category_id = posts.get(i).getCategory_id();

                Post post = new Post(post_id,post_title, post_desc, post_thumb, post_content, category_id);

                intent.putExtra("noidungg",post_content);
                intent.putExtra("post",post);

                startActivity(intent);
            }
        });

        //set NavigationView:

        setSupportActionBar(toolbarTrangchu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTrangchu.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarTrangchu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        navigationview.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(MyPost.this,MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent2 = new Intent(MyPost.this,TinTucOfflineActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.menuTuyChinh:
                                Intent intent1 = new Intent(MyPost.this,SettingActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });

    }
}
