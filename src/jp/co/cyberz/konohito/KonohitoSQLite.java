package jp.co.cyberz.konohito;

import android.content.Context;
import android.database.sqlite.*;

public class KonohitoSQLite extends SQLiteOpenHelper {
	
	static final String DB = "konohito.db";
	static final int DB_VERSION = 1;
	static final String CREATE_TABLE = "create table friend ( _id varchar primary key, name varchar );";
	static final String DROP_TABLE = "drop table friend;";

	
	public KonohitoSQLite(Context c) {
        super(c, DB, null, DB_VERSION);
    }
	
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
