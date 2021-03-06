package com.example.admin.tintuconline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.tintuconline.ClassObject.OfflineRSSItem;
import com.example.admin.tintuconline.MyTask.BocNoiDungWeb;
import com.example.admin.tintuconline.CustomView.CustomDanhSachBaiTin;
import com.example.admin.tintuconline.ClassObject.DanhMucTin;
import com.example.admin.tintuconline.MyTask.DocRss;
import com.example.admin.tintuconline.ClassObject.ItemsRss;
import com.example.admin.tintuconline.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



public class ScreenSlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_RSS = "rss";
    ListView lvDSBaiTin;
    ArrayList<DanhMucTin> arrRSS = new ArrayList<>();
    ArrayList<ItemsRss> itemsRsses = new ArrayList<>();
    DocRss docRss = new DocRss();
    private OfflineRSSItem offlineRSSItem;
    public static ScreenSlidePageFragment create(int numbPage,String urlRSS)
    {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE,numbPage);
        bundle.putString(ARG_RSS,urlRSS);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fr_screenslide_page, container, false);

        lvDSBaiTin = rootView.findViewById(R.id.lvDSBaiTin);

        String url = getArguments().getString(ARG_RSS);
        try {
            itemsRsses = docRss.execute(url).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CustomDanhSachBaiTin customDanhSachBaiBao = new CustomDanhSachBaiTin(getContext(), R.layout.item_listview_dsbaitin, itemsRsses);
        if (itemsRsses == null) {
            Toast.makeText(getContext(), "Ko co data", Toast.LENGTH_SHORT).show();
        } else {
            lvDSBaiTin.setAdapter(customDanhSachBaiBao);
        }

        lvDSBaiTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BocNoiDungWeb bocNoiDungWeb = new BocNoiDungWeb();
                bocNoiDungWeb.execute(itemsRsses.get(i).getLink());

                try {
                    String noidunglayduoc = "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + bocNoiDungWeb.get();
                    offlineRSSItem = new OfflineRSSItem(itemsRsses.get(i).getTitle(),itemsRsses.get(i).getDescription(),noidunglayduoc,itemsRsses.get(i).getUrlImg());
                    Intent intent = new Intent(getContext(), NoiDungTin.class);
                    intent.putExtra("OfflineRSSItem",offlineRSSItem);
                    intent.putExtra("ndBaiTin", noidunglayduoc);
                    intent.putExtra("URL",itemsRsses.get(i).getLink());
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenSlideActivity vnExpressActivity = (ScreenSlideActivity) getActivity();

        arrRSS = vnExpressActivity.getArrRSSVNExpress();

    }

}
