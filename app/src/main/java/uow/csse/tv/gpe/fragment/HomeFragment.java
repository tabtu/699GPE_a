package uow.csse.tv.gpe.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.LocationActivity;
import uow.csse.tv.gpe.activity.NewsDetailActivity;
import uow.csse.tv.gpe.activity.ScannerActivity;
import uow.csse.tv.gpe.activity.act.MainActivityActivity;
import uow.csse.tv.gpe.activity.club.ClubActivity;
import uow.csse.tv.gpe.activity.club.SchoolActivity;
import uow.csse.tv.gpe.activity.venue.VenueActivity;
import uow.csse.tv.gpe.adapter.NewsListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.City;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.ListViewAutoHeight;

import com.todddavies.components.progressbar.ProgressWheel;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private View view;
    private ListView listView;
    private ProgressWheel pw;

    private List<News> uplist = new ArrayList<>();
    private List<News> downlist = new ArrayList<>();
    private List<City> citylist = new ArrayList<>();
    private NewsListAdapter newsListAdapter;

    private City currentCity;

    private void setBanner() {
        Banner banner_news;
        String[] newsImg = new String[uplist.size()];
        String[] newsTitle = new String[uplist.size()];
        for (int i = 0; i < uplist.size(); i++) {
            newsImg[i] = uplist.get(i).getBackground();
            newsTitle[i] = uplist.get(i).getTitle();
        }

        banner_news = (Banner)view.findViewById(R.id.banner_news);
        banner_news.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner_news.setIndicatorGravity(Banner.CENTER);
        banner_news.setBannerTitle(newsTitle);
        banner_news.setImages(newsImg);
        banner_news.isAutoPlay(true);
        banner_news.setDelayTime(5000);

        banner_news.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int i) {
                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                intent.putExtra("news", uplist.get(i));
                startActivity(intent);
            }
        });
    }

    private void setList() {
        newsListAdapter = new NewsListAdapter(getActivity(), downlist);
        listView.setAdapter(newsListAdapter);
        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", downlist.get(i));
                startActivity(intent);
            }
        });
    }

    private void setSpinner() {
        final Spinner spinner = view.findViewById(R.id.home_spinner);
        List<String> cityData = new ArrayList<>();
        for (int i = 0; i < citylist.size(); i++) {
            cityData.add(citylist.get(i).getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.adapter_citylist, cityData);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                currentCity = citylist.get(i);
                Message msg = new Message();
                msg.what = 0x3;
                handler.sendMessage(msg);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        if (msg.what == 0x0) {
            setBanner();
        }
        if (msg.what == 0x1) {
            pw.stopSpinning();
            pw.setVisibility(View.GONE);
            setList();
        }
        if (msg.what == 0x2) {
            setSpinner();
        }
        if (msg.what == 0x3) {
            getHomeAndList();
        }
        }
    };

    private void getHomeAndList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getupnewslist + currentCity.getId());
                JsonParse jp = new JsonParse(tmp);
                uplist = jp.ParseJsonNews(tmp);
                if (uplist != null) {
                    Message msg = new Message();
                    msg.what = 0x0;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0x99;
                    handler.sendMessage(msg);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getdownnewslist + currentCity.getId() + "&" + Const.PAGE + "0");
                JsonParse jp = new JsonParse(tmp);
                downlist = jp.ParseJsonNews(tmp);
                if (downlist != null) {
                    Message msg = new Message();
                    msg.what = 0x1;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0x99;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void setButton(){
        ImageButton btn_fields;
        ImageButton btn_athlete;
        ImageButton btn_school;
        ImageButton btn_club;
        Button btn_more;

        //buttons
        btn_athlete = (ImageButton) view.findViewById(R.id.btn_athlete);
        btn_athlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivityActivity.class);
                startActivity(intent);
            }
        });

        btn_fields = (ImageButton) view.findViewById(R.id.btn_fields);
        btn_fields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),VenueActivity.class);
                intent.putExtra("city", currentCity);
                startActivity(intent);
            }
        });

        btn_school = (ImageButton) view.findViewById(R.id.btn_school);
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SchoolActivity.class);
                intent.putExtra("city", currentCity);
                startActivity(intent);
            }
        });

        btn_club = (ImageButton) view.findViewById(R.id.btn_club);
        btn_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ClubActivity.class);
                intent.putExtra("city", currentCity);
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) view.findViewById(R.id.home_list);
        pw = (ProgressWheel) view.findViewById(R.id.pw_spinner);
        pw.setVisibility(View.VISIBLE);
        pw.startSpinning();

        setButton();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getcitylist);
                JsonParse jp = new JsonParse(tmp);
                citylist = jp.ParseJsonCity(tmp);
                if (citylist != null) {
                    Message msg = new Message();
                    msg.what = 0x2;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0x99;
                    handler.sendMessage(msg);
                }
            }
        }).start();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
