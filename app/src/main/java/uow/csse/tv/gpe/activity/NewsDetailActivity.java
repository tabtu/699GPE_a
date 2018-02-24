package uow.csse.tv.gpe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.model.VNews;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/20/2018.
 */

public class NewsDetailActivity extends AppCompatActivity {
    private News news;
//    private TextView title;
//    private TextView author;
//    private TextView date;
    private WebView webview;
    private Func func = new Func();

    public NewsDetailActivity() {

    }

    private void setData() {
//        title = findViewById(R.id.newsdetail_title);
//        date = findViewById(R.id.newsdetail_date);
//        author = findViewById(R.id.newsdetail_author);
//
//        title.setText(news.getTitle());
//        date.setText(func.convertLong2String(news.getUpdatedate()));
//        author.setText(news.getAuthor());
        webview = findViewById(R.id.newsdetail_webview);
        webview.getSettings().setDefaultTextEncodingName("UTF -8");
        webview.loadData(news.getText(), "text/html; charset=UTF-8", null);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        news = (News) getIntent().getSerializableExtra("news");

        setData();

    }
}
