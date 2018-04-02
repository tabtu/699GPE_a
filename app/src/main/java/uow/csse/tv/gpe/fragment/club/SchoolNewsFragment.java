package uow.csse.tv.gpe.fragment.club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import uow.csse.tv.gpe.activity.club.ClubNewsDetailActivity;
import uow.csse.tv.gpe.activity.club.SchoolDetailActivity;
import uow.csse.tv.gpe.adapter.club.SchoolNewsListAdapter;
import uow.csse.tv.gpe.adapter.venue.VenueNewsListAdapter;
import uow.csse.tv.gpe.activity.venue.VenueNewsDetailActivity;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.CNews;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.ListViewAutoHeight;
import uow.csse.tv.gpe.model.VNews;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/26/2018.
 */

public class SchoolNewsFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<CNews> mylist = new ArrayList<>();
    private ProgressWheel pw;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;
    private SchoolNewsListAdapter schoolNewsListAdapter;
    private Club school;

    public SchoolNewsFragment() {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
                schoolNewsListAdapter = new SchoolNewsListAdapter(getActivity(), mylist);
                listView.setAdapter(schoolNewsListAdapter);
                ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
                listViewAutoHeight.setListViewHeightBasedOnChildren(listView);
            }
            if (msg.what == 0x2) {
                schoolNewsListAdapter.notifyDataSetChanged();
                schoolNewsListAdapter = new SchoolNewsListAdapter(getActivity(), mylist);
                Toast.makeText(getActivity(), "Loading finish", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x1) {
                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x3) {
                Toast.makeText(getActivity(), "Last item", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
        }
    };

    private void initList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getcsnewslist + Const.ID + school.getId() + "&" + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonCNews(tmp);
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
                    Toast.makeText(getActivity(), "Last item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadMoreData() {
        pageCount++;
        schoolNewsListAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtils hu = new HttpUtils();
                        String tmp = hu.executeHttpGet(Const.getcsnewslist + Const.ID + school.getId() + "&" + Const.PAGE + "0");
                        JsonParse jp = new JsonParse(tmp);
                        List<CNews> temp = jp.ParseJsonCNews(tmp);
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
                schoolNewsListAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Refresh finish", Toast.LENGTH_SHORT).show();
                if (mSwipeRefreshView.isRefreshing()) {
                    mSwipeRefreshView.setRefreshing(false);
                }
            }
        }, 2000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist,container,false);
        listView = (ListView) view.findViewById(R.id.simplelist_list);

        pw = (ProgressWheel) view.findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        school=((SchoolDetailActivity) getActivity()).getSchool();

        initList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ClubNewsDetailActivity.class);
                intent.putExtra("cnews", mylist.get(i));
                startActivity(intent);
            }
        });

        mSwipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipelayout);
        mSwipeRefreshView.setColorSchemeResources(R.color.gpe, R.color.lightgpe);
//        mSwipeRefreshView.setItemCount(2);
        mSwipeRefreshView.measure(0, 0);
        initSwipeFreshLayout();

        return view;
    }

}
