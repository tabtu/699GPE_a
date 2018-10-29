package uow.cs.tv.gpe.fragment.msg;

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

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.activity.MainActivity;
import uow.cs.tv.gpe.activity.msg.MessageSendDetailActivity;
import uow.cs.tv.gpe.adapter.msg.OutMessageListAdapter;
import uow.cs.tv.gpe.config.Const;
import uow.cs.tv.gpe.model.Msgs;
import uow.cs.tv.gpe.model.User;
import uow.cs.tv.gpe.util.HttpUtils;
import uow.cs.tv.gpe.util.JsonParse;
import uow.cs.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/21/2018.
 */

public class MessageOutboxFragment extends Fragment {

    private View view;
    private ListView listView;
    private List<Msgs> mylist = new ArrayList<>();
    private User user;
    private OutMessageListAdapter outMessageListAdapter;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;

    private boolean shouldRefreshOnResume = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                outMessageListAdapter = new OutMessageListAdapter(getActivity(), mylist);
                listView.setAdapter(outMessageListAdapter);
            }
            if (msg.what == 0x2) {
                outMessageListAdapter.notifyDataSetChanged();
                outMessageListAdapter = new OutMessageListAdapter(getActivity(), mylist);
                Toast.makeText(getActivity(), getString(R.string.loadfinish), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x1) {
                Toast.makeText(getActivity(), getString(R.string.empty), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
            if (msg.what == 0x3) {
                Toast.makeText(getActivity(), getString(R.string.last), Toast.LENGTH_SHORT).show();
                mSwipeRefreshView.setLoading(false);
            }
        }
    };

    private void initList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String exu = "";
                if(user != null) {
                    exu = "sen=" + user.getId();
                }
                try {
                    HttpUtils hu = new HttpUtils();
                    String tmp = hu.executeHttpGet(Const.getoutboxmsg + exu + "&" + Const.PAGE + "0");
                    JsonParse jp = new JsonParse(tmp);
                    mylist  = jp.ParseJsonMsg(tmp);
                    if (mylist == null) {
                        android.os.Message msg = new android.os.Message();
                        msg.what = 0x1;
                        handler.sendMessage(msg);
                    } else {
                        android.os.Message msg = new android.os.Message();
                        msg.what = 0x0;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void initSwipeFreshLayout() {
        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mylist.clear();
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
                    Toast.makeText(getActivity(), getString(R.string.last), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadMoreData() {
        pageCount++;
        outMessageListAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String exu = "";
                        if(user != null) {
                            exu = "sen=" + user.getId();
                        }
                        HttpUtils hu = new HttpUtils();
                        String tmp = hu.executeHttpGet(Const.getoutboxmsg + exu + "&" + Const.PAGE + "0");
                        JsonParse jp = new JsonParse(tmp);
                        List<Msgs> temp = jp.ParseJsonMsg(tmp);
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
                outMessageListAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), getString(R.string.refresh), Toast.LENGTH_SHORT).show();

                if (mSwipeRefreshView.isRefreshing()) {
                    mSwipeRefreshView.setRefreshing(false);
                }
            }
        }, 2000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        listView = (ListView) view.findViewById(R.id.simplelist_list);

        ProgressWheel pw = view.findViewById(R.id.pw_spinner);
        pw.setVisibility(View.GONE);

        user = ((MainActivity)getActivity()).getUsr();
        initList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MessageSendDetailActivity.class);
                intent.putExtra("msgin", mylist.get(i));
                startActivity(intent);
            }
        });

        mSwipeRefreshView = (SwipeRefreshView) view.findViewById(R.id.swipelayout);
        mSwipeRefreshView.setColorSchemeResources(R.color.gpe, R.color.lightgpe);
//        mSwipeRefreshView.setItemCount(20);
        mSwipeRefreshView.measure(0, 0);
        initSwipeFreshLayout();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            initList();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }
}
