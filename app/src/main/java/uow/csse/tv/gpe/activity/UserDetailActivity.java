package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.athlete.UserAthleteFragment;
import uow.csse.tv.gpe.fragment.athlete.UserCoachFragment;
import uow.csse.tv.gpe.fragment.athlete.UserRefereeFragment;
import uow.csse.tv.gpe.model.User;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User user;

    private void setData() {
        TextView name = (TextView) findViewById(R.id.userdetail_name);
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
