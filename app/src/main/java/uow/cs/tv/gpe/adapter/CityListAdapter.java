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

/**
 * Created by Vian on 2/21/2018.
 */

public class CityListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> list;

    public CityListAdapter(Context context, int textViewResourceId, List<String> city){
        super(context, R.layout.adapter_citylist);
        this.list = city;
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
            convertView = inflater.inflate(R.layout.adapter_citylist, parent, false);

            viewHolder.mName = (TextView) convertView.findViewById(R.id.citylist_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mName.setText(list.get(position));

        return convertView;
    }

    static class ViewHolder{
        TextView mName;
    }
}
