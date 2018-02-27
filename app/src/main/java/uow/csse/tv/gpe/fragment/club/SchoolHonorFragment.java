package uow.csse.tv.gpe.fragment.club;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uow.csse.tv.gpe.R;

/**
 * Created by Vian on 2/26/2018.
 */

public class SchoolHonorFragment extends Fragment{
    View view;

    public SchoolHonorFragment() {

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_honor,container,false);

        return view;
    }
}
