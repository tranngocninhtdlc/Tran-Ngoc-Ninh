package com.example.admin.tintuconline.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.tintuconline.ClassObject.DanhMucTin;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;

public class ScreenSlideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    Toolbar nToolbar;DrawerLayout drawer;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    ArrayList<DanhMucTin> arrDanhMucTinVNExpress;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenslide_activity);
        Bundle bundle = getIntent().getBundleExtra("LISTDANHMUCTIN");
        arrDanhMucTinVNExpress = (ArrayList<DanhMucTin>) bundle.getSerializable("DANHMUCTINBUNDLE");
        AnhXa();

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
        addControls();
    }


    private void addControls() {
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(mPagerAdapter);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(ScreenSlideActivity.this,MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent1 = new Intent(ScreenSlideActivity.this,TinTucOfflineActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        drawer.closeDrawers();
                        return true;
                    }
                });
    }

    private void AnhXa() {
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tablayout_vnexpress);
        nToolbar = findViewById(R.id.toolbar_trangchu);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationview);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment().create(position,arrDanhMucTinVNExpress.get(position).getUrlDanhMuc());
        }

        @Override
        public int getCount() {
            return arrDanhMucTinVNExpress.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arrDanhMucTinVNExpress.get(position).getTendanhmuc();
        }
    }

    public ArrayList<DanhMucTin> getArrRSSVNExpress() {
        return arrDanhMucTinVNExpress;
    }
}
