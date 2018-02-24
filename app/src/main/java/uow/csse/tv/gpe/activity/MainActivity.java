package uow.csse.tv.gpe.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
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

/**
 * Created by Vian on 2/22/2018.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private FrameLayout mFrameLayout;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private AccountFragment accountFragment;
    private UserFragment userFragment;
    private LoginFragment loginFragment;
    private Fragment currentFragment=new Fragment();
    private FragmentManager fm = getSupportFragmentManager();

    private User usr;
    private int status = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
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
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mFrameLayout = findViewById(R.id.layFrame);
        status();
        loginFragment = LoginFragment.newInstance("Login");
        InitNavigationBar();
        setDefaultFragment();

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
                        android.os.Message msg = new android.os.Message();
                        msg.what = 0x99;
                        handler.sendMessage(msg);
                    } else {
                        android.os.Message msg = new android.os.Message();
                        msg.what = 0x0;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public User getUsr() {
        return usr;
    }

    public void finishActivity() {
        finish();
    }

    private void status() {
        SharedPreferences pref = getSharedPreferences("status", MODE_PRIVATE);
        String outAccount = pref.getString("account","");
        String outPsd = pref.getString("password","");
        if (outAccount.length() != 0 && outPsd.length() != 0) {
            status = 1;
        } else { status = 0;}
    }

    private void InitNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "HOME").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_white_24dp, "FIND").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_msg_white_24dp, "MESSAGE").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_account_white_24dp, "ACCOUNT").setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void showFragment(Fragment fragment) {
        if (currentFragment!=fragment) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.layFrame, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = HomeFragment.newInstance("Home");
        showFragment(homeFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("Home");
                }
                if (status == 0) {
                    showFragment(loginFragment);
                } else {
                    showFragment(homeFragment);
                }
                break;
            case 1:
                if (userFragment == null) {
                    userFragment = UserFragment.newInstance("Find");
                }
                if (status == 0) {
                    showFragment(loginFragment);
                } else {
                    showFragment(userFragment);
                }
                break;
            case 2:
                if (messageFragment == null) {
                    messageFragment = MessageFragment.newInstance("Message");
                }
                if (status == 0) {
                    showFragment(loginFragment);
                } else {
                    showFragment(messageFragment);
//                    FragmentTransaction transaction = fm.beginTransaction();
//                    transaction.replace(R.id.layFrame,messageFragment).commit();
                }
            break;
            case 3:
                if (accountFragment == null) {
                    accountFragment = AccountFragment.newInstance("Account");
                }
                if (status == 0) {
                    showFragment(loginFragment);
                } else {
                    showFragment(accountFragment);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

}
