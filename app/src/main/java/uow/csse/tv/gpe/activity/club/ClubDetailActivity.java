package uow.csse.tv.gpe.activity.club;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.fragment.club.ClubActivityFragment;
import uow.csse.tv.gpe.fragment.club.ClubCourseFragment;
import uow.csse.tv.gpe.fragment.club.ClubHonorFragment;
import uow.csse.tv.gpe.fragment.club.ClubNewsFragment;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.SwitchLanguage;

/**
 * Created by Vian on 2/9/2018.
 */

public class ClubDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Club club;
    private TabhostAdapter adapter;

    public Club getClub() {
        return club;
    }

    private void setData() {
        TextView name = (TextView) findViewById(R.id.clubdetail_name);
        TextView city = (TextView) findViewById(R.id.clubdetail_city);
        TextView district = (TextView) findViewById(R.id.clubdetail_district);
        TextView memberNum = (TextView) findViewById(R.id.clubdetail_athletenum);
        TextView coachNum = (TextView) findViewById(R.id.clubdetail_cachnum);
        TextView refereeNum = (TextView) findViewById(R.id.clubdetail_refereenum);
        TextView contact = (TextView) findViewById(R.id.clubdetail_contact);
        TextView intro = (TextView) findViewById(R.id.clubdetail_intro);
        LinearLayout btn_member = (LinearLayout) findViewById(R.id.clubdetail_member);
        LinearLayout btn_coach = (LinearLayout) findViewById(R.id.clubdetail_coach);
        LinearLayout btn_referee = (LinearLayout) findViewById(R.id.clubdetail_referee);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(ClubDetailActivity.this,LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView memberlist = (RecyclerView) findViewById(R.id.clubdetail_memberlist);
//        memberlist.setLayoutManager(layoutManager);
//        MemberListAdapter memberListAdapter = new MemberListAdapter(this,club.getUsers());
//        memberAdapter.setClickListener(ClubDetailActivity.this);
//        memberlist.setAdapter(memberListAdapter);

        name.setText(club.getName());
//        city.setText(club.getDistrict().getCity().getName());
//        district.setText(club.getDistrict().getName());
        if (club.getUsers().size() != 0) {
            memberNum.setText(String.valueOf(club.getUsers().size()));
            btn_member.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ClubDetailActivity.this, ClubMemberActivity.class);
                    intent.putExtra("club", club);
                    intent.putExtra("type", "member");
                    startActivity(intent);
                }
            });
        }
        if (club.getCoaches().size() != 0) {
            coachNum.setText(String.valueOf(club.getCoaches().size()));
            btn_coach.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ClubDetailActivity.this, ClubMemberActivity.class);
                    intent.putExtra("club", club);
                    intent.putExtra("type", "coach");
                    startActivity(intent);
                }
            });
        }
        if (club.getReferees().size() != 0) {
            refereeNum.setText(String.valueOf(club.getReferees().size()));
            btn_referee.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ClubDetailActivity.this, ClubMemberActivity.class);
                    intent.putExtra("club", club);
                    intent.putExtra("type", "referee");
                    startActivity(intent);
                }
            });
        }
        contact.setText(club.getTel());
        intro.setText(club.getIntroduction());
//        Picasso.with(ClubDetailActivity.this).load(club.getPicture()).fit().into(image);
    }

    public List<User> getUserList() {
        return club.getUsers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.clubdetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        club = (Club) getIntent().getSerializableExtra("club");
        setData();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new TabhostAdapter(getSupportFragmentManager());
        adapter.AddFragment(new ClubCourseFragment(),getString(R.string.course));
        adapter.AddFragment(new ClubNewsFragment(),getString(R.string.news));
        adapter.AddFragment(new ClubHonorFragment(),getString(R.string.honor));
        adapter.AddFragment(new ClubActivityFragment(),getString(R.string.activity));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        setListener();
    }
}
