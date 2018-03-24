package uow.csse.tv.gpe.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.PreferenceUtil;
import uow.csse.tv.gpe.util.SwitchLanguage;

/**
 * Created by Vian on 3/23/2018.
 */

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private SwitchLanguage switchLanguage = new SwitchLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        PreferenceUtil.init(this);
        switchLanguage.switchLanguage(PreferenceUtil.getString("language", "zh"),getResources());

        Toolbar toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Switch sw = (Switch) findViewById(R.id.switch1);
        PreferenceUtil.init(this);
        String language = PreferenceUtil.getString("language", "zh");
        if (language.equals("en")) {
            sw.setChecked(false);
        } else {
            sw.setChecked(true);
        }
        sw.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PreferenceUtil.init(this);
        switch (buttonView.getId()){
            case R.id.switch1:
                if(isChecked){
                    switchLanguage.switchLanguage(PreferenceUtil.getString("language", "zh"),getResources());
                    PreferenceUtil.commitString("language", "zh");
                }else {
                    switchLanguage.switchLanguage(PreferenceUtil.getString("language", "en"),getResources());
                    PreferenceUtil.commitString("language", "en");
                }
                break;
            default:
                break;
        }
        finish();
        Intent it = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(it);
    }
}
