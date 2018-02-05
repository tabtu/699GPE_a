package com.example.vian.gpe_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.vian.gpe_android.R;
import com.example.vian.gpe_android.activity.FieldsDetailActivity;
import com.youth.banner.Banner;

public class HomeFragment extends Fragment {
    Banner banner_news;
    ImageButton btn_fields;
    Banner banner_interests;

    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"
    };
    //设置图片标题:自动对应
    String[] titles=new String[]{"全场2折起","全场2折起","十大星级品牌联盟","嗨购5折不要停","12趁现在","嗨购5折不要停，12.12趁现在","实打实大顶顶顶顶"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //buttons
        btn_fields = (ImageButton) view.findViewById(R.id.fields);
        btn_fields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(),FieldsDetailActivity.class);
                startActivity(intent);
            }
        });

        //banner
        banner_news = (Banner)view.findViewById(R.id.banner_news);
        banner_news.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner_news.setIndicatorGravity(Banner.CENTER);
        banner_news.setBannerTitle(titles);
        banner_news.setImages(images);
        banner_news.isAutoPlay(true);
        banner_news.setDelayTime(5000);

        banner_interests = (Banner)view.findViewById(R.id.banner_interests);
        banner_interests.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner_interests.setIndicatorGravity(Banner.CENTER);
        banner_interests.setBannerTitle(titles);
        banner_interests.setImages(images);
        banner_interests.isAutoPlay(true);
        banner_interests.setDelayTime(5000);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
