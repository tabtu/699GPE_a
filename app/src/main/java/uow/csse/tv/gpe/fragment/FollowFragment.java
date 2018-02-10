package uow.csse.tv.gpe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.csse.tv.gpe.R;

public class FollowFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    public static FollowFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(args);
        return fragment;
    }
}