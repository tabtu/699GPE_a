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

public class UserListAdapter extends ArrayAdapter<String>{

    String [] name;
    int [] image;
    String [] interest;
    String [] location;
    Context context;

    public UserListAdapter (Context context, String[] listName, int[] listImage, String[] listInterest, String[] listLocation){
        super(context, R.layout.adapter_fieldslist);
        this.name = listName;
        this.image = listImage;
        this.interest = listInterest;
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
            convertView = inflater.inflate(R.layout.adapter_userlist, parent, false);

            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.usrlist_Image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.usrlist_Name);
            viewHolder.mLocation = (TextView) convertView.findViewById(R.id.usrlist_Location);
            viewHolder.mInterest = (TextView) convertView.findViewById(R.id.usrlist_Interest);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mImage.setImageResource(image[position]);
        viewHolder.mName.setText(name[position]);
        viewHolder.mLocation.setText(location[position]);
        viewHolder.mInterest.setText(interest[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mLocation;
        TextView mInterest;
    }
}
