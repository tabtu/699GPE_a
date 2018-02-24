package uow.csse.tv.gpe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import uow.csse.tv.gpe.R;

/**
 * Created by Vian on 2/23/2018.
 */
//
//public class ActivityListAdapter extends ArrayAdapter<String> {
//
//    private Context context;
//    private List<Activity> list;
//
//    public ActivityListAdapter(Context context, int textViewResourceId, List<Activity> act){
//        super(context, R.layout.adapter_activitylist);
//        this.list = act;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        ActivityListAdapter.ViewHolder viewHolder = new ActivityListAdapter.ViewHolder();
//
//        if(convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.adapter_activitylist, parent, false);
//
//            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.activitylist_title);
//            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.activitylist_location);
//            viewHolder.mDate = (TextView) convertView.findViewById(R.id.activitylist_date);
//            viewHolder.mTime = (TextView) convertView.findViewById(R.id.activitylist_time);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ActivityListAdapter.ViewHolder)convertView.getTag();
//        }
//        viewHolder.mTitle.setText(list.get(position));
//        viewHolder.mLocation.setText(list.get(position));
//        viewHolder.mDate.setText(list.get(position));
//        viewHolder.mTime.setText(list.get(position));
//
//        return convertView;
//    }
//
//    static class ViewHolder{
//        TextView mTitle;
//        TextView mLocation;
//        TextView mDate;
//        TextView mTime;
//    }
//}
