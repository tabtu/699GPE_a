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
import uow.csse.tv.gpe.model.VNews;
import uow.csse.tv.gpe.util.Func;

/**
 * Created by Vian on 2/10/2018.
 */

public class VenueMovementListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<VNews> list;
    private Func func = new Func();

    public VenueMovementListAdapter(Context context, List<VNews> news){
        super(context, R.layout.adapter_movementlist);
        this.list = news;
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
            convertView = inflater.inflate(R.layout.adapter_movementlist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.movementlist_image);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.movementlist_title);
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.movementlist_date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(list.get(position).getPicture()).resize(320,220).centerCrop().into(viewHolder.mImage);
        viewHolder.mTitle.setText(list.get(position).getTitle());
        viewHolder.mDate.setText(func.convertLong2String(list.get(position).getUpdateDate()));

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mTitle;
        TextView mDate;
    }
}
