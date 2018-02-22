package uow.csse.tv.gpe.fragment.msg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.ActivityFragment;
import uow.csse.tv.gpe.model.Msg;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

public class MessageFragment extends Fragment{

    private View view;
    private List<uow.csse.tv.gpe.model.Message> inlist = new ArrayList<>();
    private List<uow.csse.tv.gpe.model.Message> outlist = new ArrayList<>();
//    private int userId;
    private User user;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                setFragment();
            } else {
//                setFragment();
                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setFragment() {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.message_tablayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.message_viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getFragmentManager());

        MessageInboxFragment mif = new MessageInboxFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("msgin", (Serializable) inlist);
        mif.setArguments(bundle1);
        adapter.AddFragment(mif, "Inbox");

        MessageOutboxFragment mof = new MessageOutboxFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("msgout", (Serializable) outlist);
        mof.setArguments(bundle2);
        adapter.AddFragment(mof, "Outbox");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        user = ((MainActivity)getActivity()).getUsr();

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
                    inlist  = jp.ParseJsonMsg(tmp);
                    if (inlist == null) {
                        Message msg = new Message();
                        msg.what = 0x99;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x0;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                }
            }
        }).start();

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
                    outlist  = jp.ParseJsonMsg(tmp);
                    if (outlist == null) {
                        Message msg = new Message();
                        msg.what = 0x99;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 0x0;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                }
            }
        }).start();

        setFragment();
        return view;
    }

    public static MessageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
