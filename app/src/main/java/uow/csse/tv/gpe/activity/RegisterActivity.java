package uow.csse.tv.gpe.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.fragment.LoginFragment;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;

/**
 * Created by Vian on 2/19/2018.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText account;
    private EditText psd;
    private EditText confirm;
    private Button btn_submit;
    private Button btn_again;
    private User usr;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x0) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Sign up Error", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean checkBlank() {
        boolean submit = false;
        if (account.getText().length() == 0 || psd.getText().length() == 0 || confirm.getText().length() == 0) {
            Toast.makeText(RegisterActivity.this, "The form cannot be empty!", Toast.LENGTH_SHORT).show();
            submit = false;
        } else {
            submit = true;
        }
        return submit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = (EditText) findViewById(R.id.regis_account);
        psd = (EditText) findViewById(R.id.regis_password);
        confirm = (EditText) findViewById(R.id.regis_confirm);
        btn_again = (Button) findViewById(R.id.regis_again);
        btn_submit = (Button) findViewById(R.id.regis_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBlank()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String _account = account.getText().toString();
                            String _psd = psd.getText().toString();
                            String exu = "usr=" + _account + "&pwd=" + _psd;
                            Log.v("2", Const.registtl + exu);
                            try {
                                HttpUtils hu = new HttpUtils();
                                String tmp = hu.executeHttpPost(Const.registtl + exu);
                                Log.v("2", tmp);
                                JsonParse jp = new JsonParse(tmp);
                                usr = jp.ParseJsonUser(tmp);
                                if (usr == null) {
                                    Message msg = new Message();
                                    msg.what = 0x99;
                                    handler.sendMessage(msg);
                                } else {
                                    Message msg = new Message();
                                    msg.what = 0x0;
                                    handler.sendMessage(msg);
                                }
                            } catch (Exception e) {
                                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).start();
                }
            }
        });


    }
}
