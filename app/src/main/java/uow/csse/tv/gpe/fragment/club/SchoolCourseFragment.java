package uow.csse.tv.gpe.fragment.club;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.Club;

/**
 * Created by Vian on 2/26/2018.
 */

public class SchoolCourseFragment extends Fragment{
    private View view;
    private Club school;
    private ListView listView;

    public SchoolCourseFragment() {

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        view = inflater.inflate(R.layout.fragment_course,container,false);
//        listView = (ListView) view.findViewById(R.id.course_list);
//
//        club = ((SchoolDetailActivity) getActivity()).getSchool();
//
//        CourseListAdapter courseListAdapter = new CourseListAdapter(getActivity(), club.getClub_id().);
//        listView.setAdapter(courseListAdapter);
//        ListViewAutoHeight listViewAutoHeight = new ListViewAutoHeight();
//        listViewAutoHeight.setListViewHeightBasedOnChildren(listView);

        return view;
    }
}
