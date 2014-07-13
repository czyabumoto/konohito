package jp.co.cyberz.konohito.model.api;

import java.net.URL;
import java.util.*;

import android.util.Log;

import facebook4j.*;
import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.util.CalendarUtil;
import jp.co.cyberz.util.StringUtil;

public class FriendTask extends android.os.AsyncTask<String, Integer, Friend> {
	
	private Facebook facebook;
	private String id;
	private List<Friend> friends;
	private FriendCallback callback;
	
	public FriendTask(Facebook facebook, String id, List<Friend> friends, FriendCallback callback) {
		super();
		this.facebook = facebook;
		this.id = id; 
		this.friends = friends;
		this.callback = callback;
	}
	
	@Override
	protected Friend doInBackground(String... params) {
		try {
			User u = this.facebook.getUser(id);
			
			Log.d(getClass().getName(), "id:" + id + " name:" + u.getName());
			
			//Friend f = new Friend();
			Friend f = null;
			for(Friend friend : friends) {
				if(id.equals(friend.id));
				f = friend;
				break;
			}
			f.id = id;
			f.name = u.getName();
			f.birthday = u.getBirthday();
			f.first_name = u.getFirstName();
			f.last_name = u.getLastName();
			f.middle_name = u.getMiddleName();
			
			URL link = u.getLink();
			f.link = (link == null ? "" : link.toString());
			
			URL website = u.getWebsite();
			f.website = (website == null ? "" : website.toString());
			
			List<User.Work> works = u.getWork();
			if (works != null && works.size() > 0) {
				User.Work work = works.get(0);
				if (work != null) {
					IdNameEntity name = work.getEmployer();
					if (name != null) {
						f.employer_name = name.getName();
					}
				}
			}
			
			f.api_datetime = CalendarUtil.todayDatetimeString();
			
			f.save();
			return f;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	protected void onPostExecute(Friend result) {
		super.onPostExecute(result);
		this.callback.callback(friends);
		
		// リストにAPIをまだコールしていないフレンドがあれば、再度タスクを生成する
		for (Friend friend : friends) {
			if (StringUtil.isEmpty(friend.api_datetime)) {
				new FriendTask(facebook, friend.id, friends, new FriendCallback() {
					@Override
					public void callback(List<Friend> friends) {
						callback.callback(friends);
					}
				}).execute();
				break;
			}
		}
	}

}
