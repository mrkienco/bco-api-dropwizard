/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgatebco.api.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author thinhnv
 */
public class TimeUtils {

	public final static long MILLIS_ONE_DAY = 24 * 60 *60 * 1000;
    public final static SimpleDateFormat normalTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat onlyDayFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat onlyHourFormat = new SimpleDateFormat("HH:mm:ss");
    
    public static String getCurrentDay() {
    	return getTimeString(System.currentTimeMillis(), true);
    }
    
    public static String getCurrentDate() {
    	return getTimeString(System.currentTimeMillis(), false);
    }

    /**
     * Trả giá trị 1 nếu time nhỏ hơn hiện tại 1 ngày
     * Trả giá trị 0 nếu ngược lại
     * @param time
     * @return
     */
    public static int compareTimeStampAndNow(Timestamp time) {
        Calendar currentCal = Calendar.getInstance();
        Calendar lastLoginCal = Calendar.getInstance();
        lastLoginCal.setTimeInMillis(time.getTime());
        if (currentCal.get(Calendar.YEAR) > lastLoginCal.get(Calendar.YEAR)) {
            return 1;
        }
        if (currentCal.get(Calendar.MONTH) > lastLoginCal.get(Calendar.MONTH)) {
            return 1;
        }
        if (currentCal.get(Calendar.DATE) > lastLoginCal.get(Calendar.DATE)) {
            return 1;
        }
        return 0;
    }

    /**
     * Trả ra giá trị millis time của giờ. Định dạng giờ: hh:mm:ss
     * @param strHour
     * @return
     */
    public static long getTimeByHour(String strHour) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String data[] = strHour.split(":");
        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);
        int second = Integer.parseInt(data[2]);
        calendar.clear();
        calendar.set(year, month, day, hour, minute, second);
        return calendar.getTimeInMillis();
    }

    public static long getDayBetween(Calendar c1, Calendar c2) {
        return (c1.getTimeInMillis() - c2.getTimeInMillis()) / 86400000;
    }

    /**
     * Dua ra chuoi thoi gian dua vao thoi gian truyen vao dang millisecond
     * Chuoi dua ra co the bao gom day du ngay gio phut giay, co the chi bao gom
     * ngay thang nam (bien onlyDay)
     * @param timeMillis
     * @return
     */
    public static String getTimeString(long timeMillis, boolean onlyDay) {
        String format = "";
        if (onlyDay) {
            Date date = new Date(timeMillis);
            format = onlyDayFormat.format(date);
            return format;
        } else {
            Date date = new Date(timeMillis);
            format = normalTimeFormat.format(date);
            return format;
        }
    }
    
    public static Date getDateFromString(String dateStr, boolean onlyDay) throws ParseException {
    	if (onlyDay) {
    		return onlyDayFormat.parse(dateStr);
    	} else {
    		return normalTimeFormat.parse(dateStr);
    	}
    }
    
    public static String getHourString(long millis) {
    	return onlyHourFormat.format(new Date(millis));
    }

    /**
     * Dua ra thoi gian duoi dang giua hai khoang thoi gian
     * @param startTimeMillis
     * @param endTimeMillis
     * @param onlyDay
     * @return
     */
    public static String getBetweenTimeString(long startTimeMillis, long endTimeMillis, boolean onlyDay) {
        String format = "";
        if (onlyDay) {
            Date dateStart = new Date(startTimeMillis);
            Date dateEnd = new Date(endTimeMillis);
            format = onlyDayFormat.format(dateStart) + " đến " + onlyDayFormat.format(dateEnd);
        } else {
            Date dateStart = new Date(startTimeMillis);
            Date dateEnd = new Date(endTimeMillis);
            format = normalTimeFormat.format(dateStart) + " -> " + normalTimeFormat.format(dateEnd);
        }
        return format;
    }
    
    public static ArrayList<String> listDayBetween(String start, String end) {
    	ArrayList<String> days = new ArrayList<String>();
    	try {
    		long dayMillis = 24 * 60 * 60 * 1000;
	    	Date dateStart = onlyDayFormat.parse(start);
	    	Date dateEnd = onlyDayFormat.parse(end);
	    	Date tempDate = new Date(dateStart.getTime());
	    	days.add(start);
	    	while(tempDate.getTime() < dateEnd.getTime()) {
	    		tempDate.setTime(tempDate.getTime() + dayMillis);
	    		String tempDayStr = onlyDayFormat.format(tempDate);
	    		days.add(tempDayStr);
	    	}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return days;
    }
    
    public static long getFirstDayOfWeek() {
    	// set the date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));

        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK, 
                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
        return first.getTimeInMillis();
    }
    
    public static long getLastDayOfWeek() {
    	// set the date
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));

        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK, 
                  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);
        return last.getTimeInMillis();
    }
}
