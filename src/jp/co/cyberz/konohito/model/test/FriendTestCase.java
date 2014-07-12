package jp.co.cyberz.konohito.model.test;

import jp.co.cyberz.konohito.model.Friend;
import junit.framework.TestCase;

public class FriendTestCase extends TestCase {

	public void test_saveFriend() {
		Friend friend     = new Friend();
		friend.id         = "11111";
		friend.name       = "山田太郎";
		friend.first_name = "山田";
		friend.last_name  = "太郎";
		friend.save();
		
		Friend friend2    = Friend.get(friend.id);
		assertEquals(friend.id, friend2.id);
		
	}
}
