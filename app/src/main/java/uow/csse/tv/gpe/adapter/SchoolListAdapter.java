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
import uow.csse.tv.gpe.model.School;
import uow.csse.tv.gpe.model.Venue;

/**
 * Created by Vian on 2/5/2018.
 */

public class SchoolListAdapter extends ArrayAdapter<String>{

    private Context context;
    private List<School> list;

    public SchoolListAdapter (Context context, List<School> school){
        super(context, R.layout.adapter_schoollist);
        this.list = school;
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
            convertView = inflater.inflate(R.layout.adapter_schoollist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.schoollist_Image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.schoollist_Name);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.schoollist_Location);
            viewHolder.mUsage = (TextView) convertView.findViewById(R.id.schoollist_Usage);
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
