package uow.csse.tv.gpe.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
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

    private void setData() {
        TextView name = (TextView) findViewById(R.id.userdetail_name);
        ImageView img = (ImageView) findViewById(R.id.userdetail_image);
        name.setText(user.getName());
        Picasso.with(UserDetailActivity.this).load(user.getPicture()).resize(220,220).centerCrop().transform(new CircleTransform()).into(img);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_userdetail);

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
