package uow.csse.tv.gpe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.msg.MessageReceiveDetailActivity;
import uow.csse.tv.gpe.activity.msg.MessageSendActivity;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.user.UserAthleteFragment;
import uow.csse.tv.gpe.fragment.user.UserCoachFragment;
import uow.csse.tv.gpe.fragment.user.UserRefereeFragment;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.CircleTransform;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User user;
    private User currentUser;

    private void setData() {
        TextView name = (TextView) findViewById(R.id.userdetail_name);
        ImageView img = (ImageView) findViewById(R.id.userdetail_image);
        Button btn_msg = (Button) findViewById(R.id.userdetail_msg);
        Button btn_follow = (Button) findViewById(R.id.userdetail_follow);

        name.setText(user.getName());
        if (user.getPicture() != null) {
            Picasso.with(UserDetailActivity.this).load(user.getPicture()).resize(220, 220).centerCrop().transform(new CircleTransform()).into(img);
        }
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, MessageSendActivity.class);
                intent.putExtra("msgname", user.getId());
                intent.putExtra("msgmyid", currentUser.getId());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.userdetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user = (User)getIntent().getSerializableExtra("user");
        currentUser = (User)getIntent().getSerializableExtra("currentuser");
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
