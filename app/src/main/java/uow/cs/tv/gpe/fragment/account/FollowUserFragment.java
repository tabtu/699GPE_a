package uow.cs.tv.gpe.fragment.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.cs.tv.gpe.R;

/**
 * Created by Vian on 3/5/2018.
 */

public class FollowUserFragment extends Fragment {

    View view;

    public FollowUserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        return view;
    }
}
