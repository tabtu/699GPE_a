package uow.cs.tv.gpe.activity.venue;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.adapter.TabhostAdapter;
import uow.cs.tv.gpe.fragment.venue.VenueActivityFragment;
import uow.cs.tv.gpe.fragment.venue.VenueNewsFragment;
import uow.cs.tv.gpe.model.Venue;

/**
 * Created by Vian on 2/5/2018.
 */

public class VenueDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Venue venue;

    private void setData() {
        TextView name = findViewById(R.id.venuedetail_name);
        TextView location = findViewById(R.id.venuedetail_location);
        TextView contact = findViewById(R.id.venuedetail_contact);

        name.setText(venue.getName());
        location.setText(venue.getAddress());
        contact.setText(String.valueOf(venue.getTel()));
    }

    public Venue getVenue() {
        return venue;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venuedetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.venuedetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        venue = (Venue) getIntent().getSerializableExtra("venue");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getSupportFragmentManager());
        adapter.AddFragment(new VenueNewsFragment(), getString(R.string.news));
        adapter.AddFragment(new VenueActivityFragment(), getString(R.string.activity));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
