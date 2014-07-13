package jp.co.cyberz.konohito;

import java.util.List;

import jp.co.cyberz.konohito.controller.FriendController;
import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.konohito.model.api.FriendCallback;
import jp.co.cyberz.konohito.model.api.FriendListCallback;
import jp.co.cyberz.konohito.model.api.FriendTask;
import jp.co.cyberz.util.StringUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FriendListActivity extends Activity {
	
	private FriendListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
		setTitle("Friend");
		
        // Facebook APIからのデータ取得
     	FriendController.getFriendsFromFacebook(new FriendListCallback() {
			@Override
			public void callback(List<Friend> friends) {
				onUpdateFriends(friends);
				FriendController.getFriendFromFacebook(friends, new FriendCallback() {
					@Override
					public void callback(List<Friend> friends) {
						onUpdateFriends(friends);
					}
				});
			}
     	});

		List<Friend> items = FriendController.getFriends();        
        adapter = new FriendListAdapter(this, items);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }
    
    public void onUpdateFriends(List<Friend> friends) {
    	adapter.setItems(friends);
    	adapter.notifyDataSetChanged();
    }
    
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Friend item = (Friend) parent.getAdapter().getItem(position);
            Log.d(getClass().getName(), item.toString());
        }
    };
}
