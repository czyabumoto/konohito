package jp.co.cyberz.konohito.model;

import java.util.Calendar;
import java.util.LinkedList;

import facebook4j.User;

/**
 * 友達クラス。友達一人に対する属性を管理する。
 */
public class Friend {
	
	/** Facebookユーザー属性 */
	public String id;
	public String name;
	public String first_name;
	public String middle_name;
	public String last_name;
	public String birthday;
	
	/** タグリスト */
	public LinkedList<String> tags;
	
	/** 最初に出会った日 */
	public String first_met_date;
	
	/** メモ */
	public String memo;
	
	/** Facebookフレンド追加日時 */
	public Calendar add_date;
	
	
	public void save() {
		
	}
	
	public static Friend get(String id) {
		
		return new Friend();
	}
	
}
