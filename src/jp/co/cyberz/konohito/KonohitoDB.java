package jp.co.cyberz.konohito;

import java.util.*;

import jp.co.cyberz.konohito.model.Friend;
import jp.co.cyberz.util.StringUtil;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class KonohitoDB {
	
	private static SQLiteDatabase db = null;
	
	private static final String TABLE_NAME_FRIENDS = "friend";
	private static final String[] COLUMNS_FRIENDS = {
		"_id",
		"name",
		"first_name",
		"middle_name",
		"last_name",
		"birthday",
		"first_met_date",
		"memo",
		"add_datetime",
		"tags"}; //10

	public static void init(Context c) {
		if (db == null) {
			KonohitoSQLite sqlite = new KonohitoSQLite(c);
			db = sqlite.getWritableDatabase();
		}
	}
	
	public static List<Friend> getFriends() {
		LinkedList<Friend> friends = new LinkedList<Friend>();
		try {
			Cursor cursor = db.query(TABLE_NAME_FRIENDS, COLUMNS_FRIENDS, null, null, null, null, null);
			boolean next = cursor.moveToFirst();
			while (next) {
				Friend friend = new Friend();
				setFriendFromCursor(friend, cursor);
				friends.add(friend);
				next = cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return friends;
	}
	
	public static Friend getFriend(String id) {
		Friend friend = new Friend();
		try {
			Cursor cursor = db.query(TABLE_NAME_FRIENDS, COLUMNS_FRIENDS, "_id=?", new String[] {id}, null, null, null);
			boolean next = cursor.moveToFirst();
			while (next) {
				setFriendFromCursor(friend, cursor);
				next = cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return friend;
	}
	
	private static void setFriendFromCursor(Friend friend, Cursor cursor) {
		friend.id             = cursor.getString(0);	
		friend.name           = cursor.getString(1);
		friend.first_name     = cursor.getString(2);
		friend.middle_name    = cursor.getString(3);
		friend.last_name      = cursor.getString(4);
		friend.birthday       = cursor.getString(5);
		friend.first_met_date = cursor.getString(6);
		friend.memo           = cursor.getString(7);
		friend.add_datetime   = cursor.getString(8);
		friend.addTags(cursor.getString(9));
	}
	
	public static void save(Friend friend) {
		try {
			ContentValues values = new ContentValues();
			values.put("_id" ,           friend.id);
			values.put("name",           StringUtil.nvl(friend.name));
			values.put("first_name",     StringUtil.nvl(friend.first_name));
			values.put("middle_name",    StringUtil.nvl(friend.middle_name));
			values.put("last_name",      StringUtil.nvl(friend.last_name));
			values.put("birthday",       StringUtil.nvl(friend.birthday));
			values.put("first_met_date", StringUtil.nvl(friend.first_met_date));
			values.put("memo",           StringUtil.nvl(friend.memo));
			values.put("add_datetime",   StringUtil.nvl(friend.add_datetime));
			values.put("tags",           friend.getTagsCsv());
			long result = db.insert(TABLE_NAME_FRIENDS, null, values);
			if(result < 0) {
				result = db.update(TABLE_NAME_FRIENDS, values, "_id=?", new String[] {friend.id});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
