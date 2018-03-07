package uow.csse.tv.gpe.activity.club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.todddavies.components.progressbar.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.UserDetailActivity;
import uow.csse.tv.gpe.activity.club.ClubDetailActivity;
import uow.csse.tv.gpe.adapter.user.UserListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.Coach;
import uow.csse.tv.gpe.model.Referee;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.SwipeRefreshView;

/**
 * Created by Vian on 3/6/2018.
 */

public class ClubMemberActivity extends AppCompatActivity{

    private ListView listView;
    private List<User> myList = new ArrayList<>();
    private Club club;
    private ProgressWheel pw;
    private SwipeRefreshView mSwipeRefreshView;
    private int pageCount;
    private boolean lastItem = false;
    private UserListAdapter userListAdapter;
    private String type;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                userListAdapter = new UserListAdapter(ClubMemberActivity.this, myList);
                listView.setAdapter(userListAdapter);
                pw.stopSpinning();
                pw.setVisibility(View.GONE);
            }
//            if (msg.what == 0x2) {
//                userListAdapter.notifyDataSetChanged();
//                userListAdapter = new UserListAdapter(ClubMemberActivity.this, myList);
//                Toast.makeText(ClubMemberActivity.this, "Loading finish", Toast.LENGTH_SHORT).show();
//                mSwipeRefreshView.setLoading(false);
//            }
//            if (msg.what == 0x1) {
//                Toast.makeText(ClubMemberActivity.this, "empty list", Toast.LENGTH_SHORT).show();
//                mSwipeRefreshView.setLoading(false);
//            }
//            if (msg.what == 0x3) {
//                Toast.makeText(ClubMemberActivity.this, "Last item", Toast.LENGTH_SHORT).show();
//                mSwipeRefreshView.setLoading(false);
//            }
            if (msg.what == 0x99 || msg.what == 0x98) {
                Toast.makeText(ClubMemberActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        type = (String) getIntent().getSerializableExtra("type");
        club = (Club) getIntent().getSerializableExtra("club");

        pw = (ProgressWheel) findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpUtils hu = new HttpUtils();
                    if (type.equals("coach")) {
                        for (Coach c : club.getCoaches()) {
                            String tmp = hu.executeHttpGet(Const.getuserlist + "type=" + type + "&id=" + c.getId());
                            JsonParse jp = new JsonParse(tmp);
                            User temp = jp.ParseJsonUser(tmp);
                            myList.add(temp);
                        }
                    } else if (type.equals("referee")) {
                        for (Referee r : club.getReferees()) {
                            String tmp = hu.executeHttpGet(Const.getuserlist + "type=" + type + "&id=" + r.getId());
                            JsonParse jp = new JsonParse(tmp);
                            User temp = jp.ParseJsonUser(tmp);
                            myList.add(temp);
                        }
                    } else if (type.equals("member")) {
                        myList = club.getUsers();
                    }
                    else {
                        Message msg = new Message();
                        msg.what = 0x99;
                        handler.sendMessage(msg);
                    }
                    if (myList.size() != 0) {
                        Message msg = new Message();
                        msg.what = 0x0;
                        handler.sendMessage(msg);
                    }
//                    Log.v("tmp",myList.size()+"");
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = 0x98;
                    handler.sendMessage(msg);
                }
            }
        }).start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.fieldslist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ClubMemberActivity.this, UserDetailActivity.class);
                intent.putExtra("user", myList.get(i));
                startActivity(intent);
            }
        });

    }
}
