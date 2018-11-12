package uow.cs.tv.gpe.fragment.club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.activity.club.SchoolDetailActivity;
import uow.cs.tv.gpe.adapter.ActivityListAdapter;
import uow.cs.tv.gpe.config.Const;
import uow.cs.tv.gpe.activity.act.ActivityDetailActivity;
import uow.cs.tv.gpe.model.Activity;
import uow.cs.tv.gpe.model.Club;
import uow.cs.tv.gpe.util.ListViewAutoHeight;
import uow.cs.tv.gpe.util.HttpUtils;
import uow.cs.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/26/2018.
 */

public class SchoolActivityFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<Activity> mylist = new ArrayList<>();
    private Club school;

    public SchoolActivityFragment() {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                ActivityListAdapter activityListAdapter = new ActivityListAdapter(getActivity(), mylist);
                listView.setAdapter(activityListAdapter);
                ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
                listViewAutoHeight.setListViewHeightBasedOnChildren(listView);
            } else {
                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist,container,false);
        listView = (ListView) view.findViewById(R.id.simplelist_list);

        school=((SchoolDetailActivity) getActivity()).getSchool();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getclubactivitylist + Const.ID + school.getId() + "&" + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonActivity(tmp);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ActivityDetailActivity.class);
                intent.putExtra("act", mylist.get(i));
//                intent.putExtra("venue",venue);
                startActivity(intent);
            }
        });

        return view;
    }

}
