package com.example.vian.gpe_android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.vian.gpe_android.R;
import com.example.vian.gpe_android.adapter.TabhostAdapter;
import com.example.vian.gpe_android.fragment.FieldsDetailActivityFragment;
import com.example.vian.gpe_android.fragment.FieldsDetailMovementFragment;
import com.youth.banner.Banner;

/**
 * Created by Vian on 2/5/2018.
 */

public class FieldsDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    Banner banner_fields;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fieldsdetail);

        banner_fields = (Banner) findViewById(R.id.banner_fields);
        banner_fields.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner_fields.setIndicatorGravity(Banner.CENTER);
        banner_fields.setBannerTitle(titles);
        banner_fields.setImages(images);
        banner_fields.isAutoPlay(true);
        banner_fields.setDelayTime(5000);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FieldsDetailMovementFragment(),"Movement");
        adapter.AddFragment(new FieldsDetailActivityFragment(),"Activity");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
