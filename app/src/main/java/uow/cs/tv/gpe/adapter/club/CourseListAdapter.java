package uow.cs.tv.gpe.adapter.club;

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

import uow.cs.tv.gpe.R;
import uow.cs.tv.gpe.model.Club;

/**
 * Created by Vian on 2/12/2018.
 */

public class CourseListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Club> list;

    public CourseListAdapter(Context context, List<Club> clubs){
        super(context, R.layout.adapter_courselist);
        this.list = clubs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addItem(Club club) {
        list.add(club);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_courselist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.courselist_image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.courselist_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Picasso.with(getContext()).load(list.get(position).getPicture()).fit().into(viewHolder.mImage);
        viewHolder.mName.setText(list.get(position).getName());

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
    }
}
