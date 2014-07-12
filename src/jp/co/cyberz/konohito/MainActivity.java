package jp.co.cyberz.konohito;

import jp.co.cyberz.konohito.controller.FriendController;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TODO: Facebook認証していなければFacebook認証画面へ
		
		FriendController.getFriends();
		
		// TODO: フレンドリストの表示へ
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
