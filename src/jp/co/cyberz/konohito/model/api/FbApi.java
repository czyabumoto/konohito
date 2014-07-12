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
		facebook.setOAuthPermissions("email,publish_stream,public_profile,user_friends,user_status");
		facebook.setOAuthAccessToken(new AccessToken("CAACEdEose0cBAMAwHWmZAaYw9ApbMifjaRo4GbnnKy9Mi8n0bNeIv6n1H6AG2glAdt5uqyo5ZAFqNlXpBqD76epN7xVXwZA7FPqvZCR1R2XaWIpt68ADVXOhdlhEWAN8ozMyZBcOr9A3KIImb8VRIMS2JfcpiKBAxztp8Qc5ZCNCyAEsFCQNUA27MnR3XMrZB2ziX4rOvCPNAZDZD", null));
	}

	/**
	 * フレンドリストを取得
	 * 
	 * @return
	 */
	public void getFriendList(FriendListCallback callback) {
		new FriendListTask(this.facebook, callback).execute();
	}
}
