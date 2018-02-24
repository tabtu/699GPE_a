package uow.csse.tv.gpe.activity.msg;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/23/2018.
 */

public class MessageSendDetailActivity extends AppCompatActivity{

    private Msgs message;
    private Func func = new Func();

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
                Log.v("qqqqqqq","delete");
            }
        });
    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsgdetail);
        message = (Msgs) getIntent().getSerializableExtra("msgin");
        setData();
    }

}
