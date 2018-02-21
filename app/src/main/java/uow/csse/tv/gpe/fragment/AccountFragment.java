package uow.csse.tv.gpe.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.model.User;

public class AccountFragment extends Fragment {

    private Button btn_signout;

    private void setData(View view) {
        btn_signout = view.findViewById(R.id.account_signout);
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
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
