package uow.csse.tv.gpe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.ClubDetailActivity;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.adapter.PolygonAdapter;

/**
 * Created by Vian on 2/9/2018.
 */

public class UserDetailAthleteFragment extends Fragment {

    private View view;
    private UserDetailActivity userDetailActivity;
    private ListView listView;
    private Handler handler;

    public UserDetailAthleteFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userDetailActivity = (UserDetailActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_userdetail_athlete,container,false);

        listView = (ListView) view.findViewById(R.id.fieldslist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                startActivity(intent);
            }
        });

        PolygonAdapter poly = (PolygonAdapter) view.findViewById(R.id.poly);
        poly.setRealData(new int[]{1, 2, 3, 4, 5, 4});

        return view;
    }
}
