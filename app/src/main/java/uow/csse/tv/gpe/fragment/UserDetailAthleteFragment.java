package uow.csse.tv.gpe.fragment;

import android.annotation.SuppressLint;
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
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.activity.VenueDetailActivity;
import uow.csse.tv.gpe.adapter.PolygonAdapter;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/9/2018.
 */

public class UserDetailAthleteFragment extends Fragment {

    View view;

    public UserDetailAthleteFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_userdetail_athlete,container,false);

        PolygonAdapter poly = (PolygonAdapter) view.findViewById(R.id.poly);
        poly.setRealData(new int[]{1, 2, 3, 4, 5, 4});

        return view;
    }
}
