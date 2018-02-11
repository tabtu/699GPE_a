package uow.csse.tv.gpe.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.adapter.UserListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.UserAthleteFragment;
import uow.csse.tv.gpe.fragment.UserCoachFragment;
import uow.csse.tv.gpe.fragment.UserRefereeFragment;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User user;

    private void setData() {
        TextView name = (TextView) findViewById(R.id.Name);
//        Log.v("v",user.getReferee().getClubs().get(0).getName());
        name.setText(user.getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        user = (User)getIntent().getSerializableExtra("user");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        if (user.getAthlete() != null) {
            UserAthleteFragment uaf = new UserAthleteFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            uaf.setArguments(bundle);
            adapter.AddFragment(uaf, "Athlete");
        }
        if (user.getCoach() != null) {
            adapter.AddFragment(new UserCoachFragment(), "Coach");
        }
        if (user.getReferee() != null) {
            adapter.AddFragment(new UserRefereeFragment(), "Referee");
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
