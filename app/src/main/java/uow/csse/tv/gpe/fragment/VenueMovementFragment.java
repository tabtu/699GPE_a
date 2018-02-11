package uow.csse.tv.gpe.fragment;

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

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.ClubDetailActivity;
import uow.csse.tv.gpe.adapter.ClubListAdapter;
import uow.csse.tv.gpe.adapter.VenueMovementListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.VNews;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/5/2018.
 */

public class VenueMovementFragment extends Fragment {
    View view;
    private ListView listView;
    private List<VNews> mylist = new ArrayList<>();

    public VenueMovementFragment() {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                VenueMovementListAdapter venueMovementListAdapter = new VenueMovementListAdapter(getActivity(), mylist);
                listView.setAdapter(venueMovementListAdapter);
            } else {
                Toast.makeText(getActivity(), "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_news,container,false);

        listView = (ListView) view.findViewById(R.id.list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getvenuenewslist);
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonVNews(tmp);
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
                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
