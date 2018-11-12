package uow.cs.tv.gpe.activity.club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.todddavies.components.progressbar.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.adapter.club.ClubListAdapter;
import uow.cs.tv.gpe.config.Const;
import uow.cs.tv.gpe.model.City;
import uow.cs.tv.gpe.model.Club;
import uow.cs.tv.gpe.util.HttpUtils;
import uow.cs.tv.gpe.util.JsonParse;
import uow.cs.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/5/2018.
 */

public class ClubActivity extends AppCompatActivity {

    private ListView listView;
    private List<Club> mylist = new ArrayList<>();
    private City city;
    private ProgressWheel pw;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;
    private  ClubListAdapter clubListAdapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                clubListAdapter = new ClubListAdapter(ClubActivity.this, mylist);
                listView.setAdapter(clubListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x2) {
                clubListAdapter.notifyDataSetChanged();
                clubListAdapter = new ClubListAdapter(ClubActivity.this, mylist);
                Toast.makeText(ClubActivity.this, getString(R.string.loadfinish), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x1) {
                Toast.makeText(ClubActivity.this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x3) {
                Toast.makeText(ClubActivity.this, getString(R.string.last), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
        }
    };

    private void initList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtils hu = new HttpUtils();
                        String tmp = hu.executeHttpGet(Const.getclublist + city.getId() + "&"+ Const.PAGE + "0");
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
            }
        }, 1000);
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
                    Toast.makeText(ClubActivity.this, getString(R.string.last), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadMoreData() {
        pageCount++;
        clubListAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtils hu = new HttpUtils();
                        String tmp = hu.executeHttpGet(Const.getclublist + city.getId() + "&"+ Const.PAGE + "0");
                        JsonParse jp = new JsonParse(tmp);
                        List<Club> temp = jp.ParseJsonClub(tmp);
                        if (temp.size() != 0) {
                            mylist.addAll(temp);
                            Message msg = new Message();
                            msg.what = 0x2;
                            handler.sendMessage(msg);
                        } else {
                            lastItem = true;
                            Message msg = new Message();
                            msg.what = 0x3;
                            handler.sendMessage(msg);
                        }
                    }
                }).start();
            }
        }, 2000);
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lastItem = false;
                pageCount = 0;
                initList();
                clubListAdapter.notifyDataSetChanged();
                Toast.makeText(ClubActivity.this, getString(R.string.refresh), Toast.LENGTH_SHORT).show();
                if (mSwipeRefreshView.isRefreshing()) {
                    mSwipeRefreshView.setRefreshing(false);
                }
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pw = (ProgressWheel) findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        city = (City) getIntent().getSerializableExtra("city");

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
                Intent intent = new Intent(ClubActivity.this, ClubDetailActivity.class);
                intent.putExtra("club", mylist.get(i));
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