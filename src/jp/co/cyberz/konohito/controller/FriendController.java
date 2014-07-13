package jp.co.cyberz.konohito.controller;

import java.util.*;

import android.util.Log;

import jp.co.cyberz.konohito.KonohitoDB;
import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.konohito.model.api.FbApi;
import jp.co.cyberz.konohito.model.api.FriendCallback;
import jp.co.cyberz.konohito.model.api.FriendListCallback;
import jp.co.cyberz.util.StringUtil;

public class FriendController {
	
	private static FriendListCallback list_callback = null;
	private static FriendCallback friend_callback = null;
	
	/**
	 * Facebook APIを通じてフレンドリストを取得する
	 * @param callback
	 */
	public static void getFriendsFromFacebook(FriendListCallback callback) {
		list_callback = callback;
		FbApi.getInstance().getFriendList(new FriendListCallback() {
			@Override
			public void callback(List<Friend> friends) {
				for (Friend friend : friends) {
					friend.save();
				}
				Log.d(getClass().getName(), friends.toString());
				list_callback.callback(friends);
			}
		});
	}
	
	public static void getFriendFromFacebook(List<Friend> friends, FriendCallback callback) {
		friend_callback = callback;
		// リストにAPIをまだコールしていないフレンドがあれば、タスクを生成する
		for (Friend friend : friends) {
			if (StringUtil.isEmpty(friend.api_datetime)) {
				FbApi.getInstance().getFriend(friend, friends, new FriendCallback() {
					@Override
					public void callback(List<Friend> friends) {
						friend_callback.callback(friends);
					}
				});
				break;
			}
		}
	}
	
	/**
	 * SQLiteからフレンドリストを取得する
	 * @return LinkedList<Friend>
	 */
	public static List<Friend> getFriends() {
		return KonohitoDB.getFriends();
	}
	
	public static Friend getFriend(String id) {
		return KonohitoDB.getFriend(id);
	}

}
