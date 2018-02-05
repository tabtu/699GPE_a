package com.example.vian.gpe_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

public class HomeFragment extends Fragment {
    Banner banner;

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
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_fragment);
//        banner = (Banner) findViewById(R.id.banner);
//        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
//        banner.setIndicatorGravity(Banner.CENTER);
//        banner.setBannerTitle(titles);
//        banner.isAutoPlay(true);
//        banner.setDelayTime(5000);
//
//        banner.setImages(images, new Banner.OnLoadImageListener() {
//            @Override
//            public void OnLoadImage(ImageView view, Object url) {
//                System.out.println("Loading...");
//                Glide.with(getApplicationContext()).load(url).into(view);
//                System.out.println("Loading complete");
//            }
//        });
//
//        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
//            @Override
//            public void OnBannerClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        banner = (Banner)view.findViewById(R.id.banner);

        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.setBannerTitle(titles);
        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
