package com.example.admin.tintuconline.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.tintuconline.ClassObject.OfflineRSSItem;
import com.example.admin.tintuconline.ClassObject.Post;
import com.example.admin.tintuconline.CustomView.AdapterListBaiTinOffline;
import com.example.admin.tintuconline.Database.DBOfflineRSSItem;
import com.example.admin.tintuconline.Database.DBPosts;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;
import java.util.List;


public class TinTucOfflineActivity extends AppCompatActivity {

    ListView lvDanhSachBaiTinOffline;
    DBOfflineRSSItem dbOfflineRSSItem;
    Toolbar nToolbar;
    DrawerLayout drawer;
    List<OfflineRSSItem> offlineRSSItems = new ArrayList<>();
    AdapterListBaiTinOffline adapterListBaiTinOffline;
    NavigationView navigationView;


    DBPosts dbPosts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintucoffline);
        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        setSupportActionBar(nToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        nToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
        );

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(TinTucOfflineActivity.this,MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                break;
                            case R.id.menuTuyChinh:
                                Intent intent1 = new Intent(TinTucOfflineActivity.this,SettingActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
        lvDanhSachBaiTinOffline = findViewById(R.id.lvDSBaiTinOffLine);
        registerForContextMenu(lvDanhSachBaiTinOffline);

        dbOfflineRSSItem = new DBOfflineRSSItem(TinTucOfflineActivity.this);
        dbPosts = new DBPosts(TinTucOfflineActivity.this);

        offlineRSSItems = dbOfflineRSSItem.getAlLOffLineItemRss();
        for (int i = 0; i<dbPosts.getAllPosts().size(); i++){

            String title = dbPosts.getAllPosts().get(i).getPost_title();
            String description = dbPosts.getAllPosts().get(i).getPost_desc();
            String content = dbPosts.getAllPosts().get(i).getPost_content();
            String urlImg = dbPosts.getAllPosts().get(i).getPost_thumb();


        }


        Toast.makeText(this,offlineRSSItems.size() + "",Toast.LENGTH_SHORT).show();
        adapterListBaiTinOffline = new AdapterListBaiTinOffline(TinTucOfflineActivity.this,R.layout.item_listview_dsbaitin,offlineRSSItems);
        lvDanhSachBaiTinOffline.setAdapter(adapterListBaiTinOffline);

        lvDanhSachBaiTinOffline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TinTucOfflineActivity.this,NoiDungTin.class);
                intent.putExtra("ndBaiTin",offlineRSSItems.get(i).getContent());
                startActivity(intent);
            }
        });

        lvDanhSachBaiTinOffline.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int positon = i;

                Toast.makeText(TinTucOfflineActivity.this, "Xoasss", Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder builder = new AlertDialog.Builder(TinTucOfflineActivity.this);
                builder.setTitle("Alert!!");
                builder.setMessage("Bạn có muốn xóa!!!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbOfflineRSSItem.delete(offlineRSSItems.get(positon));
                        offlineRSSItems.clear();
                        offlineRSSItems.addAll(dbOfflineRSSItem.getAlLOffLineItemRss());
                        adapterListBaiTinOffline.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"Xóa");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle()=="Xóa"){
           dbOfflineRSSItem.delete(offlineRSSItems.get(info.position));
            offlineRSSItems.clear();
            offlineRSSItems.addAll(dbOfflineRSSItem.getAlLOffLineItemRss());
           adapterListBaiTinOffline.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

}
