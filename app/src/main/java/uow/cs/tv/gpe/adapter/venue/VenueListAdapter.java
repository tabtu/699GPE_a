package uow.cs.tv.gpe.adapter.venue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.Venue;

/**
 * Created by Vian on 2/5/2018.
 */

public class VenueListAdapter extends ArrayAdapter<String>{

    private Context context;
    private List<Venue> list;

    public VenueListAdapter(Context context, List<Venue> venue){
        super(context, R.layout.adapter_venueslist);
        this.list = venue;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addItem(Venue venue) {
        list.add(venue);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_venueslist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.venuelist_image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.venuelist_name);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.venuelist_location);
            viewHolder.mUsage = (TextView) convertView.findViewById(R.id.venuelist_usage);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if (list.get(position).getPicture() != null) {
//            Picasso.with(getContext()).load(list.get(position).getPicture()).resize(150,150).centerCrop().transform(new CircleTransform()).into(viewHolder.mImage);
        }
        viewHolder.mName.setText(list.get(position).getName());
        viewHolder.mLocation.setText(list.get(position).getAddress());
        viewHolder.mUsage.setText(list.get(position).getSportsName());
        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
        TextView mUsage;
    }
}
