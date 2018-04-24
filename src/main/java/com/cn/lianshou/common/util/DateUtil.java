package com.cn.lianshou.common.util;

import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fireWorks on 2016/12/5.
 */
public class DateUtil {

    public static final String DATE_PATTERN             = "yyyy-MM-dd";
    public static final String DATE_PATTERN2            = "yyyy.MM.dd";
    public static final String DATE_NUMBER_PATTERN      = "yyyyMMdd";
    public static final String TIME_NUMBER_PATTERN      = "HHmmss";
    public static final String TIME_SHORT_PATTERN       = "HH:mm:ss";
    public static final String DATE_TIME_PATTERN        = "yyyyMMddHHmmss";
    public static final String DATE_TIME_NUMBER_PATTERN = "yyyyMMddHHmmssSSSSSS";
    public static final String TIME_PATTERN             = "yyyy-MM-dd HH:mm:ss";
    private static String[]    week                     = new String[] { "周日", "周一", "周二", "周三",
            "周四", "周五", "周六"                           };

    /**
     * 将日期字符串转换成日期
     * @param src
     * @param pattern
     * @return
     */
    public static String date2StringByPattern(Date src , String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(src);
    }

    /**
     * 将日期字符串转换成日期
     * @param src
     * @param pattern
     * @return
     */
    public static Date string2DateByPattern(String src , String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try{
            date = sdf.parse(src);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return date;
    }
    /**
     * 返回pattern格式的字符串
     *
     * @param pattern
     * @return
     */
    public static String getStringForPattern(String pattern) {

        if (pattern == null) {
            return null;
        }
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }

    /**
     * 返回yyyy.MM.dd字符串日期
     * @author jie.zou
     * @return
     * @throws Exception
     */

    public static String getDateFormat(String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN2);
        return simpleDateFormat.format(stringToDate(date));
    }

    /**
     * 返回yyyy-MM-dd字符串日期
     * @author jie.zou
     * @return
     * @throws Exception
     */

    public static String getDateFormatString(String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(stringToDate(date));
    }

    /**
     * 返回yyyy.MM.dd字符串日期
     * @author jie.zou
     * @return
     * @throws Exception
     */

    public static String getDateFormat(Date date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN2);
        return simpleDateFormat.format(date);
    }

    /**
     * 返回yyyyMMddHHmmss字符串
     * @return
     */
    public static String getDateAndTime() {
        return getStringForPattern(DATE_TIME_PATTERN);
    }

    /**
     * 返回yyyyMMddHHmmssSSSSSS字符串
     * @return
     */
    private static String getDateAndTimeSS() {
        return getStringForPattern(DATE_TIME_NUMBER_PATTERN);
    }

    /**
     * 返回yyyyMMdd字符串
     * @return
     */
    public static String getDate() {
        return getStringForPattern(DATE_NUMBER_PATTERN);
    }

    /**
     * 返回HHmmss字符串
     * @return
     */
    public static String getTime() {
        return getStringForPattern(TIME_NUMBER_PATTERN);
    }

    /**
     * 由yyyyMMdd格式的字符串返回日期
     *
     * @param date
     * @return
     */
    public static Date stringToDate(String date) throws Exception {

        if (date == null)
            return null;

        if (date.trim().length() == 0)
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_NUMBER_PATTERN);

        return simpleDateFormat.parse(date);
    }

    /**
     * 由yyyy-MM-dd格式的字符串返回yyyyMMdd字符串
     *
     * @param
     * @return
     */
    public static String stringPatTostring(String string) throws Exception {

        if (string == null)
            return null;

        if (string.trim().length() == 0)
            return null;
        String date = string.substring(0, 4)+string.substring(5,7)+string.substring(8, 10);
        return date;
    }

    /**
     * /** 由日期返回yyyyMMdd格式的字符串
     *
     * @param date
     * @return
     */
    public static String dateToNumber(Date date) {

        if (date == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_NUMBER_PATTERN);

        return simpleDateFormat.format(date);
    }

    /**
     * 根据终止日和相隔天数计算起始日
     *
     * @param endDate
     * @param days
     * @return
     */
    public static Date getStartDateByDays(Date endDate, int days) {

        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.setTime(endDate);
        calendarEndDate.add(Calendar.DAY_OF_YEAR, 0 - days);

        return calendarEndDate.getTime();
    }

    /**
     * 根据终止日和相隔天数计算起始日
     *
     * @param endDate
     * @param days
     * @return
     * @throws ParseException
     */
    public static String getStartDateByDays(String endDate, int days) throws Exception {
        return dateToNumber(getStartDateByDays(stringToDate(endDate), days));
    }

    /**
     *
     * @param bhDate yyyyMMdd
     * @param cnt  需要提前或推后几个月,负数提前，正数推后
     * @return
     */
    public static String getPreOrNextMonth(String bhDate, int cnt) {
        if (cnt == 0) {
            return bhDate;
        }
        String year = bhDate.substring(0, 4);
        String month = bhDate.substring(4, 6);
        String date = bhDate.substring(6, 8);
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));
        c.add(Calendar.MONTH, cnt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(c.getTime());
    }

    public void test1(){
        System.out.println(getPreOrNextMonth("20170131",1));
    }

    /**
     * 获取系统日期
     * @return yyyy-MM-dd
     */
    public static String getSystemDate() {
        return DateUtil.getStringForPattern(DATE_PATTERN);
    }

    /**
     * 获取系统时间
     * @return  yyyy-MM-dd HH:mm:ss
     */
    public static String getSystemDateTime() {
        return DateUtil.getStringForPattern(TIME_PATTERN);
    }

    /**
     *
     * 将字符串str转换成yyyy-MM-dd HH:mm:ss格式的
     *
     */
    public static String formatDate(String str) {
        if (str == null) {
            return null;
        }

        if (str.trim().equals("")) {
            return "";
        }

        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " "
                + str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
    }

    /**
     *
     * 将字符串str转换成yyyy-MM-dd格式的
     *
     */
    public static String formatDate2(String str) {
        if (str == null) {
            return null;
        }

        if (str.trim().equals("")) {
            return "";
        }

        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
    }


    /**
     * 由日期返回yyyyMMddHHmmss格式的字符串
     * @param time
     * @return
     */
    public static String timeToNumber(Date time) {

        if (time == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);

        return simpleDateFormat.format(time);
    }

    /**
     * 由yyyyMMdd格式的字符串返回日期
     * @param string
     * @return
     */
    public static Date numberToDate(String string) throws ParseException {

        if (string == null)
            return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return simpleDateFormat.parse(string);
    }

    /**
     * 根据起始日和相隔天数计算终止日
     * @param startDate
     * @param days
     * @return
     */
    public static Date getEndDateByDays(Date startDate, int days) {

        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        calendarStartDate.add(Calendar.DAY_OF_YEAR, days);

        return calendarStartDate.getTime();
    }

    /**
     *计算给定两个日期之间相差的天数
      */
    public static int getDayBetweenDay(String date1,String date2){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
        try {
            cal1.setTime(sdf.parse(date1));
            cal2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l=cal2.getTimeInMillis()-cal1.getTimeInMillis();
        int days=new Long(l/(1000*60*60*24)).intValue();
        return days;
    }

    /**
     *计算给定两个日期之间相差的天数
     */
    public static int getDayBetweenDay2(String date1,String date2){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal1=Calendar.getInstance();
        Calendar cal2=Calendar.getInstance();
        try {
            cal1.setTime(sdf.parse(date1));
            cal2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获得逾期天数
        BigDecimal l = new BigDecimal(cal2.getTimeInMillis()-cal1.getTimeInMillis());
        MathContext mc = new MathContext(3, RoundingMode.FLOOR);
        BigDecimal d1 = l.divide(new BigDecimal(1000*60*60*24),mc);
        String d2 = d1.toString().substring(2,4);
        int days = 0;
        if (d2.equals("00")){
            days = Integer.parseInt(d1.toString().substring(0,1));
        }else {
            days = Integer.parseInt(d1.toString().substring(0,d1.toString().indexOf("."))) + 1;
        }
        return days;
    }

    /**
     * 根据起始日和相隔月数计算终止日
     * @param startDate
     * @param months
     * @return
     */
    public static Date getEndDateByMonths(Date startDate, int months) {

        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        calendarStartDate.add(Calendar.MONTH, months);

        return calendarStartDate.getTime();
    }

    /**
     * 移除日期中的 - 或者 /
     * @param dateStr
     * @return
     */
    public static String removeLineDateString(String dateStr) {
        if (StringUtils.isBlank(dateStr))
            return dateStr;

        dateStr = dateStr.replaceAll("-", "");
        dateStr = dateStr.replaceAll("/", "");
        return dateStr;
    }

    /**
     * 移除时间中的 : 或者
     * @param timeStr
     * @return
     */
    public static String removeColonTimeString(String timeStr){
        if(StringUtils.isBlank(timeStr)){ return timeStr; }

        timeStr = timeStr.replaceAll(":","");
        return timeStr;
    }

    /**
     * 增加时间中的 : 或者其它符号
     * @param timeStr HHmmss
     * @return
     */
    public static String addColonTimeString(String timeStr,String separator){
        if(StringUtils.isBlank(timeStr) || timeStr.length() != 6){ return timeStr; }
        String hour = timeStr.substring(0,2);
        String min = timeStr.substring(2,4);
        String sec = timeStr.substring(4,6);
        timeStr = hour+separator+min+separator+sec;
        return timeStr;
    }


    /**
     * 将yyyy-MM-dd HH:mm:ss转化为yyyyMMddHHmmss格式
     * 将yyyy/MM/dd HH:mm:ss转化为yyyyMMddHHmmss格式
     * @param dateTimeStr
     * @return
     */
    public static String removeLineColonDateTime(String dateTimeStr){
        if(StringUtils.isBlank(dateTimeStr)){ return dateTimeStr; }

        dateTimeStr = dateTimeStr.replaceAll("-","");
        dateTimeStr = dateTimeStr.replaceAll("/","");
        dateTimeStr = dateTimeStr.replaceAll(":","");
        dateTimeStr = dateTimeStr.replaceAll(" ","");
        return dateTimeStr;
    }

    /**
     * 增加日期中的 - 或者 /
     * @param dateStr yyyyMMdd
     * @return
     */
    public static String addLineDateString(String dateStr, String separator) {
        if (dateStr == null || dateStr.length() != 8)
            return dateStr;

        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(4, 6);
        String day = dateStr.substring(6, 8);
        dateStr = year + separator + month + separator + day;
        return dateStr;
    }

    /**
     * 给定日期，计算该日期是周几
     * @param date YYYYMMDD格式的日期数据
     * @return
     */
    public static String getWeekday(String date) {
        if (date == null || date.length() != 8) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(DateUtil.stringToDate(date));
        } catch (Exception ex) {
            return null;
        }

        return week[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String getTime(String time, String sourcePattern, String targetPattern) {
        try {
            SimpleDateFormat old = new SimpleDateFormat(sourcePattern);
            Date date = old.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat(targetPattern);
            return formatter.format(date);
        } catch (Exception e) {
            return " ";
        }
    }

    public static String getDate(String time, String sourcePattern, String targetPattern) {
        try {
            SimpleDateFormat old = new SimpleDateFormat(sourcePattern);
            Date date = old.parse(time);
            SimpleDateFormat formatter = new SimpleDateFormat(targetPattern);
            return formatter.format(date);
        } catch (Exception e) {
            return " ";
        }
    }

    /**
     *
     * 根据结束日期(yyyy-MM-dd）获取最近相隔intervalMonth月的开始日期(yyyy-MM-dd)
     * @param intervalMonth
     * @return
     */
    public static String getStartDateM(String endDate, int intervalMonth) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        Date eDate = df.parse(endDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(eDate);
        cal.add(Calendar.MONTH, -intervalMonth);
        Date startDate = cal.getTime();
        return df.format(startDate);
    }

    /**
     *
     * 根据结束日期(yyyy-MM-dd）获取最近相隔intervalDay月的开始日期(yyyy-MM-dd)
     * @param intervalDay
     * @return
     */
    public static String getStartDateD(String endDate, int intervalDay) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
        Date eDate = df.parse(endDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(eDate);
        cal.add(Calendar.DAY_OF_MONTH, -intervalDay);
        Date startDate = cal.getTime();
        return df.format(startDate);
    }

    /**
     * 天数加1
     *
     * @param date
     * @return
     */
    public static String getDayOverOne(String date) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_NUMBER_PATTERN);
            Date dd = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return format.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    public static String getTbsday(String tbsdy) {
        String year = tbsdy.substring(0, 4);
        String month = tbsdy.substring(4, 6);
        String day = tbsdy.substring(6, 8);
        return year + "-" + month + "-" + day;
    }

    public static String getTbsMonth(String tbsdy) {
        String year = tbsdy.substring(0, 4);
        String month = tbsdy.substring(4, 6);
        return year + "-" + month;
    }

    /**
     * 返回yyyyMMddHHmmss字符串
     *
     * @param tbsday GLOBAL日期
     * @return GLOBAL日期 +后六位时分秒
     */
    public static String getGlobalDateAndTime(String tbsday) {
        String timeStamp;
        String dateTime = getDateAndTime();

        timeStamp = tbsday + dateTime.substring(8, 14);

        return timeStamp;
    }

    /**
     * 返回yyyyMMddHHmmssss字符串
     *
     * @param tbsday GLOBAL日期
     * @return GLOBAL日期 +后六位时分秒
     */
    public static String getGlobalDateAndTimeSS(String tbsday) {
        String timeStamp;
        String dateTime = getDateAndTimeSS();

        timeStamp = tbsday + dateTime.substring(8, 14);

        return timeStamp;
    }

    /**
     * 天数减1
     *
     * @param date
     * @return
     */
    public static String getDayReduOne(String date) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_NUMBER_PATTERN);
            Date dd = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return format.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到月份第一天的日期
     *
     * @Methods Name getFirstDayOfMonth
     * @return Date
     */
    public static String getFirstDayOfMonth(String date){
        try{
            SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
            Calendar cDay = Calendar.getInstance();
            cDay.set(Calendar.YEAR,Integer.valueOf(date.substring(0, 4)));
            cDay.set(Calendar.MONTH, Integer.valueOf(date.substring(4, 6))-1);
            cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
            return df.format(cDay.getTime());
        }catch(Exception e){
            return "";
        }
    }

    /**
     * 得到月份最后一天的日期
     *
     * @Methods Name getLastDayOfMonth
     * @return Date
     */
    public static String getLastDayOfMonth(String date){
        try{
            SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
            Calendar cDay = Calendar.getInstance();
            cDay.set(Calendar.YEAR,Integer.valueOf(date.substring(0, 4)));
            cDay.set(Calendar.MONTH, Integer.valueOf(date.substring(4, 6))-1);
            cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
            return df.format(cDay.getTime());
        }catch(Exception e){
            return "";
        }
    }

}
