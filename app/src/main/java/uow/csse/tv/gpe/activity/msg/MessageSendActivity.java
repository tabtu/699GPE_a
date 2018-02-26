package uow.csse.tv.gpe.activity.msg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.msg.MessageInboxFragment;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/22/2018.
 */

public class MessageSendActivity extends AppCompatActivity {

    private String senName;
    private String myId;
    private EditText name;
    private EditText content;
    private Msgs message;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                Toast.makeText(MessageSendActivity.this, "Send Success!", Toast.LENGTH_SHORT).show();
                finish();
//                Intent intent = new Intent(MessageSendActivity.this, MainActivity.class);
//                intent.putExtra("pageid",2);
//                startActivity(intent);
            } else {
                Toast.makeText(MessageSendActivity.this, "Send Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setData() {
        name = findViewById(R.id.sendmsg_name);
        content = findViewById(R.id.sendmsg_content);
        Button btn_send = findViewById(R.id.sendmsg_send);
        Button btn_cancel = findViewById(R.id.sendmsg_cancel);

        if (senName == null) {
            name.setText("NULL");
        } else {
            name.setText(senName);
        }

        content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        content.setSingleLine(false);
        content.setHorizontallyScrolling(false);
        content.setGravity(Gravity.TOP);

        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String exu = "sen=" + myId + "&rec=" + name.getText().toString() + "&text=" + content.getText().toString();
                        try {
                            HttpUtils hu = new HttpUtils();
                            String tmp = hu.executeHttpPost(Const.postmsg + exu);
                            JsonParse jp = new JsonParse(tmp);
                            message = jp.ParseJsonSingleMsg(tmp);
                            if (message == null) {
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
        });

    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sendmsg_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        senName = (String) getIntent().getSerializableExtra("msgname");
        myId = (String) getIntent().getSerializableExtra("msgmyid");
        setData();
    }
}
