package jp.co.cyberz.konohito.model.api;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyberz.konohito.model.Friend;
import facebook4j.Facebook;
import facebook4j.ResponseList;
import facebook4j.User;
import android.os.AsyncTask;

public class FriendListTask extends AsyncTask<String, Integer, List<Friend>>{
	
	private Facebook facebook;
	private FriendListCallback callback;
	
	public FriendListTask(Facebook facebook,FriendListCallback callback) {
		super();
		this.facebook = facebook;
		this.callback = callback;
	}
	
	@Override
	protected List<Friend> doInBackground(String... params) {
		try {
			ResponseList<facebook4j.Friend> friends = this.facebook.getFriends();
			List<Friend> fList = new ArrayList<Friend>();
			for (facebook4j.Friend friend : friends) {
				String id = friend.getId();
				User u = this.facebook.getUser(id);
				
				Friend f = new Friend();
				f.id = friend.getId();
				f.name = u.getName();
				// f.add_date
				f.birthday = u.getBirthday();
				// f.first_met_date
				f.first_name = u.getFirstName();
				f.last_name = u.getLastName();
				// f.memo
				f.middle_name = u.getMiddleName();
				// f.tags
				fList.add(f);
			}
			return fList;
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
