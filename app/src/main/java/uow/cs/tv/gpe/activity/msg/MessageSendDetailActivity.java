package uow.cs.tv.gpe.activity.msg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.config.Const;
import uow.cs.tv.gpe.model.Msgs;
import uow.cs.tv.gpe.util.Func;
import uow.cs.tv.gpe.util.HttpUtils;

/**
 * Created by Vian on 2/23/2018.
 */

public class MessageSendDetailActivity extends AppCompatActivity{

    private Msgs message;
    private Func func = new Func();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x0) {
                Toast.makeText(MessageSendDetailActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(MessageSendDetailActivity.this, "Delete Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public MessageSendDetailActivity() {

    }

    private void setData() {
        TextView name = findViewById(R.id.sendmsgdetail_name);
        TextView time = findViewById(R.id.sendmsgdetail_time);
        TextView date = findViewById(R.id.sendmsgdetail_date);
        TextView content = findViewById(R.id.sendmsgdetail_content);
        ImageButton btn_menu = findViewById(R.id.sendmsgdetail_menu);

        Date td = new Date();
        td.setTime(message.getSendtime());

        String userName = "User" + message.getSender().substring(10, 15);
        name.setText(userName);
        time.setText(func.convertLong2Time(td.getTime()));
        date.setText(func.convertLong2String(message.getLogtime()));
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
                Intent intent = new Intent(MessageSendDetailActivity.this, MessageSendActivity.class);
                intent.putExtra("msgname", message.getSender());
                intent.putExtra("msgmyid", message.getReceiver());
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String _sen = "sen=" + message.getReceiver();
                        String _rec = "rec=" + message.getSender();
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
        setContentView(R.layout.activity_sendmsgdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.sendmsgdetail_toolbar);
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
    }

}
