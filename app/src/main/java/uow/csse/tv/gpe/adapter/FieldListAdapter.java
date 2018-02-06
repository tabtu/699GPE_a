package uow.csse.tv.gpe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import uow.csse.tv.gpe.R;

/**
 * Created by Vian on 2/5/2018.
 */

public class FieldListAdapter extends ArrayAdapter<String>{

    String [] name;
    int [] image;
    String [] usage;
    String [] location;
    Context context;

    public FieldListAdapter (Context context, String[] listName, int[] listImage, String[] listUsage, String[] listLocation){
        super(context, R.layout.adapter_fieldslist);
        this.name = listName;
        this.image = listImage;
        this.usage = listUsage;
        this.location = listLocation;
        this.context = context;
    }

    @Override
    public int getCount() {
        return name.length;
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
        viewHolder.mImage.setImageResource(image[position]);
        viewHolder.mName.setText(name[position]);
        viewHolder.mLocation.setText(location[position]);
        viewHolder.mUsage.setText(usage[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
        TextView mUsage;
    }
}
