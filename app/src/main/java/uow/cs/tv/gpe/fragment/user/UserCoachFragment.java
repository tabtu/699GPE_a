package uow.cs.tv.gpe.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.cs.tv.gpe.R;

/**
 * Created by Vian on 2/9/2018.
 */

public class UserCoachFragment extends Fragment {

    View view;

    public UserCoachFragment() {

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_userdetail_coach,container,false);

        return view;
    }
}
