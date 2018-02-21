package uow.csse.tv.gpe.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.activity.MainActivity;
import uow.csse.tv.gpe.activity.NewsDetailActivity;
import uow.csse.tv.gpe.activity.school.ClubActivity;
import uow.csse.tv.gpe.activity.school.SchoolActivity;
import uow.csse.tv.gpe.activity.venue.VenueActivity;
import uow.csse.tv.gpe.activity.user.UserActivity;
import uow.csse.tv.gpe.adapter.CityListAdapter;
import uow.csse.tv.gpe.adapter.NewsListAdapter;
import uow.csse.tv.gpe.config.Const;
import uow.csse.tv.gpe.model.City;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.util.HttpUtils;
import uow.csse.tv.gpe.util.JsonParse;
import uow.csse.tv.gpe.util.ListViewAutoHeight;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private ImageButton btn_fields;
    private ImageButton btn_athlete;
    private ImageButton btn_school;
    private ImageButton btn_club;
    private ListView listView;

    private List<News> uplist = new ArrayList<>();
    private List<News> downlist = new ArrayList<>();
    private List<City> citylist = new ArrayList<>();

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
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", uplist.get(i));
                startActivity(intent);
            }
        });
    }

    private void setList() {
        NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity(), downlist);
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
        Spinner spinner = view.findViewById(R.id.home_spinner);
        List<String> cityData = new ArrayList<>();
        for (int i = 0; i < citylist.size(); i++) {
            cityData.add(citylist.get(i).getName());
        }
//        CityListAdapter cityListAdapter = new CityListAdapter(getActivity(),R.layout.adapter_citylist, cityData);
//        spinner.setAdapter(cityListAdapter);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.adapter_citylist, cityData);
        spinner.setAdapter(arrayAdapter);



//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
////                intent.putExtra("news", downlist.get(i));
//                startActivity(intent);
//            }
//        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        if (msg.what == 0x0) {
            setBanner();
        }
        if (msg.what == 0x1) {
            setList();
        }
        if (msg.what == 0x2) {
            setSpinner();
        }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) view.findViewById(R.id.home_list);

        //buttons
        btn_athlete = (ImageButton) view.findViewById(R.id.btn_athlete);
        btn_athlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
            }
        });

        btn_fields = (ImageButton) view.findViewById(R.id.btn_fields);
        btn_fields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),VenueActivity.class);
                startActivity(intent);
            }
        });

        btn_school = (ImageButton) view.findViewById(R.id.btn_school);
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SchoolActivity.class);
                startActivity(intent);
            }
        });

        btn_club = (ImageButton) view.findViewById(R.id.btn_club);
        btn_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ClubActivity.class);
                startActivity(intent);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                pd = ProgressDialog.show(getActivity(), "…Please Wait", "Loading…");
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        pd = ProgressDialog.show(getActivity(), "…Please Wait", "Loading…");
//
//                    }
//                });
                HttpUtils hu = new HttpUtils();
                String tmp = hu.executeHttpGet(Const.getupnewslist);
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
                String tmp = hu.executeHttpGet(Const.getdownnewslist);
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
//        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText(getArguments().getString("ARGS"));
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
