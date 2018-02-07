package uow.csse.tv.gpe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.Venue;

/**
 * Created by Vian on 2/5/2018.
 */

public class FieldListAdapter extends ArrayAdapter<String>{

    private Context context;
    private List<Venue> list;

    public FieldListAdapter (Context context, List<Venue> venue){
        super(context, R.layout.adapter_fieldslist);
        this.list = venue;
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
            convertView = inflater.inflate(R.layout.adapter_fieldslist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.listImage);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.listName);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.listLocation);
            viewHolder.mUsage = (TextView) convertView.findViewById(R.id.listUsage);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        viewHolder.mImage.setImageResource(image[position]);
        viewHolder.mName.setText(list.get(position).getName());
        viewHolder.mLocation.setText(list.get(position).getAddress());
        viewHolder.mUsage.setText(list.get(position).getTel());

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
        TextView mUsage;
    }
}
