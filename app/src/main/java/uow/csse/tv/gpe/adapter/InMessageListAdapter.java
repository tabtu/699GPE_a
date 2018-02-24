package uow.csse.tv.gpe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.Msgs;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/10/2018.
 */

public class InMessageListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Msgs> list;
    private Func func = new Func();

    public InMessageListAdapter(Context context, List<Msgs> msg){
        super(context, R.layout.adapter_msglist);
        this.list = msg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_msglist, parent, false);

            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.msglist_title);
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.msglist_date);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.msglist_detail);
            viewHolder.mlayout = (LinearLayout) convertView.findViewById(R.id.msglist_isread);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        String userName = "User" + list.get(position).getSender().substring(10, 15);
        viewHolder.mTitle.setText(userName);
        viewHolder.mDate.setText(func.convertLong2String(list.get(position).getLogtime()));
        viewHolder.mText.setText(list.get(position).getText());
        if (!list.get(position).getIsread()) {
            viewHolder.mlayout.setBackgroundColor(Color.parseColor("#f7caca"));
        } else {
            viewHolder.mlayout.setBackgroundColor(Color.parseColor("#ecf7e0"));
        }

        return convertView;
    }

    static class ViewHolder{
        TextView mTitle;
        TextView mText;
        TextView mDate;
        LinearLayout mlayout;
    }
}
