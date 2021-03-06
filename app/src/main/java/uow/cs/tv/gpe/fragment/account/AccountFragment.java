package uow.cs.tv.gpe.fragment.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.activity.account.FollowActivity;
import uow.cs.tv.gpe.activity.MainActivity;
import uow.cs.tv.gpe.activity.account.SettingActivity;
import uow.cs.tv.gpe.activity.scan.OpenScannerActivity;
import uow.cs.tv.gpe.activity.scan.ScannerActivity;
import uow.cs.tv.gpe.model.User;

public class AccountFragment extends Fragment {

    private User user;

    private void setData(View view) {
        TextView name = view.findViewById(R.id.account_name);
        if (user.getName() == null) {
            String userName = "User" + user.getId().substring(10, 15);
            name.setText(userName);
        } else {
            name.setText(user.getName());
        }

        Button btn_signout = view.findViewById(R.id.account_signout);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userSettings = getActivity().getSharedPreferences("status", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userSettings.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout btn_follow = view.findViewById(R.id.account_follow);
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FollowActivity.class);
                intent.putExtra("user",((MainActivity) getActivity()).getUsr());
                startActivity(intent);
            }
        });

        LinearLayout btn_setting = view.findViewById(R.id.account_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).finish();
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout btn_scanner = view.findViewById(R.id.account_scanner);
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OpenScannerActivity.class);
                intent.putExtra("usid", user.getId());
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.account_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).finish();
            }
        });

        user = ((MainActivity)getActivity()).getUsr();

        setData(view);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static AccountFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
