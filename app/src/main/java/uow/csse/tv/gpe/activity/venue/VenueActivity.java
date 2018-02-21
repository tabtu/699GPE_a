package uow.csse.tv.gpe.activity.venue;

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
import uow.csse.tv.gpe.adapter.VenueListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.City;
import uow.csse.tv.gpe.model.Venue;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/5/2018.
 */

public class VenueActivity extends AppCompatActivity {

    private ListView listView;
    private List<Venue> mylist = new ArrayList<>();
    private City city;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        if (msg.what == 0x0) {
            VenueListAdapter venueListAdapter = new VenueListAdapter(VenueActivity.this, mylist);
            listView.setAdapter(venueListAdapter);
        } else {
            Toast.makeText(VenueActivity.this, "empty list", Toast.LENGTH_SHORT).show();
        }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        city = (City) getIntent().getSerializableExtra("city");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Fields");
        listView = (ListView) findViewById(R.id.fieldslist);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getvenuelist + city.getId() + "&"+ Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                mylist = jp.ParseJsonVenue(tmp);
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
                Intent intent = new Intent(VenueActivity.this, VenueDetailActivity.class);
                intent.putExtra("venue", mylist.get(i));
                startActivity(intent);
            }
        });
    }
}