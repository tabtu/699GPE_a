package uow.csse.tv.gpe.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.activity.RegisterActivity;
import uow.csse.tv.gpe.activity.UserActivity;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

public class LoginFragment extends Fragment{
    private EditText account;
    private EditText psd;
    private Button btn_login;
    private Button btn_register;
    private User usr;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private void saveStatus() {
        pref = getActivity().getSharedPreferences("status", Context.MODE_PRIVATE);
        editor = pref.edit();

        editor.putString("account", account.getText().toString());
        editor.putString("password", psd.getText().toString());
        editor.commit();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                saveStatus();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("user", usr);
                startActivity(intent);
                MainActivity parentActivity = (MainActivity) getActivity();
                parentActivity.finishActivity();
            } else {
                Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        account = (EditText) view.findViewById(R.id.login_account);
        psd = (EditText) view.findViewById(R.id.login_password);
        btn_login = (Button) view.findViewById(R.id.login_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String _account = account.getText().toString();
                        String _psd = psd.getText().toString();
                        String exu = "usr=" + _account + "&pwd=" + _psd;
                        Log.v("2", Const.loginlgtl + exu);
                        try {
                            HttpUtils hu = new HttpUtils();
                            String tmp = hu.executeHttpPost(Const.loginlgtl + exu);
                            Log.v("2", tmp);
                            JsonParse jp = new JsonParse(tmp);
                            usr  = jp.ParseJsonUser(tmp);
                            Log.v("2", usr.getName());
                            if (usr == null) {
                                Message msg = new Message();
                                msg.what = 0x99;
                                handler.sendMessage(msg);
                            } else {
                                Message msg = new Message();
                                msg.what = 0x0;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            Log.v("2", e.getMessage());
//                            Message msg = new Message();
//                            msg.what = 0x99;
//                            handler.sendMessage(msg);
                        }
                    }
                }).start();
            }
        });

        btn_register = (Button) view.findViewById(R.id.login_regis);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static LoginFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
