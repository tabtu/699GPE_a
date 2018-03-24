package uow.csse.tv.gpe.fragment.msg;

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
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.activity.msg.MessageReceiveDetailActivity;
import uow.csse.tv.gpe.adapter.msg.InMessageListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 2/21/2018.
 */

public class MessageInboxFragment extends Fragment {

    private View view;
    private ListView listView;
    private List<Msgs> mylist = new ArrayList<>();
    private User user;
    private ProgressWheel pw;
    private InMessageListAdapter inMessageListAdapter;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;

    private boolean shouldRefreshOnResume = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
                inMessageListAdapter = new InMessageListAdapter(getActivity(), mylist);
                listView.setAdapter(inMessageListAdapter);
            }
            if (msg.what == 0x2) {
                inMessageListAdapter.notifyDataSetChanged();
                inMessageListAdapter = new InMessageListAdapter(getActivity(), mylist);
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
                    exu = "rec=" + user.getId();
                }
                try {
                    HttpUtils hu = new HttpUtils();
                    String tmp = hu.executeHttpGet(Const.getinboxmsg + exu + "&" + Const.PAGE + "0");
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
        inMessageListAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String exu = "";
                        if(user != null) {
                            exu = "rec=" + user.getId();
                        }
                        HttpUtils hu = new HttpUtils();
                        String tmp = hu.executeHttpGet(Const.getinboxmsg + exu + "&" + Const.PAGE + pageCount);
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
                inMessageListAdapter.notifyDataSetChanged();
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

        pw = (ProgressWheel) view.findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        user = ((MainActivity)getActivity()).getUsr();
        initList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MessageReceiveDetailActivity.class);
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
