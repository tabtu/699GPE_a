package uow.csse.tv.gpe.fragment.user;

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
import uow.csse.tv.gpe.activity.ClubDetailActivity;
import uow.csse.tv.gpe.adapter.ClubListAdapter;
import uow.csse.tv.gpe.adapter.PolygonAdapter;
import uow.csse.tv.gpe.util.ListViewAutoHeight;
import uow.csse.tv.gpe.model.User;

/**
 * Created by Vian on 2/9/2018.
 */

public class UserAthleteFragment extends Fragment {

    private View view;
    private ListView listView;
    private User user;

    public UserAthleteFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_userdetail_athlete,container,false);
        listView = (ListView) view.findViewById(R.id.userdetail_athelete_clublist);

        user = (User)getArguments().getSerializable("user");

        ClubListAdapter clubListAdapter = new ClubListAdapter(getActivity(), user.getAthlete().getClubs());
        listView.setAdapter(clubListAdapter);
        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                intent.putExtra("club", user.getAthlete().getClubs().get(i));
                startActivity(intent);
            }
        });

        PolygonAdapter poly = (PolygonAdapter) view.findViewById(R.id.poly);
        poly.setRealData(user.getAthlete().getGraphicInts());
        return view;
    }
}
