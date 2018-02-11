package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.ActivityFragment;
import uow.csse.tv.gpe.fragment.CourseFragment;
import uow.csse.tv.gpe.fragment.HonorFragment;
import uow.csse.tv.gpe.fragment.VenueMovementFragment;

/**
 * Created by Vian on 2/9/2018.
 */

public class ClubDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdetail);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());

        adapter.AddFragment(new CourseFragment(),"Course");
        adapter.AddFragment(new VenueMovementFragment(),"Movement");
        adapter.AddFragment(new HonorFragment(),"Honor");
        adapter.AddFragment(new ActivityFragment(),"Activity");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
