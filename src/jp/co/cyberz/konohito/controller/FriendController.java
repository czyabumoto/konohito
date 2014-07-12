package jp.co.cyberz.konohito.controller;

import java.util.*;

import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.konohito.model.api.FbApi;
import jp.co.cyberz.konohito.model.api.FriendListCallback;

public class FriendController {
	
	public static void getFriends() {
		
		// SQLiteからフレンド取得
		LinkedList<Friend> friends = load();
		
		// TODO: Facebook Graph APIでフレンドリストを取得
		
		// TODO: DBに保存されているものからの差分を取得(newフラグとして設定する)
		FbApi.getInstance().getFriendList(new FriendListCallback() {
			@Override
			public void callback(List<Friend> friends) {
				for (Friend friend : friends) {
					friend.save();
				}
				System.out.println(friends);
			}
		});
		
		// TODO: リスナーにコールバック
	}
	
	/**
	 * SQLiteにフレンドリストを保存する。
	 * @param friends
	 * @return
	 */
	public static boolean save(List<Friend> friends) {
		// TODO: SQLiteにフレンドリストを保存
		return false;
	}
	
	/**
	 * SQLiteからフレンドリストを取得する
	 * @return LinkedList<Friend>
	 */
	public static LinkedList<Friend> load() {
		LinkedList<Friend> friends = new LinkedList<Friend>();
		
		// TODO: SQLiteからフレンドリストを取得
		
		return friends;
	}

}
