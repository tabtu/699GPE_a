package uow.csse.tv.gpe.fragment.msg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import uow.csse.tv.gpe.activity.msg.MessageReceiveDetailActivity;
import uow.csse.tv.gpe.adapter.msg.InMessageListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/21/2018.
 */

public class MessageInboxFragment extends Fragment {

    private View view;
    private ListView listView;
    private List<Msgs> mylist = new ArrayList<>();
    private User user;

    private boolean shouldRefreshOnResume = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                InMessageListAdapter inMessageListAdapter = new InMessageListAdapter(getActivity(), mylist);
                listView.setAdapter(inMessageListAdapter);
            } else {
                Toast.makeText(getActivity(), "No Message", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String exu = "";
//                String exu = "rec=" + userId;
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
                        msg.what = 0x99;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        listView = (ListView) view.findViewById(R.id.simplelist_list);
        user = ((MainActivity)getActivity()).getUsr();
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MessageReceiveDetailActivity.class);
                intent.putExtra("msgin", mylist.get(i));
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            init();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

}
