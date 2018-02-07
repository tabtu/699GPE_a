package uow.csse.tv.gpe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.FieldListAdapter;
import uow.csse.tv.gpe.model.Venue;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/5/2018.
 */

public class FieldsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                //pd.dismiss();
                FieldListAdapter fieldListAdapter = new FieldListAdapter(FieldsActivity.this, mylist);
                listView.setAdapter(fieldListAdapter);
            } else {
                Toast.makeText(FieldsActivity.this, "empty list", Toast.LENGTH_SHORT).show();
            }
        }
    };



    private List<Venue> mylist = new ArrayList<>();

//    private void createVenue() {
//        for (int i = 0; i < 2; i++) {
//            Venue tmp = new Venue();
//            tmp.setName("123");
//            tmp.setAddress("adfg");
//            tmp.setTel("45745754745");
//            mylist.add(tmp);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        //createVenue();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Fields");
        listView = (ListView) findViewById(R.id.fieldslist);
//        FieldListAdapter fieldListAdapter = new FieldListAdapter(FieldsActivity.this, fieldName, fieldPic, fieldLocation, fieldUsage);
//        FieldListAdapter fieldListAdapter = new FieldListAdapter(FieldsActivity.this, mylist);
//        listView.setAdapter(fieldListAdapter);


        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet("http://gpe.tabtu.top/venue");
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
                Intent intent = new Intent(FieldsActivity.this, FieldsDetailActivity.class);
//                intent.putExtra("name",fieldName[i]);
//                intent.putExtra("image",fieldPic[i]);
                startActivity(intent);
            }
        });
    }
}
