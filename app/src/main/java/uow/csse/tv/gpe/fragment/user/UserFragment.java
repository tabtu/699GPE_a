package uow.csse.tv.gpe.fragment.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.todddavies.components.progressbar.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.activity.act.ActivityDetailActivity;
import uow.csse.tv.gpe.activity.club.ClubDetailActivity;
import uow.csse.tv.gpe.activity.venue.VenueDetailActivity;
import uow.csse.tv.gpe.adapter.ActivityListAdapter;
import uow.csse.tv.gpe.adapter.club.ClubListAdapter;
import uow.csse.tv.gpe.adapter.user.UserListAdapter;
import uow.csse.tv.gpe.adapter.venue.VenueListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Activity;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.model.Venue;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/19/2018.
 */

public class UserFragment extends Fragment {

    private ListView listView;
    private List<User> userList = new ArrayList<>();
    private List<Venue> venueList = new ArrayList<>();
    private List<Club> clubList = new ArrayList<>();
    private List<Activity> activityList = new ArrayList<>();
    private String[] typeList;
    private String typeSelect;
    private String searchContent;
    private View view;
    private SwipeRefreshView mSwipeRefreshView;
    private UserListAdapter userListAdapter;
    private ClubListAdapter clubListAdapter;
    private VenueListAdapter venueListAdapter;
    private ActivityListAdapter activityListAdapter;
    private ProgressWheel pw;
    private SearchView searchView;
    private Spinner spinner;
    private int pageCount;
    private boolean lastItem = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x10) {
                userListAdapter = new UserListAdapter(getActivity(), userList);
                listView.setAdapter(userListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x11) {
                clubListAdapter = new ClubListAdapter(getActivity(), clubList);
                listView.setAdapter(clubListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x12) {
                venueListAdapter = new VenueListAdapter(getActivity(), venueList);
                listView.setAdapter(venueListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x13) {
                activityListAdapter = new ActivityListAdapter(getActivity(), activityList);
                listView.setAdapter(activityListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
            if (msg.what == 0x20) {
                userListAdapter.notifyDataSetChanged();
                userListAdapter = new UserListAdapter(getActivity(), userList);
                Toast.makeText(getActivity(), "Loading finish", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x21) {
                clubListAdapter = new ClubListAdapter(getActivity(), clubList);
                listView.setAdapter(clubListAdapter);
                Toast.makeText(getActivity(), "Loading finish", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x22) {
                venueListAdapter = new VenueListAdapter(getActivity(), venueList);
                listView.setAdapter(venueListAdapter);
                Toast.makeText(getActivity(), "Loading finish", Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x23) {
                activityListAdapter = new ActivityListAdapter(getActivity(), activityList);
                listView.setAdapter(activityListAdapter);
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
                String tmp = hu.executeHttpGet(Const.getallusers + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                userList = jp.ParseJsonUsers(tmp);
                if (userList != null) {
                    Message msg = new Message();
                    msg.what = 0x10;
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
                userList.clear();
                clubList.clear();
                venueList.clear();
                activityList.clear();
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
        userListAdapter.notifyDataSetChanged();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                JsonParse jp = new JsonParse();
                typeSelect = (String) spinner.getSelectedItem();
                if (typeSelect.equals("athlete") || typeSelect.equals("coach") || typeSelect.equals("referee")) {
                    String tmp = hu.executeHttpGet(Const.postsearchuser + "type=" + typeSelect + "&name=" + searchContent + "&" + Const.PAGE + pageCount);
                    List<User> temp = jp.ParseJsonUsers(tmp);
                    if (temp.size() != 0) {
                        userList.addAll(temp);
                        Message msg = new Message();
                        msg.what = 0x20;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x3;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("club") || typeSelect.equals("school")) {
                    String tmp = hu.executeHttpGet(Const.postsearchclub + "type=" + typeSelect + "&name=" + searchContent + "&" + Const.PAGE + pageCount);
                    List<Club> temp = jp.ParseJsonClub(tmp);
                    if (temp.size() != 0) {
                        clubList.addAll(temp);
                        Message msg = new Message();
                        msg.what = 0x21;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x3;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("venue")) {
                    String tmp = hu.executeHttpGet(Const.postsearchvenue + "name=" + searchContent + "&" + Const.PAGE + pageCount);
                    List<Venue> temp = jp.ParseJsonVenue(tmp);
                    if (temp.size() != 0) {
                        venueList.addAll(temp);
                        Message msg = new Message();
                        msg.what = 0x22;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x3;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("activity")) {
                    String tmp = hu.executeHttpGet(Const.postsearchactivity + "name=" + searchContent + "&" + Const.PAGE + pageCount);
                    List<Activity> temp = jp.ParseJsonActivity(tmp);
                    if (temp.size() != 0) {
                        activityList.addAll(temp);
                        Message msg = new Message();
                        msg.what = 0x23;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x3;
                        handler.sendMessage(msg);
                    }
                } else {
                    String tmp = hu.executeHttpGet(Const.getallusers + Const.PAGE + pageCount);
                    List<User> temp = jp.ParseJsonUsers(tmp);
                    if (temp.size() != 0) {
                        userList.addAll(temp);
                        Message msg = new Message();
                        msg.what = 0x20;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x3;
                        handler.sendMessage(msg);
                    }
                }
            }
        }).start();
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lastItem = false;
                pageCount = 0;
                newThread();
                userListAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Refresh finish", Toast.LENGTH_SHORT).show();

                if (mSwipeRefreshView.isRefreshing()) {
                    mSwipeRefreshView.setRefreshing(false);
                }
            }
        }, 2000);
    }

    private void setSpinner() {
        spinner = view.findViewById(R.id.find_spinner);
        List<String> typeData = new ArrayList<>();
        for (int i = 0; i < typeList.length; i++) {
            typeData.add(typeList[i]);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.adapter_citylist, typeData);
        spinner.setAdapter(arrayAdapter);
    }

    private void newThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                JsonParse jp = new JsonParse();
                typeSelect = (String) spinner.getSelectedItem();
                if (typeSelect.equals("athlete") || typeSelect.equals("coach") || typeSelect.equals("referee")) {
                    String tmp = hu.executeHttpGet(Const.postsearchuser + "type=" + typeSelect + "&name=" + searchContent + "&" + Const.PAGE + "0");
                    userList = jp.ParseJsonUsers(tmp);
                    if (userList != null) {
                        Message msg = new Message();
                        msg.what = 0x10;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x1;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("club") || typeSelect.equals("school")) {
                    String tmp = hu.executeHttpGet(Const.postsearchclub + "type=" + typeSelect + "&name=" + searchContent + "&" + Const.PAGE + "0");
                    clubList = jp.ParseJsonClub(tmp);
                    if (clubList != null) {
                        Message msg = new Message();
                        msg.what = 0x11;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x1;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("venue")) {
                    String tmp = hu.executeHttpGet(Const.postsearchvenue + "name=" + searchContent + "&" + Const.PAGE + "0");
                    venueList = jp.ParseJsonVenue(tmp);
                    if (venueList != null) {
                        Message msg = new Message();
                        msg.what = 0x12;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x1;
                        handler.sendMessage(msg);
                    }
                } else if (typeSelect.equals("activity")) {
                    String tmp = hu.executeHttpGet(Const.postsearchactivity + "name=" + searchContent + "&" + Const.PAGE + "0");
                    activityList = jp.ParseJsonActivity(tmp);
                    if (clubList != null) {
                        Message msg = new Message();
                        msg.what = 0x13;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x1;
                        handler.sendMessage(msg);
                    }
                } else {
                    initList();
                }
            }
        }).start();
    }

    private void setSearch() {
        searchView = (SearchView) view.findViewById(R.id.find_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchContent = s;
                newThread();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);

        pw = (ProgressWheel) view.findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        typeList = new String[]{"user","athlete","coach","referee","club","school","venue","activity"};
        setSpinner();
        setSearch();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.search_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).finish();
            }
        });

        listView = (ListView) view.findViewById(R.id.fieldslist);
        initList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                typeSelect = (String) spinner.getSelectedItem();
                if (typeSelect.equals("user") || typeSelect.equals("athlete") || typeSelect.equals("coach") || typeSelect.equals("referee")) {
                    Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                    intent.putExtra("user", userList.get(i));
                    intent.putExtra("currentuser", ((MainActivity) getActivity()).getUsr());
                    startActivity(intent);
                }
                else if (typeSelect.equals("club")) {
                    Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                    intent.putExtra("club", clubList.get(i));
                    startActivity(intent);
                }
                else if (typeSelect.equals("vanue")) {
                    Intent intent = new Intent(getActivity(), VenueDetailActivity.class);
                    intent.putExtra("venue", venueList.get(i));
                    startActivity(intent);
                }
                else if (typeSelect.equals("activity")) {
                    Intent intent = new Intent(getActivity(), ActivityDetailActivity.class);
                    intent.putExtra("act", activityList.get(i));
                    startActivity(intent);
                }
            }
        });

        mSwipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipelayout);
        mSwipeRefreshView.setColorSchemeResources(R.color.gpe,
                R.color.lightgpe);
        mSwipeRefreshView.setItemCount(20);
        mSwipeRefreshView.measure(0, 0);
        initSwipeFreshLayout();

        return view;
    }

    public static UserFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
