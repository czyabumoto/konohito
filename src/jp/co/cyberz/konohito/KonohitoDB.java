package jp.co.cyberz.konohito;

import jp.co.cyberz.konohito.model.Friend;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class KonohitoDB {
	
	private static SQLiteDatabase db = null;
	
	private static final String TABLE_NAME_FRIENDS = "friend";
	private static final String[] COLUMNS_FRIENDS = {"_id", "name"};
	
	public static void init(Context c) {
		if (db == null) {
			KonohitoSQLite sqlite = new KonohitoSQLite(c);
			db = sqlite.getWritableDatabase();
		}
	}
	
	public static Friend getFriend(String id) {
		Friend friend = new Friend();
		try {
			Cursor cursor = db.query(TABLE_NAME_FRIENDS, COLUMNS_FRIENDS, "_id=?", new String[] {id}, null, null, null);
			boolean next = cursor.moveToFirst();
			while (next) {
				friend.id   = cursor.getString(0);	
				friend.name = cursor.getString(1);
				next = cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			
		}
		return friend;
	}
	
	public static void save(Friend friend) {
		try {
			ContentValues values = new ContentValues();
			values.put("_id" , friend.id);
			values.put("name", friend.name);
			long result = db.insert(TABLE_NAME_FRIENDS, null, values);
			if(result < 0) {
				result = db.update(TABLE_NAME_FRIENDS, values, "_id=?", new String[] {friend.id});
			}
		} catch (Exception e) {
			
		}
	}

}
