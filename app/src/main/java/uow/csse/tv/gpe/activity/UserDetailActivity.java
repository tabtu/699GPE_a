package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.UserDetailAthleteFragment;
import uow.csse.tv.gpe.fragment.UserDetailCoachFragment;
import uow.csse.tv.gpe.fragment.UserDetailRefereeFragment;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

//    public void refreshUI (Message msg, ListView listView, List<Club> mylist) {
//        if (msg.what == 0x0) {
//            //pd.dismiss();
//            ClubListAdapter clubListAdapter = new ClubListAdapter(UserDetailActivity.this, mylist);
//            listView.setAdapter(clubListAdapter);
//        } else {
//            Toast.makeText(UserDetailActivity.this, "empty list", Toast.LENGTH_SHORT).show();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new UserDetailAthleteFragment(),"Athlete");
        adapter.AddFragment(new UserDetailCoachFragment(),"Coach");
        adapter.AddFragment(new UserDetailRefereeFragment(),"Referee");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
