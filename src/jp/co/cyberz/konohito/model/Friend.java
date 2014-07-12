package jp.co.cyberz.konohito.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.StringTokenizer;

import jp.co.cyberz.konohito.KonohitoDB;
import jp.co.cyberz.util.CalendarUtil;

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
	
	public String link;
	public String website;
	public String employer_name;
	
	/** タグリスト */
	public LinkedList<String> tags;
	
	/** 最初に出会った日 */
	public String first_met_date;
	
	/** メモ */
	public String memo;
	
	/** Facebookフレンド追加日時 */
	public String add_datetime;
	
	public Friend() {
		add_datetime = CalendarUtil.todayDatetimeString();
	}
	
	public void save() {
		KonohitoDB.save(this);
	}
	
	public static Friend get(String id) {
		return KonohitoDB.getFriend(id);
	}
	
	/**
	 * タグを追加する。
	 * @param tag
	 */
	public LinkedList<String> addTag(String tag) {
		if (tags == null) tags = new LinkedList<String>();
		for (String t : tags) {
			if(t.equals(tag)) {
				return tags;
			}
		}
		tags.add(tag);
		return tags;
	}
	
	/**
	 * カンマ区切り形式で複数のタグを追加する。
	 * @param tags
	 * @return
	 */
	public LinkedList<String> addTags(String csv) {
		if(csv == null) return tags;
		StringTokenizer st = new StringTokenizer(csv, ",");
		while(st.hasMoreTokens()) {
			String tag = st.nextToken();
			addTag(tag);
		}
		return tags;
	}
	
	/**
	 * タグを削除する。
	 * @param tag
	 */
	public LinkedList<String> removeTab(String tag) {
		if (tags == null) tags = new LinkedList<String>();
		tags.remove(tag);
		return tags;
	}
	
	/**
	 * CSV形式のタグリストを返す。
	 * @return
	 */
	public String getTagsCsv() {
		if (tags == null) return "";
		StringBuffer buf = new StringBuffer();
		for (String tag : tags) {
			buf.append(tag  + ",");
		}
		return buf.toString();
	}
	
}
