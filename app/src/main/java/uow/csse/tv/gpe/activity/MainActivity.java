package uow.csse.tv.gpe.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.fragment.AccountFragment;
import uow.csse.tv.gpe.fragment.FollowFragment;
import uow.csse.tv.gpe.fragment.HomeFragment;
import uow.csse.tv.gpe.fragment.MessageFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "HOME").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "FOLLOW").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_msg_white_24dp, "MESSAGE").setActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.ic_account_white_24dp, "ACCOUNT").setActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("Home"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("HOME"));
        fragments.add(FollowFragment.newInstance("FOLLOW"));
        fragments.add(MessageFragment.newInstance("MESSAGE"));
        fragments.add(AccountFragment.newInstance("ACCOUNT"));
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
