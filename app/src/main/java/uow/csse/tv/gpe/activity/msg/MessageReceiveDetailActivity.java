package uow.csse.tv.gpe.activity.msg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.util.Func;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/22/2018.
 */

public class MessageReceiveDetailActivity extends AppCompatActivity{

    private Msgs message;
    private Func func = new Func();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                Toast.makeText(MessageReceiveDetailActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(MessageReceiveDetailActivity.this, "Delete Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public MessageReceiveDetailActivity() {

    }

    private void setData() {
        TextView name = findViewById(R.id.receivemsgdetail_name);
        TextView time = findViewById(R.id.receivemsgdetail_time);
        TextView date = findViewById(R.id.receivemsgdetail_date);
        TextView content = findViewById(R.id.receivemsgdetail_content);
        ImageButton btn_menu = findViewById(R.id.receivemsgdetail_menu);

        Date td = new Date();
        td.setTime(message.getSendtime());

        String userName = "User" + message.getSender().substring(10, 15);
        name.setText(userName);
        time.setText(func.convertLong2Time(td.getTime()));
        date.setText(func.convertLong2String(message.getSendtime()));
        content.setText(message.getText());
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
    }

    private void showMenu() {
        Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.adapter_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.show();

        TextView reply = contentView.findViewById(R.id.dialog_reply);
        TextView delete = contentView.findViewById(R.id.dialog_delete);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageReceiveDetailActivity.this, MessageSendActivity.class);
                intent.putExtra("msgname", message.getSender());
                intent.putExtra("msgmyid", message.getReceiver());
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String _sen = "sen=" + message.getSender();
                        String _rec = "rec=" + message.getReceiver();
                        String _sedt = "sedt=" + message.getSendtime();
                        try {
                            HttpUtils hu = new HttpUtils();
                            String tmp = hu.executeHttpPost(Const.postdeletemsg + _sen + "&" + _rec + "&" + _sedt);
                            if (tmp.equals("true")) {
                                android.os.Message msg = new android.os.Message();
                                msg.what = 0x0;
                                handler.sendMessage(msg);
                            } else {
                                android.os.Message msg = new android.os.Message();
                                msg.what = 0x99;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            Toast.makeText(MessageReceiveDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
                finish();
            }
        });
    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivemsgdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.receivemsgdetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        message = (Msgs) getIntent().getSerializableExtra("msgin");
        setData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String _sen = "sen=" + message.getSender();
                String _rec = "rec=" + message.getReceiver();
                String _sedt = "sedt=" + message.getSendtime();
                try {
                    HttpUtils hu = new HttpUtils();
                    String tmp = hu.executeHttpGet(Const.getmsgread + _sen + "&" + _rec + "&" + _sedt);
                    Log.v("tmp",tmp.length()+"");
                } catch (Exception e) {
                    Toast.makeText(MessageReceiveDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

}
