package jp.co.cyberz.konohito;

import android.content.Context;
import android.database.sqlite.*;

public class KonohitoSQLite extends SQLiteOpenHelper {
	
	static final String DB = "konohito.db";
	static final int DB_VERSION = 1;

	static final String CREATE_TABLE_FRIEND = 
			"create table friend ( " +
				"_id varchar primary key," +
				"name varchar," +
				"first_name varchar," +
				"middle_name varchar," +
				"last_name varchar," +       // 5
				"birthday varchar," +
				"first_met_date varchar," +
				"memo varchar," +
				"add_datetime varchar," +
				"api_datetime varchar, " +  // 10
				"link varchar, " +
				"website varchar, " +
				"employer_name varchar, " +
				"tags varchar)"; //14
	static final String DROP_TABLE_FRIEND = "drop table friend;";

	
	public KonohitoSQLite(Context c) {
        super(c, DB, null, DB_VERSION);
    }
	
	public void onOpen(SQLiteDatabase db) {
		//onUpgrade(db, 0, 0);
    }
	
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FRIEND);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_FRIEND);
        onCreate(db);
    }
}
