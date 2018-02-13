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
import uow.csse.tv.gpe.model.Club;

/**
 * Created by Vian on 2/9/2018.
 */

public class SchoolListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Club> list;

    public SchoolListAdapter(Context context, List<Club> clubs){
        super(context, R.layout.adapter_clublist);
        this.list = clubs;
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
            convertView = inflater.inflate(R.layout.adapter_clublist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.clublist_Image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.clublist_Name);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.clublist_Location);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(list.get(position).getPicture()).fit().into(viewHolder.mImage);
        viewHolder.mName.setText(list.get(position).getName());
        viewHolder.mLocation.setText(list.get(position).getAddress());

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
    }
}
