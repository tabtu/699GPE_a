package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.ClubListAdapter;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.adapter.UserListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.UserDetailAthleteFragment;
import uow.csse.tv.gpe.fragment.UserDetailCoachFragment;
import uow.csse.tv.gpe.fragment.UserDetailRefereeFragment;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Handler handler;
    private ListView listView;
    private List<Club> mylist = new ArrayList<>();

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                ClubListAdapter clubListAdapter = new ClubListAdapter(UserDetailActivity.this, mylist);
                listView.setAdapter(clubListAdapter);
            } else {
                Toast.makeText(UserDetailActivity.this, "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getvenuelist);
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonClub(tmp);
                if (mylist != null) {
                    Message msg = new Message();
                    msg.what = 0x0;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0x1;
                    handler.sendMessage(msg);
                }
            }
        }).start();

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
