package uow.csse.tv.gpe.activity.user;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.UserListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/5/2018.
 */

public class UserActivity extends AppCompatActivity {

    private ListView listView;
    private List<User> mylist = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                UserListAdapter userListAdapter = new UserListAdapter(UserActivity.this, mylist);
                listView.setAdapter(userListAdapter);
            } else {
                Toast.makeText(UserActivity.this, "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView) findViewById(R.id.fieldslist);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getuserlist);
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonUsers(tmp);
                if (mylist != null) {
                    Message msg = new Message();
                    msg.what = 0x0;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0x1;
                    handler.sendMessage(msg);
                }
            }
        }).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(UserActivity.this, UserDetailActivity.class);
                intent.putExtra("user", mylist.get(i));
                startActivity(intent);
            }
        });
    }
}