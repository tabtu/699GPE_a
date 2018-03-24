package uow.csse.tv.gpe.activity.account;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.account.FollowClubFragment;
import uow.csse.tv.gpe.fragment.account.FollowSchoolFragment;
import uow.csse.tv.gpe.fragment.account.FollowUserFragment;
import uow.csse.tv.gpe.fragment.account.FollowVenueFragment;
import uow.csse.tv.gpe.model.User;

public class FollowActivity extends AppCompatActivity{

    private User user;

    private void setFragment() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.follow_tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.follow_viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FollowUserFragment(), "User");
        adapter.AddFragment(new FollowVenueFragment(), "Venue");
        adapter.AddFragment(new FollowSchoolFragment(), "School");
        adapter.AddFragment(new FollowClubFragment(), "Club");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public User getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        user = (User) getIntent().getSerializableExtra("user");

        Toolbar toolbar = (Toolbar) findViewById(R.id.follow_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setFragment();
    }
}
