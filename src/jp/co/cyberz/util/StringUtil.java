package jp.co.cyberz.util;

public class StringUtil {
	/**
	 * 空の文字列かどうか
	 */
	public static boolean isEmpty(String value){
		return (value == null || value.length() == 0);
	}
	
	public static String nvl(String value) {
		return nvl(value, "");
	}
	public static String nvl(String value, String defaultValue) {
		if(isEmpty(value)) return defaultValue;
		return value;
	}
	public static String nvl(Integer value) {
		return nvl(value, "");
	}
	public static String nvl(Double value) {
		return nvl(value, "");
	}
	public static String nvl(Integer value, String defaultValue) {
		if(value == null) return defaultValue;
		return value.toString();
	}
	public static String nvl(Double value, String defaultValue) {
		if(value == null) return defaultValue;
		return value.toString();
	}
	public static String nvl(Long value) {
		return nvl(value, "");
	}
	public static String nvl(Long value, String defaultValue) {
		if(value == null) return defaultValue;
		return value.toString();
	}
}
