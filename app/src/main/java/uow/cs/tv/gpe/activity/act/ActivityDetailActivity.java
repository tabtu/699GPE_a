package uow.cs.tv.gpe.activity.act;

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

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.Activity;
import uow.cs.tv.gpe.model.Club;
import uow.cs.tv.gpe.model.Venue;
import uow.cs.tv.gpe.util.Func;
import uow.cs.tv.gpe.util.SwitchLanguage;

/**
 * Created by Vian on 2/25/2018.
 */

public class ActivityDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Activity activity;
    private Venue venue;
    private Club club;
    private Func func = new Func();

    private void setData() {
        TextView title = (TextView)findViewById(R.id.activitydetail_title);
        TextView date = (TextView)findViewById(R.id.activitydetail_date);
        TextView time = (TextView)findViewById(R.id.activitydetail_time);
        TextView location = (TextView)findViewById(R.id.activitydetail_location);
        TextView intro = (TextView)findViewById(R.id.activitydetail_intro);
        TextView zbf = (TextView) findViewById(R.id.activitydetail_zbf);
        TextView price = (TextView) findViewById(R.id.activitydetail_price);
        ImageView image = (ImageView) findViewById(R.id.activitydetail_image);
        Button btn_join = (Button) findViewById(R.id.activitydetail_join);

        title.setText(activity.getTitle());
        if (activity.getStartdate() != null && activity.getEnddate() != null) {
            String dt = func.convertLong2String(activity.getStartdate()) + " - " + func.convertLong2String(activity.getEnddate());
            date.setText(dt);
        }
        if (activity.getContent() != null) {
            intro.setText(activity.getContent());
        }
        if (activity.getPicture() != null) {
            Picasso.with(ActivityDetailActivity.this).load(activity.getPicture()).resize(400,500).centerCrop().into(image);
        }
        if (activity.getPrice() != 0) {
            price.setText(String.valueOf(activity.getPrice()));
        } else {
            SwitchLanguage switchLanguage = new SwitchLanguage();
            if (switchLanguage.getLanguage().equals("en")) {
                price.setText("Free");
            } else {
                price.setText("免费");
            }
        }


//        for (int i = 0; i < activity.getClubs().)
//
//        if (activity.getAvaliable()) {
//            btn_join.setEnabled(true);
//        } else {
//            btn_join.setEnabled(false);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activitydetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activity = (Activity)getIntent().getSerializableExtra("act");
//        venue = (Venue)getIntent().getSerializableExtra("venue");
//        club = (Club)getIntent().getSerializableExtra("club");
        setData();
    }
}

