package uow.cs.tv.gpe.fragment.msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.activity.MainActivity;
import uow.cs.tv.gpe.adapter.TabhostAdapter;

public class MessageFragment extends Fragment{

    private View view;

    private void setFragment() {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.message_tablayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.message_viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getFragmentManager());

        adapter.AddFragment(new MessageInboxFragment(), getString(R.string.inbox));
        adapter.AddFragment(new MessageOutboxFragment(), getString(R.string.outbox));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.message_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).finish();
            }
        });
        setFragment();
        return view;
    }

    public static MessageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
