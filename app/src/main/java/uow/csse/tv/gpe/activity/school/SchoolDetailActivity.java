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
import uow.csse.tv.gpe.fragment.HonorFragment;
import uow.csse.tv.gpe.fragment.club.ClubMovementFragment;
import uow.csse.tv.gpe.model.Club;

/**
 * Created by Vian on 2/9/2018.
 */

public class SchoolDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Club school;

    private void setData() {
        TextView name = (TextView) findViewById(R.id.schooldetail_name);
        TextView location = (TextView) findViewById(R.id.schooldetail_location);
        TextView contact = (TextView) findViewById(R.id.schooldetail_contact);
        TextView register = (TextView) findViewById(R.id.schooldetail_register);
        TextView intro = (TextView) findViewById(R.id.schooldetail_intro);

        name.setText(school.getName());
        location.setText(school.getAddress());
        contact.setText(school.getTel());
//        register.setText(school.get());
        intro.setText(school.getIntroduction());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schooldetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.schooldetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        school = (Club) getIntent().getSerializableExtra("school");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new HonorFragment(),"Honor");
        adapter.AddFragment(new ClubMovementFragment(),"Movement");
        adapter.AddFragment(new HonorFragment(),"Activity");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
