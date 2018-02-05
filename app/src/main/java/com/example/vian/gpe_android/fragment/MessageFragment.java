package com.example.vian.gpe_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vian.gpe_android.R;

public class MessageFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static MessageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
