package com.example.vian.gpe_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vian.gpe_android.R;
import com.example.vian.gpe_android.adapter.FieldListAdapter;

/**
 * Created by Vian on 2/5/2018.
 */

public class FieldsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    String[] fieldName = {"123","456"};
    int[] fieldPic = {R.mipmap.athlete,R.mipmap.athlete};
    String[] fieldUsage = {"adsa","dddd"};
    String[] fieldLocation = {"cccccccccccc","dddddddddddddddddd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Fields");

        listView = (ListView) findViewById(R.id.fieldslist);
        FieldListAdapter fieldListAdapter = new FieldListAdapter(FieldsActivity.this, fieldName, fieldPic, fieldLocation, fieldUsage);
        listView.setAdapter(fieldListAdapter);
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
