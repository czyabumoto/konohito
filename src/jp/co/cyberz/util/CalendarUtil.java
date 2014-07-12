package jp.co.cyberz.util;

import android.annotation.SuppressLint;
import java.util.Calendar;

@SuppressLint("UseValueOf")
public class CalendarUtil {

	/**
	 * 今日の日付文字列を取得。YYYY/MM/DD形式
	 * @return
	 */
	public static String todayDateString() {
        Calendar cal = Calendar.getInstance();
		return dateString(cal);
	}
	
	/**
	 * 現在の日付文字列を取得。YYYY/MM/DD HH:MI:SS形式 (MySQL datetime型)
	 * @param cal
	 * @return
	 */
	public static String todayDatetimeString() {
        Calendar cal = Calendar.getInstance();
		return CalendarUtil.datetimeString(cal);
	}
	
	/**
	 * 日付文字列を取得。YYYY/MM/DD形式
	 * @param cal
	 * @return
	 */
	public static String dateString(Calendar cal) {
		return String.format("%04d/%02d/%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
	}
	
	/**
	 * 日付文字列を取得。YYYY/MM/DD HH:MI:SS形式 (MySQL datetime型)
	 * @param cal
	 * @return
	 */
	public static String datetimeString(Calendar cal) {
		return String.format("%04d/%02d/%02d %02d:%02d:%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	
	


	public static String dayOfWeekString(Calendar cal) {
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
	    	case Calendar.SUNDAY: 		return "sunday";
	    	case Calendar.MONDAY: 		return "monday";
	    	case Calendar.TUESDAY: 		return "tuesday";
	    	case Calendar.WEDNESDAY:	return "wednesday";
	    	case Calendar.THURSDAY:		return "thursday";
	    	case Calendar.FRIDAY: 		return "friday";
	    	case Calendar.SATURDAY: 	return "saturday";
		}
		return null;
	}

	public static String dayOfJapaneseWeekString(Calendar cal) {
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
	    	case Calendar.SUNDAY: 		return "日";
	    	case Calendar.MONDAY: 		return "月";
	    	case Calendar.TUESDAY: 		return "火";
	    	case Calendar.WEDNESDAY:	return "水";
	    	case Calendar.THURSDAY:		return "木";
	    	case Calendar.FRIDAY: 		return "金";
	    	case Calendar.SATURDAY: 	return "土";
		}
		return null;
	}

	/**
	 * YYYY/MM/DD形式の日付をCalendarオブジェクトに変換する
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(String date) {
		if(isDateString(date)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, new Integer(date.substring(0, 4)).intValue());
			cal.set(Calendar.MONTH, new Integer(date.substring(5, 7)).intValue()-1);
			cal.set(Calendar.DAY_OF_MONTH, new Integer(date.substring(8, 10)));
			return cal;
		}
		return null;
	}

	/**
	 * YYYY-MM-DD HH24:mm:ss形式の日付をCalendarオブジェクトに変換する
	 * @param date
	 * @return
	 */
	public static Calendar getCalendarFromDateTime(String datetime) {
		if(isDatetimeString(datetime)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, new Integer(datetime.substring(0, 4)).intValue());
			cal.set(Calendar.MONTH, new Integer(datetime.substring(5, 7)).intValue()-1);
			cal.set(Calendar.DAY_OF_MONTH, new Integer(datetime.substring(8, 10)));
			cal.set(Calendar.HOUR_OF_DAY, new Integer(datetime.substring(11, 13)));
			cal.set(Calendar.MINUTE, new Integer(datetime.substring(14, 16)));
			cal.set(Calendar.SECOND, new Integer(datetime.substring(17, 19)));
			return cal;
		}
		return null;
	}

	/**
	 * 時間の差分を秒で返す
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static long differenceSeconds(Calendar cal1, Calendar cal2) {
		long t1 = cal1.getTimeInMillis();
		long t2 = cal2.getTimeInMillis();
		return (t2 - t1) / 1000;
	}

	public static long differenceDays(Calendar cal1, Calendar cal2) {
		long t1 = cal1.getTimeInMillis();
		long t2 = cal2.getTimeInMillis();
		return (t2 - t1) / 1000 / 60 / 60 / 24;
	}

	public static int compareDayDateString(String date1, String date2) {
		if(!StringUtil.isEmpty(date1) && !StringUtil.isEmpty(date2)) {
			if(date1.length() >= 10 && date2.length() >= 10) { // 10="YYYY-MM-DD".length()
				return date1.substring(0, 10).compareTo(date2.substring(0, 10));
			}
		}
		return 0;
	}

	/**
	 * YYYY/MM/DD形式かどうか判定する（高速処理）
	 * @param date
	 * @return
	 */
	public static boolean isDateString(String date) {
		if(date == null || date.length() < 10) return false;
		char c[] = date.toCharArray();
		if(c[0] != '2' || c[1] != '0') return false;
		if(!Character.isDigit(c[2]) || !Character.isDigit(c[3])) return false;
		if(!Character.isDigit(c[5]) || !Character.isDigit(c[6])) return false;
		if(c[4] != '/' || c[7] != '/') return false;
		if(!Character.isDigit(c[8]) || !Character.isDigit(c[9])) return false;
		return true;
	}

	/**
	 * YYYY/MM/DD HH:MI:SS形式かどうか判定する（高速処理）
	 * @param date
	 * @return
	 */
	public static boolean isDatetimeString(String datetime) {
		if(datetime == null || datetime.length() < 19) return false;
		char c[] = datetime.toCharArray();
		if(!isDateString(datetime)) return false;
		if(c[10] != ' ' || c[13] != ':' || c[16] != ':')  return false;
		if(!Character.isDigit(c[11]) || !Character.isDigit(c[12])) return false;
		if(!Character.isDigit(c[14]) || !Character.isDigit(c[15])) return false;
		if(!Character.isDigit(c[17]) || !Character.isDigit(c[18])) return false;
		return true;
	}
}
