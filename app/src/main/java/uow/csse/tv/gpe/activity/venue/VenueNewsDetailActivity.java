package uow.csse.tv.gpe.activity.venue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.VNews;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/20/2018.
 */

public class VenueNewsDetailActivity extends AppCompatActivity {
    private VNews vnews;
//    private TextView title;
//    private TextView date;
    private WebView webview;
    private Func func = new Func();

    public VenueNewsDetailActivity() {

    }

    private void setData() {
//        title = findViewById(R.id.newsdetail_title);
//        date = findViewById(R.id.newsdetail_date);
//
//        title.setText(vnews.getTitle());
//        date.setText(func.convertLong2String(vnews.getUpdateDate()));
        webview = findViewById(R.id.newsdetail_webview);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");
        webview.loadData(vnews.getContent(), "text/html; charset=UTF-8", null);
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

        vnews = (VNews)getIntent().getSerializableExtra("vnews");

        setData();

    }
}
