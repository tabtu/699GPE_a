package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.ActivityFragment;
import uow.csse.tv.gpe.fragment.club.CourseFragment;
import uow.csse.tv.gpe.fragment.HonorFragment;
import uow.csse.tv.gpe.fragment.club.ClubMovementFragment;
import uow.csse.tv.gpe.model.Club;

/**
 * Created by Vian on 2/9/2018.
 */

public class ClubDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Club club;

    private void setData() {
        TextView name = (TextView)findViewById(R.id.clubdetail_name);
        TextView city = (TextView)findViewById(R.id.clubdetail_city);
        TextView district = (TextView)findViewById(R.id.clubdetail_district);
        TextView memberNum = (TextView)findViewById(R.id.clubdetail_athletenum);
        TextView coachNum = (TextView)findViewById(R.id.clubdetail_cachnum);
        TextView courseNum = (TextView)findViewById(R.id.clubdetail_coursenum);
        TextView contact = (TextView)findViewById(R.id.clubdetail_contact);
        TextView intro = (TextView)findViewById(R.id.clubdetail_intro);
        ImageView image = (ImageView)findViewById(R.id.clubdetail_image);

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

        club = (Club)getIntent().getSerializableExtra("club");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        CourseFragment cf = new CourseFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("club", club);
        cf.setArguments(bundle);
        adapter.AddFragment(new CourseFragment(),"Course");

        adapter.AddFragment(new ClubMovementFragment(),"Movement");
        adapter.AddFragment(new HonorFragment(),"Honor");
        adapter.AddFragment(new ActivityFragment(),"Activity");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
