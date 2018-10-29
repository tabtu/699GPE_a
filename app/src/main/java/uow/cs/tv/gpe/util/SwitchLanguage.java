package uow.cs.tv.gpe.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Created by Vian on 3/23/2018.
 */

public class SwitchLanguage {

    private String language = "";

    public String getLanguage() {
        language = PreferenceUtil.getString("language", "zh");
        return language;
    }

    public void switchLanguage(String language, Resources resources) {
        this.language = language;
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("en")) {
            config.locale = Locale.ENGLISH;
        } else {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        }
        resources.updateConfiguration(config, dm);

        //保存设置语言的类型
        PreferenceUtil.commitString("language", language);

        Log.v("language",this.language);
    }
}
