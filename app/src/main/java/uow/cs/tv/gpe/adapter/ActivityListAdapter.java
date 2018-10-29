package uow.cs.tv.gpe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.Activity;
import uow.cs.tv.gpe.util.Func;

/**
 * Created by Vian on 2/25/2018.
 */

public class ActivityListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Activity> list;
    private Func func = new Func();

    public ActivityListAdapter(Context context, List<Activity> act){
        super(context, R.layout.adapter_activitylist);
        this.list = act;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addItem(Activity activity) {
        list.add(activity);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ActivityListAdapter.ViewHolder viewHolder = new ActivityListAdapter.ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_activitylist, parent, false);

            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.activitylist_title);
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.activitylist_date);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.activitylist_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ActivityListAdapter.ViewHolder)convertView.getTag();
        }

        viewHolder.mTitle.setText(list.get(position).getTitle());
        String dt = func.convertLong2String(list.get(position).getStartdate()) + " - " + func.convertLong2String(list.get(position).getEnddate());
        viewHolder.mDate.setText(dt);
//        viewHolder.mTime.setText(list.get(position).get);

        return convertView;
    }

    static class ViewHolder{
        TextView mTitle;
        TextView mDate;
        TextView mTime;
    }
}
