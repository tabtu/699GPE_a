package uow.csse.tv.gpe.fragment.club;

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
import uow.csse.tv.gpe.activity.club.SchoolDetailActivity;
import uow.csse.tv.gpe.adapter.venue.VenueNewsListAdapter;
import uow.csse.tv.gpe.activity.venue.VenueNewsDetailActivity;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.util.ListViewAutoHeight;
import uow.csse.tv.gpe.model.VNews;

/**
 * Created by Vian on 2/26/2018.
 */

public class SchoolNewsFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<VNews> mylist = new ArrayList<>();
    private Club school;

    public SchoolNewsFragment() {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                VenueNewsListAdapter venueNewsListAdapter = new VenueNewsListAdapter(getActivity(), mylist);
                listView.setAdapter(venueNewsListAdapter);
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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpUtils hu = new HttpUtils();
//                String tmp = hu.executeHttpGet(Const.getvenuenewslist + Const.ID + club.getId() + "&" + Const.PAGE + "0");
//                JsonParse jp = new JsonParse(tmp);
//                mylist = jp.ParseJsonVNews(tmp);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), VenueNewsDetailActivity.class);
                intent.putExtra("vnews", mylist.get(i));
                startActivity(intent);
            }
        });

        return view;
    }

}
