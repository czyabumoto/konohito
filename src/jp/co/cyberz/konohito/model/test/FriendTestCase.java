package jp.co.cyberz.konohito.model.test;

import android.test.AndroidTestCase;
import jp.co.cyberz.konohito.KonohitoDB;
import jp.co.cyberz.konohito.model.Friend;

public class FriendTestCase extends AndroidTestCase {

	protected void setUp() {
		KonohitoDB.init(getContext());
	}

	public void test_saveFriend() {
		
		Friend friend     = new Friend();
		friend.id         = "11111";
		friend.name       = "山田太郎";
		friend.first_name = "山田";
		friend.last_name  = "太郎";
		
		friend.addTag("タグA");
		friend.addTag("タグB");
		friend.addTag("タグA");
		
		friend.save();
		
		String tags = friend.getTagsCsv();
		
		Friend friend2    = Friend.get(friend.id);
		assertEquals(friend.id,   friend2.id);
		assertEquals(friend.name, friend2.name);
		assertEquals(friend.tags, friend2.tags);
		
	}
}
