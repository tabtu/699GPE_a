package uow.cs.tv.gpe.activity.club;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.CNews;
import uow.cs.tv.gpe.util.Func;

/**
 * Created by Vian on 4/2/2018.
 */

public class ClubNewsDetailActivity extends AppCompatActivity{
    private CNews cnews;
    //    private TextView title;
//    private TextView date;
    private WebView webview;
    private Func func = new Func();

    public ClubNewsDetailActivity() {

    }

    private void setData() {
//        title = findViewById(R.id.newsdetail_title);
//        date = findViewById(R.id.newsdetail_date);
//
//        title.setText(vnews.getTitle());
//        date.setText(func.convertLong2String(vnews.getUpdateDate()));
        webview = findViewById(R.id.newsdetail_webview);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");
        webview.loadData(cnews.getContent(), "text/html; charset=UTF-8", null);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newsdetail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cnews = (CNews)getIntent().getSerializableExtra("cnews");

        setData();

    }
}
