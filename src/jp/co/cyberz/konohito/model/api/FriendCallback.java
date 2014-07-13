package jp.co.cyberz.konohito.model.api;

import java.util.List;

import jp.co.cyberz.konohito.model.Friend;

public interface FriendCallback {
	public void callback(List<Friend> friends);
}
