package com.lianlian.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by on 2018/08/12
 */
public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
    // 获得本周一与当前日期相差的天数
    public static  int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }
 
    // 获得当前周- 周一的日期
    public static  String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday = dateFormat.format(monday);
        return preMonday;
    }
 
 
    // 获得当前周- 周日  的日期
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        String preMonday = dateFormat.format(monday);
        return preMonday;
    }
    // 获得当前月--开始日期
    public static String getMinMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
    }
 
    // 获得当前月--结束日期
    public static String getMaxMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
    }
    
    public static String getYearDate(){
    	Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.YEAR));
    }
}