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
import android.widget.GridView;

import com.example.admin.tintuconline.ClassObject.DanhSachTin;
import com.example.admin.tintuconline.ClassObject.Tin;
import com.example.admin.tintuconline.ClassObject.DanhMucTin;
import com.example.admin.tintuconline.CustomView.CustomDanhMucTin;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar nToolbar;
    DrawerLayout drawer;
    GridView grvDanhSachTin;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        actionToolbar();
        ActionDanhMucTin();
    }

    private void ActionDanhMucTin() {
        DanhSachTin arrayDanhSachTin = new DanhSachTin();
        ArrayList<Tin> tin = arrayDanhSachTin.LayDanhSachTin();
        CustomDanhMucTin customDanhMucTin = new CustomDanhMucTin(this,R.layout.item_listview_dstin,tin);
        grvDanhSachTin.setAdapter(customDanhMucTin);
        grvDanhSachTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent intent = new Intent(MainActivity.this, ScreenSlideActivity.class);
                    ArrayList<DanhMucTin> arrDanhMucTin = new ArrayList<>();
                    arrDanhMucTin.add(new DanhMucTin("Trang Chủ",StaticDataVNExpress.urlTrangChu));
                    arrDanhMucTin.add(new DanhMucTin("Cộng Đồng",StaticDataVNExpress.urlCongDong));
                    arrDanhMucTin.add(new DanhMucTin("Giải Trí",StaticDataVNExpress.urlGiaiTri));
                    arrDanhMucTin.add(new DanhMucTin("Thời Sự",StaticDataVNExpress.urlThoiSu));
                    arrDanhMucTin.add(new DanhMucTin("Giáo Dục",StaticDataVNExpress.urlGiaoDuc));
                    arrDanhMucTin.add(new DanhMucTin("Du Lịch",StaticDataVNExpress.urlDuLich));
                    arrDanhMucTin.add(new DanhMucTin("Khoa Học",StaticDataVNExpress.urlKhoaHoc));
                    arrDanhMucTin.add(new DanhMucTin("Gia Đình",StaticDataVNExpress.urlGiaDinh));
                    arrDanhMucTin.add(new DanhMucTin("Kinh Doanh",StaticDataVNExpress.urlKinhDoanh));
                    arrDanhMucTin.add(new DanhMucTin("Pháp Luật",StaticDataVNExpress.urlPhapLuat));
                    arrDanhMucTin.add(new DanhMucTin("Số Hóa",StaticDataVNExpress.urlSoHoa));
                    arrDanhMucTin.add(new DanhMucTin("Startup",StaticDataVNExpress.urlStartUp));
                    arrDanhMucTin.add(new DanhMucTin("Sức Khỏe",StaticDataVNExpress.urlSucKhoe));
                    arrDanhMucTin.add(new DanhMucTin("Tâm Sự",StaticDataVNExpress.urlTamSu));
                    arrDanhMucTin.add(new DanhMucTin("Thế Giới",StaticDataVNExpress.urlTheGioi));
                    arrDanhMucTin.add(new DanhMucTin("Thể Thao",StaticDataVNExpress.urlTheThao));
                    arrDanhMucTin.add(new DanhMucTin("Xe",StaticDataVNExpress.urlXe));
                    arrDanhMucTin.add(new DanhMucTin("Cười",StaticDataVNExpress.urlCuoi));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DANHMUCTINBUNDLE",arrDanhMucTin);
                    intent.putExtra("LISTDANHMUCTIN",bundle);
                    startActivity(intent);
                }else if (i == 1){
                    Intent intent = new Intent(MainActivity.this, ScreenSlideActivity.class);
                    ArrayList<DanhMucTin> arrDanhMucTin = new ArrayList<>();
                    arrDanhMucTin.add(new DanhMucTin("Trang Chủ",StaticDataTuoiTre.urlTrangChu));
                    arrDanhMucTin.add(new DanhMucTin("Thế Giới",StaticDataTuoiTre.UrlTheGioi));
                    arrDanhMucTin.add(new DanhMucTin("Kinh Doanh",StaticDataTuoiTre.urlKinhDoanh));
                    arrDanhMucTin.add(new DanhMucTin("Xe",StaticDataTuoiTre.urlxe));
                    arrDanhMucTin.add(new DanhMucTin("Văn Hóa",StaticDataTuoiTre.urlVanHoa));
                    arrDanhMucTin.add(new DanhMucTin("Thể Thao",StaticDataTuoiTre.urlTheThao));
                    arrDanhMucTin.add(new DanhMucTin("Khoa Học",StaticDataTuoiTre.urlKhoaHoc));
                    arrDanhMucTin.add(new DanhMucTin("Giả Thật",StaticDataTuoiTre.urlGiaThat));
                    arrDanhMucTin.add(new DanhMucTin("Bạn Đọc Làm Báo",StaticDataTuoiTre.urlBanDocLamBao));
                    arrDanhMucTin.add(new DanhMucTin("Thời Sự",StaticDataTuoiTre.urlThoiSu));
                    arrDanhMucTin.add(new DanhMucTin("Pháp Luật",StaticDataTuoiTre.urlPhapLuat));
                    arrDanhMucTin.add(new DanhMucTin("Công Nghệ",StaticDataTuoiTre.urlNhipSongSo));
                    arrDanhMucTin.add(new DanhMucTin("Nhịp Sống Trẻ",StaticDataTuoiTre.urlNhipSongTre));
                    arrDanhMucTin.add(new DanhMucTin("Giải Trí",StaticDataTuoiTre.urlGiaiTri));
                    arrDanhMucTin.add(new DanhMucTin("Giáo Dục",StaticDataTuoiTre.urlGiaoDuc));
                    arrDanhMucTin.add(new DanhMucTin("Sức Khỏe",StaticDataTuoiTre.urlSucKhoe));
                    arrDanhMucTin.add(new DanhMucTin("Thư Giãn",StaticDataTuoiTre.urlThuGian));
                    arrDanhMucTin.add(new DanhMucTin("Du Lịch",StaticDataTuoiTre.urlDuLich));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DANHMUCTINBUNDLE",arrDanhMucTin);
                    intent.putExtra("LISTDANHMUCTIN",bundle);
                    startActivity(intent);

                    Intent intent1 = new Intent(MainActivity.this, MyPost.class);
                    startActivity(intent);

                }
            }
        });
    }

    private void actionToolbar() {
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
                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent = new Intent(MainActivity.this,TinTucOfflineActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTuyChinh:
                                Intent intent1 = new Intent(MainActivity.this,SettingActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
    }

    private void anhXa() {
        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        grvDanhSachTin = findViewById(R.id.grvDanhSachTin );
        navigationView = (NavigationView) findViewById(R.id.navigationview);
    }
}
