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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.adapter.user.UserListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/19/2018.
 */

public class UserFragment extends Fragment {

    private ListView listView;
    private List<User> mylist = new ArrayList<>();
    private View view;
    private SwipeRefreshView mSwipeRefreshView;
    private UserListAdapter userListAdapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                userListAdapter = new UserListAdapter(getActivity(), mylist);
                listView.setAdapter(userListAdapter);
            } else {
                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
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
                mylist = jp.ParseJsonUsers(tmp);
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
//        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipelayout);
//        swipeRefreshLayout.setColorSchemeResources(R.color.gpe,R.color.black);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                (new Handler()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//
//                        initList();
//                    }
//                },3000);
//            }
//        });

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });


        // 设置下拉加载更多
        mSwipeRefreshView.setOnLoadMoreListener(new SwipeRefreshView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                loadMoreData();
            }
        });
    }

    private void loadMoreData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mylist.clear();
//                mylist.addAll(DataResource.getMoreData());
                Toast.makeText(getActivity(), "Loading finish", Toast.LENGTH_SHORT).show();

                // 加载完数据设置为不加载状态，将加载进度收起来
                mSwipeRefreshView.setLoading(false);
            }
        }, 2000);
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mylist.clear();
//                mylist.addAll(DataResource.getData());
                initList();
                userListAdapter.notifyDataSetChanged();

                Toast.makeText(getActivity(), "Refresh finish", Toast.LENGTH_SHORT).show();

                if (mSwipeRefreshView.isRefreshing()) {
                    mSwipeRefreshView.setRefreshing(false);
                }
            }
        }, 2000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_search, container, false);

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
                Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                intent.putExtra("user",mylist.get(i));
                intent.putExtra("currentuser",((MainActivity)getActivity()).getUsr());
                startActivity(intent);
            }
        });

        mSwipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipelayout);
        mSwipeRefreshView.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_blue_bright, R.color.colorPrimaryDark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark, android.R.color.holo_purple);
        mSwipeRefreshView.setItemCount(8);
        // 手动调用,通知系统去测量
        mSwipeRefreshView.measure(0, 0);
//        mSwipeRefreshView.setRefreshing(true);

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
