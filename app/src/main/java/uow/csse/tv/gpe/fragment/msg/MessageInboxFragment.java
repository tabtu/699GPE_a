package uow.csse.tv.gpe.fragment.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.MessageListAdapter;
import uow.csse.tv.gpe.model.Message;

/**
 * Created by Vian on 2/21/2018.
 */

public class MessageInboxFragment extends Fragment {

    private View view;
    private ListView listView;
    private List<Message> mylist = new ArrayList<>();

    public MessageInboxFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_simplelist, container, false);
        listView = (ListView) view.findViewById(R.id.simplelist_list);

        mylist = (List<Message>) getArguments().getSerializable("msgin");

        MessageListAdapter messageListAdapter = new MessageListAdapter(getActivity(), mylist);
        listView.setAdapter(messageListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MessageInboxFragment.class);
                intent.putExtra("msgin", mylist.get(i));
                startActivity(intent);
            }
        });
        return view;
    }
}
