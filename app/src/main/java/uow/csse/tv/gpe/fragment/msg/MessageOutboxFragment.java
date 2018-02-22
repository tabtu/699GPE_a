package uow.csse.tv.gpe.fragment.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.school.ClubDetailActivity;
import uow.csse.tv.gpe.adapter.ClubListAdapter;
import uow.csse.tv.gpe.adapter.MessageListAdapter;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.ListViewAutoHeight;

/**
 * Created by Vian on 2/21/2018.
 */

public class MessageOutboxFragment extends Fragment {

    private View view;
    private ListView listView;
    private int userId;

    public MessageOutboxFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        listView = (ListView) view.findViewById(R.id.userdetail_athelete_clublist);

//        userId = (int)getArguments().getSerializable("userid");
//
//        MessageListAdapter messageListAdapter = new MessageListAdapter(getActivity(), user.getAthlete().getClubs());
//        listView.setAdapter(clubListAdapter);
//        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
//        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
//                intent.putExtra("club", user.getAthlete().getClubs().get(i));
//                startActivity(intent);
//            }
//        });
        return view;
    }
}
