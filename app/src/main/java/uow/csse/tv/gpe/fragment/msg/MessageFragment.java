package uow.csse.tv.gpe.fragment.msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;

public class MessageFragment extends Fragment{

    private View view;

    private void setFragment() {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.message_tablayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.message_viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getFragmentManager());

        adapter.AddFragment(new MessageInboxFragment(), "Inbox");
        adapter.AddFragment(new MessageOutboxFragment(), "Outbox");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

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
