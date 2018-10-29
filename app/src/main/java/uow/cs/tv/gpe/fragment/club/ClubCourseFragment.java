package uow.cs.tv.gpe.fragment.club;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.Club;

/**
 * Created by Vian on 2/10/2018.
 */

public class ClubCourseFragment extends Fragment{
    private View view;
    private Club club;
    private ListView listView;

    public ClubCourseFragment() {

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_course,container,false);
//        listView = (ListView) view.findViewById(R.id.course_list);
//
//        club=((ClubDetailActivity) getActivity()).getClub();
//
//        CourseListAdapter courseListAdapter = new CourseListAdapter(getActivity(), club.getClub_id().);
//        listView.setAdapter(courseListAdapter);
//        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
//        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);

        return view;
    }
}
