package jp.co.cyberz.konohito.model.api;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyberz.konohito.model.Friend;
import android.os.AsyncTask;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public final class FbApi {

	private static FbApi api;

	private Facebook facebook;

	public static FbApi getInstance() {
		if (FbApi.api == null) {
			FbApi.api = new FbApi();
		}
		return FbApi.api;
	}

	private FbApi() {
		this.facebook = FacebookFactory.getSingleton();
		this.facebook.setOAuthAppId("325326170957493", "6e913651e863728127bfed697ea80c1c");
		//facebook.setOAuthPermissions("email,publish_stream,public_profile,user_friends,user_status");
		//facebook.setOAuthAccessToken(new AccessToken("CAACEdEose0cBAGhHs89HZACXVXlbZC2l3n56T22d1U903LxSZAj52Yir2sRukeT2FQ8JZAL3iK2wK8RjYXVNZB9BgDdjo9BBli3pgOI3eZAQAk20r2y8EZCGdHU8zGNV2gwPWqBaCooqsW3Vo4JacSXZCIXaiddOJShbRiQxL1wcaZCY9Cij5GiSZAZAh9ZCiPU36YFHgAb248SYlwZDZD", null));
	}
	
	public void setAccessSession(String accessToken, List<String> accessPermissions) {
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for (String accessPermission : accessPermissions) {
			sb.append(sep);
			sb.append(accessPermission);
			sep = ",";
		}
		this.facebook.setOAuthPermissions(sb.toString());
		this.facebook.setOAuthAccessToken(new AccessToken(accessToken));
	}

	/**
	 * フレンドリストを取得
	 * 
	 * @return
	 */
	public void getFriendList(FriendListCallback callback) {
		new FriendListTask(this.facebook, callback).execute();
	}
	
	public void getFriend(Friend friend, List<Friend> friends, FriendCallback callback) {
		new FriendTask(this.facebook, friend.id, friends, callback).execute();
	}
}
