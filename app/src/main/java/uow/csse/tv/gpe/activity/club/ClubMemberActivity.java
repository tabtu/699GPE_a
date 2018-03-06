package uow.csse.tv.gpe.activity.club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.todddavies.components.progressbar.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.activity.club.ClubDetailActivity;
import uow.csse.tv.gpe.adapter.user.UserListAdapter;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 3/6/2018.
 */

public class ClubMemberActivity extends AppCompatActivity{

    private ListView listView;
    private List<User> mylist = new ArrayList<>();
    private Club club;
    private ProgressWheel pw;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;
    private UserListAdapter userListAdapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                userListAdapter = new UserListAdapter(ClubMemberActivity.this, mylist);
                listView.setAdapter(userListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x2) {
                userListAdapter.notifyDataSetChanged();
                userListAdapter = new UserListAdapter(ClubMemberActivity.this, mylist);
                Toast.makeText(ClubMemberActivity.this, "Loading finish", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x1) {
                Toast.makeText(ClubMemberActivity.this, "empty list", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x3) {
                Toast.makeText(ClubMemberActivity.this, "Last item", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
        }
    };

    private void initList() {
        userListAdapter = new UserListAdapter(ClubMemberActivity.this, mylist);
        listView.setAdapter(userListAdapter);
        pw.stopSpinning();
        pw.setVisibility(View.GONE);
    }

    private void initSwipeFreshLayout() {
        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mSwipeRefreshView.setOnLoadMoreListener(new SwipeRefreshView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (!lastItem) {
                    loadMoreData();
                } else {
                    mSwipeRefreshView.setLoading(false);
                    Toast.makeText(ClubMemberActivity.this, "Last item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadMoreData() {
        pageCount++;
        userListAdapter.notifyDataSetChanged();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        HttpUtils hu = new HttpUtils();
//                        String tmp = hu.executeHttpGet(Const.getvenuelist + city.getId() + "&"+ Const.PAGE + "0");
//                        JsonParse jp = new JsonParse(tmp);
//                        List<Venue> temp = jp.ParseJsonVenue(tmp);
//                        if (temp.size() != 0) {
//                            mylist.addAll(temp);
//                            Message msg = new Message();
//                            msg.what = 0x2;
//                            handler.sendMessage(msg);
//                        } else {
//                            lastItem = true;
//                            Message msg = new Message();
//                            msg.what = 0x3;
//                            handler.sendMessage(msg);
//                        }
//                    }
//                }).start();
//            }
//        }, 2000);
    }

    private void initData() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lastItem = false;
//                pageCount = 0;
//                initList();
//                venueListAdapter.notifyDataSetChanged();
//                Toast.makeText(VenueActivity.this, "Refresh finish", Toast.LENGTH_SHORT).show();
//                if (mSwipeRefreshView.isRefreshing()) {
//                    mSwipeRefreshView.setRefreshing(false);
//                }
//            }
//        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        club = (Club) getIntent().getSerializableExtra("club");
        mylist = club.getUsers();

        pw = (ProgressWheel) findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.fieldslist);
        initList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ClubMemberActivity.this, UserDetailActivity.class);
                intent.putExtra("user", mylist.get(i));
                startActivity(intent);
            }
        });

        mSwipeRefreshView = (SwipeRefreshView) findViewById(R.id.swipelayout);
        mSwipeRefreshView.setColorSchemeResources(R.color.gpe,
                R.color.lightgpe);
//        mSwipeRefreshView.setItemCount(2);
        mSwipeRefreshView.measure(0, 0);
        initSwipeFreshLayout();

    }
}
