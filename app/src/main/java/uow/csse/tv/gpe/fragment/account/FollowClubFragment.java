package uow.csse.tv.gpe.fragment.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.csse.tv.gpe.R;

/**
 * Created by Vian on 3/5/2018.
 */

public class FollowClubFragment extends Fragment {

    View view;

    public FollowClubFragment() {

    }

    private void setData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        setData();
        return view;
    }
}
