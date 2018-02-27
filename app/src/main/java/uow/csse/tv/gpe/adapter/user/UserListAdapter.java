package uow.csse.tv.gpe.adapter.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.model.Venue;
import uow.csse.tv.gpe.util.CircleTransform;

/**
 * Created by Vian on 2/5/2018.
 */

public class UserListAdapter extends ArrayAdapter<String>{

    private Context context;
    private List<User> list;

    public UserListAdapter (Context context, List<User> user){
        super(context, R.layout.adapter_userlist);
        this.list = user;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addItem(User user) {
        list.add(user);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_userlist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.usrlist_Image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.usrlist_Name);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.usrlist_Location);
            viewHolder.mInterest = (TextView) convertView.findViewById(R.id.usrlist_Interest);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (list.get(position).getPicture() != null) {
            Picasso.with(getContext()).load(list.get(position).getPicture()).resize(150,150).centerCrop().transform(new CircleTransform()).into(viewHolder.mImage);
        }
//        Picasso.with(getContext()).load(list.get(position).getPicture()).resize(150,150).centerCrop().transform(new CircleTransform()).into(viewHolder.mImage);
        viewHolder.mName.setText(list.get(position).getName());
        viewHolder.mLocation.setText(String.valueOf(list.get(position).getEmail()));
        viewHolder.mInterest.setText(String.valueOf(list.get(position).getIntroduction()));

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
        TextView mInterest;
    }
}
