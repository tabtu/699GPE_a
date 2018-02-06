package uow.csse.tv.gpe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import uow.csse.tv.gpe.R;
import uow.csse.tv.gpe.adapter.UserListAdapter;
import uow.csse.tv.gpe.fragment.AccountFragment;

/**
 * Created by Vian on 2/5/2018.
 */

public class UserActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    String[] userName = {"123","456"};
    int[] userPic = {R.mipmap.athlete,R.mipmap.athlete};
    String[] userInterest = {"adsa","dddd"};
    String[] userLocation = {"cccccccccccc","dddddddddddddddddd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        toolbar = (Toolbar) findViewById(R.id.usrlist_toolbar);
        toolbar.setTitle("Users");

        listView = (ListView) findViewById(R.id.userslist);
        UserListAdapter userListAdapter = new UserListAdapter(UserActivity.this, userName, userPic, userLocation, userInterest);
        listView.setAdapter(userListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                intent = new Intent(UserActivity.this,AccountFragment.class);
                startActivity(intent);
//                intent.putExtra("name",userName[i]);
//                intent.putExtra("image",userPic[i]);
                startActivity(intent);
            }
        });
    }
}
