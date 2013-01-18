package com.jing.taxiCharge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-18
 * StringTime: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
public class StringTime {
    private int hour;
    private int minute;
    private static final int EVENING_START_TIME = 23;
    private static final int EVENING_END_TIME = 6;
    private static final int MINUTES_PER_DAY = (24 * 60);
    String regEx = "(\\d*):(\\d*)";

    public StringTime(String time){
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(time);
        if (m.matches()) {
            hour = Integer.parseInt(m.group(1)) ;
            minute = Integer.parseInt(m.group(2));
        }
    }

    public int minus(StringTime start_at) {
        int hourDiff = this.hour - start_at.hour;
        int minuteDiff = this.minute - start_at.minute;
        int totalDiffMinutes = hourDiff * 60 + minuteDiff;
        if (totalDiffMinutes >= 0){
            return totalDiffMinutes;
        } else {
            return MINUTES_PER_DAY + totalDiffMinutes;
        }
    }

    public boolean isEveningTime() {
        return hour >= EVENING_START_TIME  || hour <= EVENING_END_TIME;
    }
}
