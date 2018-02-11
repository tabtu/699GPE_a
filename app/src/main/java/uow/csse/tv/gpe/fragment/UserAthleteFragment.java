package uow.csse.tv.gpe.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import uow.csse.tv.gpe.activity.ClubDetailActivity;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.adapter.ClubListAdapter;
import uow.csse.tv.gpe.adapter.PolygonAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.config.ListViewAutoHeight;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/9/2018.
 */

public class UserAthleteFragment extends Fragment {

    private View view;
    private ListView listView;
//    private List<Club> mylist = new ArrayList<>();
    private User user;

    public UserAthleteFragment() {

    }

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x0) {
//                //pd.dismiss();
//                ClubListAdapter clubListAdapter = new ClubListAdapter(getActivity(), mylist);
//                listView.setAdapter(clubListAdapter);
//                ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
//                listViewAutoHeight.setListViewHeightBasedOnChildren(listView);
//            } else {
//                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_userdetail_athlete,container,false);
        listView = (ListView) view.findViewById(R.id.list);

        user = (User)getArguments().getSerializable("user");
        Log.v("u", user.getName());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpUtils hu = new HttpUtils();
//                String tmp = hu.executeHttpGet(Const.getclublist);
//                JsonParse jp = new JsonParse(tmp);
//                mylist = jp.ParseJsonClub(tmp);
//                if (mylist != null) {
//                    Message msg = new Message();
//                    msg.what = 0x0;
//                    handler.sendMessage(msg);
//                } else {
//                    Message msg = new Message();
//                    msg.what = 0x1;
//                    handler.sendMessage(msg);
//                }
//            }
//        }).start();


        ClubListAdapter clubListAdapter = new ClubListAdapter(getActivity(), user.getAthlete().getClubs());
        listView.setAdapter(clubListAdapter);
        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                startActivity(intent);
            }
        });

        PolygonAdapter poly = (PolygonAdapter) view.findViewById(R.id.poly);
//        poly.setRealData(new int[]{1, 2, 3, 4, 5, 4});
        poly.setRealData(user.getAthlete().getGraphicInts());
        return view;
    }
}
