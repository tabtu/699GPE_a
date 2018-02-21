package uow.csse.tv.gpe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.Message;
import uow.csse.tv.gpe.model.Msg;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/20/2018.
 */

public class MessageListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Message> list;
    private Func func = new Func();

    public MessageListAdapter(Context context, List<Message> msg){
        super(context, R.layout.adapter_movementlist);
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

            viewHolder.mName = (TextView) convertView.findViewById(R.id.msglist_title);
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.msglist_date);
            viewHolder.mDetail = (TextView) convertView.findViewById(R.id.msglist_detail);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.mName.setText(list.get(position).getMsgid().getSender());
        viewHolder.mDate.setText(func.convertLong2String(list.get(position).getMsgid().getSendtime()));
        viewHolder.mDetail.setText(list.get(position).getText());

        return convertView;
    }

    static class ViewHolder{
        TextView mName;
        TextView mDate;
        TextView mDetail;
    }
}
