package uow.csse.tv.gpe.fragment.club;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import uow.csse.tv.gpe.R;

/**
 * Created by Vian on 2/12/2018.
 */

public class ClubMovementFragment extends Fragment{
    private View view;
    private ListView listView;

    public ClubMovementFragment() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }
}
