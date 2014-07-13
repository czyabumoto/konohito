package jp.co.cyberz.konohito.model.api;

import java.util.*;

import jp.co.cyberz.konohito.controller.FriendController;
import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.util.StringUtil;
import facebook4j.Facebook;
import facebook4j.ResponseList;
import android.os.AsyncTask;

public class FriendListTask extends AsyncTask<String, Integer, List<Friend>>{
	
	private Facebook facebook;
	private FriendListCallback callback;
	
	public FriendListTask(Facebook facebook, FriendListCallback callback) {
		super();
		this.facebook = facebook;
		this.callback = callback;
	}
	
	@Override
	protected List<Friend> doInBackground(String... params) {
		try {
			ResponseList<facebook4j.Friend> fb_friends = this.facebook.getFriends();
			List<Friend> friends = FriendController.getFriends();
			
			LinkedList<Friend> newList = new LinkedList<Friend>();
			
			for (facebook4j.Friend fb_friend : fb_friends) {
				String id = fb_friend.getId();
				
				// DBにないフレンドを新規に追加する
				Friend friend = FriendController.getFriend(id);
				if (!friend.isValid()) {
					friend = new Friend();
					friend.id   = id;
					friend.name = fb_friend.getName();
					newList.add(friend);
				}
			}
			
			for (Friend friend : friends) {
				newList.add(friend);
			}
			return newList;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	protected void onPostExecute(List<Friend> result) {
		super.onPostExecute(result);
		this.callback.callback(result);
	}

}
