package jp.co.cyberz.konohito;

import jp.co.cyberz.konohito.controller.FriendController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// SQLite初期化
		KonohitoDB.init(getApplicationContext());
		
        // TODO: Facebook認証していなければFacebook認証画面へ
		
		// フレンドリストの表示へ
		Button btSend = (Button) findViewById(R.id.SendToFriendList);
		btSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						FriendListActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
