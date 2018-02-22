package uow.csse.tv.gpe.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.AccountFragment;
import uow.csse.tv.gpe.fragment.HomeFragment;
import uow.csse.tv.gpe.fragment.LoginFragment;
import uow.csse.tv.gpe.fragment.msg.MessageFragment;
import uow.csse.tv.gpe.fragment.user.UserFragment;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private ArrayList<Fragment> fragments;
    private User usr;
    private int status = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {

            } else {
                Toast.makeText(MainActivity.this, "No User", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        usr = (User)getIntent().getSerializableExtra("user");

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("status", MODE_PRIVATE);
                String _account = pref.getString("account","");
                String _psd = pref.getString("password","");

                String exu = "usr=" + _account + "&pwd=" + _psd;
                try {
                    HttpUtils hu = new HttpUtils();
                    String tmp = hu.executeHttpPost(Const.loginlgtl + exu);
                    JsonParse jp = new JsonParse(tmp);
                    usr  = jp.ParseJsonUser(tmp);
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
                }
            }
        }).start();

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "HOME").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_white_24dp, "FIND").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_msg_white_24dp, "MESSAGE").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_account_white_24dp, "ACCOUNT").setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    public User getUsr() {
        return usr;
    }

    public void finishActivity() {
        finish();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("Home"));
        transaction.commit();
    }

    private void status() {
        SharedPreferences pref = getSharedPreferences("status", MODE_PRIVATE);
        String outAccount = pref.getString("account","");
        String outPsd = pref.getString("password","");
        if (outAccount.length() != 0 && outPsd.length() != 0) {
            status = 1;
        } else { status = 0;}
    }

    private ArrayList<Fragment> getFragments() {
        status();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("HOME"));
        if (status == 0) {
            fragments.add(LoginFragment.newInstance("FIND"));
            fragments.add(LoginFragment.newInstance("MESSAGE"));
            fragments.add(LoginFragment.newInstance("ACCOUNT"));
        } else {
            if (status ==1) {
                fragments.add(UserFragment.newInstance("FIND"));
                fragments.add(MessageFragment.newInstance("MESSAGE"));
                fragments.add(AccountFragment.newInstance("ACCOUNT"));
        } else {
            fragments.add(LoginFragment.newInstance("FIND"));
            fragments.add(LoginFragment.newInstance("MESSAGE"));
            fragments.add(LoginFragment.newInstance("ACCOUNT"));
        }
    }
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.replace(R.id.layFrame, fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
