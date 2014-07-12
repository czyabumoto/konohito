package jp.co.cyberz.konohito;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyberz.konohito.controller.FriendController;
import jp.co.cyberz.konohito.model.Friend;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class FriendListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_friend_list);
		setTitle("Friend");

		List<Friend> items = FriendController.getFriends();        
        FriendListAdapter adapter = new FriendListAdapter(this, items);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }
    
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Friend item = (Friend) parent.getAdapter().getItem(position);
            Log.d(getClass().getName(), item.toString());
        }
    };
}
