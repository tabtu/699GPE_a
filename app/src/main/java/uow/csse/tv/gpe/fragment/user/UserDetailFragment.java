package uow.csse.tv.gpe.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.TabhostAdapter;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.util.CircleTransform;

/**
 * Created by Vian on 2/6/2018.
 */

public class UserDetailFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User user;

    private void setData(View view) {
        TextView name = (TextView) view.findViewById(R.id.userdetail_name);
        ImageView img = (ImageView) view.findViewById(R.id.userdetail_image);
        name.setText(user.getName());
        Picasso.with(getActivity()).load(user.getPicture()).resize(220,220).centerCrop().transform(new CircleTransform()).into(img);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState0) {
        View view = inflater.inflate(R.layout.fragment_userdetail, container, false);

        user = (User)getArguments().getSerializable("user");
        setData(view);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TabhostAdapter adapter = new TabhostAdapter(getFragmentManager());

        if (user.getAthlete() != null) {
            UserAthleteFragment uaf = new UserAthleteFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            uaf.setArguments(bundle);
            adapter.AddFragment(uaf, "Athlete");
        }
        if (user.getCoach() != null) {
            adapter.AddFragment(new UserCoachFragment(), "Coach");
        }
        if (user.getReferee() != null) {
            adapter.AddFragment(new UserRefereeFragment(), "Referee");
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
