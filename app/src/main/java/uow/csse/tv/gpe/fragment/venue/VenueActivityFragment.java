package uow.csse.tv.gpe.fragment.venue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.act.MainActivityActivity;
import uow.csse.tv.gpe.activity.venue.VenueDetailActivity;
import uow.csse.tv.gpe.adapter.ActivityListAdapter;
import uow.csse.tv.gpe.adapter.venue.VenueActivityListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.activity.act.ActivityDetailActivity;
import uow.csse.tv.gpe.model.Activity;
import uow.csse.tv.gpe.model.Venue;
import uow.csse.tv.gpe.util.ListViewAutoHeight;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/25/2018.
 */

public class VenueActivityFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<Activity> mylist = new ArrayList<>();
//    private Activity activity;
    private Venue venue;

    public VenueActivityFragment() {

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
//        venue = (Venue) getArguments().getSerializable("venue");
        venue = ((VenueDetailActivity) getActivity()).getVenue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getvenueactivitylist + Const.ID + venue.getId() + "&" + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonActivity(tmp);
                Log.v("xxxxx1111",venue.getName()+"");
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
