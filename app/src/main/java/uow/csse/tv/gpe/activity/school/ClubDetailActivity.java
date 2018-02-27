package uow.csse.tv.gpe.activity.school;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.club.ClubActivityFragment;
import uow.csse.tv.gpe.fragment.club.ClubCourseFragment;
import uow.csse.tv.gpe.fragment.club.ClubHonorFragment;
import uow.csse.tv.gpe.fragment.club.ClubNewsFragment;
import uow.csse.tv.gpe.model.Club;

/**
 * Created by Vian on 2/9/2018.
 */

public class ClubDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Club club;
    private TabhostAdapter adapter;

    public Club getClub() {
        return club;
    }

    private void setData() {
        TextView name = (TextView) findViewById(R.id.clubdetail_name);
        TextView city = (TextView) findViewById(R.id.clubdetail_city);
        TextView district = (TextView) findViewById(R.id.clubdetail_district);
        TextView memberNum = (TextView) findViewById(R.id.clubdetail_athletenum);
        TextView coachNum = (TextView) findViewById(R.id.clubdetail_cachnum);
        TextView courseNum = (TextView) findViewById(R.id.clubdetail_coursenum);
        TextView contact = (TextView) findViewById(R.id.clubdetail_contact);
        TextView intro = (TextView) findViewById(R.id.clubdetail_intro);

        name.setText(club.getName());
        city.setText(club.getDistrict().getCity().getName());
        district.setText(club.getDistrict().getName());
//        memberNum.setText(club.getAthletes().size());
//        coachNum.setText(club.getCoaches().size());
//        courseNum.setText(club.getCoaches().size());
        contact.setText(club.getTel());
        intro.setText(club.getIntroduction());
//        Picasso.with(ClubDetailActivity.this).load(club.getPicture()).fit().into(image);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.clubdetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        club = (Club) getIntent().getSerializableExtra("club");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new ClubCourseFragment(),"Course");
        adapter.AddFragment(new ClubNewsFragment(),"News");
        adapter.AddFragment(new ClubHonorFragment(),"Honor");
        adapter.AddFragment(new ClubActivityFragment(),"Activity");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        setListener();
    }

//    private void setListener() {
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            //标签选中之后执行的方法
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                viewPager.setCurrentItem(tab.getPosition());
//                initFragment(tab.getPosition());
//            }
//
//            //标签没选中
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//
//    private void initFragment(int position) {
//        switch (position) {
//            case 0:
//                if (courseFragment == null) {
//                    courseFragment = new CourseFragment();
//                }
//                showFragment(courseFragment);
//                break;
//            case 1:
//                if (clubNewsFragment == null) {
//                    clubNewsFragment = new ClubNewsFragment();
//                }
//                showFragment(clubNewsFragment);
//                break;
//            case 2:
//                if (honorFragment == null) {
//                    honorFragment = new HonorFragment();
//                }
//                showFragment(honorFragment);
//                break;
//            case 3:
//                if (clubActivityFragment == null) {
//                    clubActivityFragment = new ClubActivityFragment();
//                }
//                showFragment(clubActivityFragment);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void showFragment(Fragment fragment) {
////        Bundle bundle=new Bundle();
////        bundle.putSerializable("club",club);
////        fragment.setArguments(bundle);
////        viewPager.setAdapter();
//    }

}
