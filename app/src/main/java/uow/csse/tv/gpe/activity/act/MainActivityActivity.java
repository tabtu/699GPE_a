package uow.csse.tv.gpe.activity.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.ActivityListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Activity;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/19/2018.
 */

public class MainActivityActivity extends AppCompatActivity {

    private ListView listView;
    private List<Activity> mylist = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                ActivityListAdapter activityListAdapter = new ActivityListAdapter(MainActivityActivity.this, mylist);
                listView.setAdapter(activityListAdapter);
            } else {
                Toast.makeText(MainActivityActivity.this, "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.gethomeactivitylist + "&" + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonActivity(tmp);
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
                Intent intent = new Intent(MainActivityActivity.this, ActivityDetailActivity.class);
                intent.putExtra("act", mylist.get(i));
                startActivity(intent);
            }
        });
    }

}
