/**
 * 58.com Inc.
 * Copyright (c) 2005-2012 All Rights Reserved.
 */
package com.wage.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 * @author lijian02
 * @version $Id: DateUtil.java, v 0.1 2012-5-29 下午4:47:31 lijian02 Exp $
 */
public class DateUtil {

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple            = "yyyy-MM-dd HH:mm:ss";

    public static final String simpleStartDay            = "yyyy-MM-dd 00:00:00";

    public static final String simpleEndDay            = "yyyy-MM-dd 23:59:59";

    /** 年月日 yyyy-MM-dd */
    public static final String dtSimple          = "yyyy-MM-dd";

    public static final int    millisecondOfDay  = 1000 * 60 * 60 * 24;

    public static final int    millisecondOfHour = 1000 * 60 * 60;
    
    public static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
    
    public static DateFormat getYMD() {
		DateFormat df = threadLocal.get();
		if(df == null) {
			threadLocal.set(new SimpleDateFormat("yyyyMMdd"));
			df = threadLocal.get();
		}
		return df;
	}
    
    
    
    /**
     * 一天的毫秒数
     */
    public static final long DAY_MS = 24 * 60 * 60 * 1000L;

    /**
     * 秒与毫秒进制
     */
    public static final Long SECOND_MS = 1000L;
    /**
     * 日期格式，(yyyy-mm-dd)
     */
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    /**
     * 日期格式，(yyyy/mm/dd)
     */
    public static final String PATTERN_DAY_SLASH_0 = "yyyy/MM/dd";
    /**
     * 日期格式，(yyyy/m/d)
     */
    public static final String PATTERN_DAY_SLASH_1 = "yyyy/M/d";
    /**
     * 日期格式，(yyyy-MM)
     */
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_MONTH_S = "yyyyMM";
    public static final String PATTERN_MD = "yyyy-MM-01";
    /**
     * 日期格式，(yyyymmdd)
     */
    public static final String PATE_COMPARE = "yyyyMMdd";
    /**
     * 日期格式，(yyyy年mm月dd日)
     */
    public static final String PATTERN_DAY_C = "yyyy年MM月dd日";
    /**
     * 日期格式，(yyyy-MM-dd HH:mm:ss)
     */
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式，(yyyy/MM/dd HH:mm:ss)
     */
    public static final String PATTERN_FULL_SLASH_0 = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期格式，(yyyy/M/d H:m:s)
     */
    public static final String PATTERN_FULL_SLASH_1 = "yyyy/M/d H:m:s";
    public static final String PATTERN_FULL_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    /**
     * 日期格式，(yyyyMMdd_HHmmss)
     */
    public static final String PATTERN_FULL_S = "yyyyMMdd_HHmmss";
    /**
     * 分钟最小值
     */
    public static final String MINUTE_MIN = " 00:00:00";
    /**
     * 分钟最大值
     */
    public static final String MINUTE_MAX = " 23:59:59";
    /**
     * 一号
     */
    public static final String firstDay = "-01";
	private static SimpleDateFormat datePartFormat = new SimpleDateFormat(PATTERN_DAY);
	private static SimpleDateFormat defaultDatetimeFormat = new SimpleDateFormat(PATTERN_FULL);
	private static SimpleDateFormat timePartFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * 获取格式
     * @param format
     * @return
     */
    public static DateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String simple(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(simple).format(date);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dtSimple(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(dtSimple).format(date);
    }
    
    /**
     * yyyyMMdd
     * @param date
     * @return
     */
    public static String dtCompareSimple(Date date){
        if (date == null) {
            return "";
        }
        return getFormat(PATE_COMPARE).format(date);
    }

    /**
     * 当前时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String simpleFormat() {

        return simple(new Date());
    }

    /**
     * 获取传入时间相差的日期
     * @param dt  传入日期，可以为空
     * @param diffDay   需要获取相隔diff天的日期 如果为正则取以后的日期，否则时间往前推
     * @return
     */
    public static Date getDiffDay(Date dt, int diffDay) {
        Calendar ca = Calendar.getInstance();

        if (dt == null) {
            ca.setTime(new Date());
        } else {
            ca.setTime(dt);
        }
        ca.add(Calendar.DATE, diffDay);
        return ca.getTime();
    }

    /**
     * 获取输入日期月份的相差日期
     *
     * @param dt
     * @param diffMon
     * @return
     */
    public static Date getDiffMon(Date dt, int diffMon) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, diffMon);
        return c.getTime();
    }
    
    /**
     * 获取输入日期月份的相差年份
     *
     * @param dt
     * @param diffYear
     * @return
     */
    public static Date getDiffYear(Date dt, int diffYear) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.YEAR, diffYear);
        return c.getTime();
    }

    /**
     * 获取输入日期月份的相差日期
     *
     * @param dt
     * @param diffHour
     * @return
     */
    public static Date getDiffHour(Date dt, int diffHour) {
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.HOUR, diffHour);
        return c.getTime();
    }

    /**
     * 返回日期相差天数，进位向上取整数
     * <p><code>startDate < endDate</code>  结果为正</p>
     * @param startDate
     * @param endDate
     *
     * @return
     */
    public static int countDays(Date startDate, Date endDate) {

        if ((startDate == null) || (endDate == null)) {
            return 0;
        }
        double diffMs = endDate.getTime() - startDate.getTime();
        return (int) Math.ceil(diffMs / millisecondOfDay);
    }

    /**
     * 计算时间段包含的天数（包括起始时间当天）
     * <p><code>startDate < endDate</code>  结果为正</p>
     * @param startDate
     * @param endDate
     *
     * @return
     */
    public static int countContainDays(Date startDate, Date endDate) {

        if ((startDate == null) || (endDate == null)) {
            return 0;
        }
        startDate = toDate(startDate);
        endDate = toDate(endDate);
        double diffMs = endDate.getTime() - startDate.getTime();
        return (int) Math.ceil(diffMs / millisecondOfDay) + 1;
    }

    /**
     * 返回日期相差小时，进位向上取整数
     * <p><code>startDate < endDate</code>  结果为正</p>
     * @param startDate
     * @param endDate
     *
     * @return
     */
    public static int countHours(Date startDate, Date endDate) {

        if ((startDate == null) || (endDate == null)) {
            return 0;
        }
        double diffMs = endDate.getTime() - startDate.getTime();
        return (int) Math.ceil(diffMs / millisecondOfHour);
    }

    /**
     * 将字符串yyyy-MM-dd HH:mm:ss转化为时间
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public static Date toDateTime(String stringDate) throws ParseException {

        if (stringDate == null) {
            return null;
        }

        return getFormat(simple).parse(stringDate);
    }

    /**
     * 将字符串yyyy-MM-dd转化为时间
     * @param stringDate
     * @return
     * @throws ParseException
     */
    public static Date toDate(String stringDate) throws ParseException {

        if (stringDate == null) {
            return null;
        }

        return getFormat(dtSimple).parse(stringDate);
    }

    /**
     * 将时间转换为只用日期
     * @param date
     * @return
     */
    public static Date toDate(Date date) {

        Calendar ca = Calendar.getInstance();
        if (date == null) {
            ca.setTime(new Date());
        } else {
            ca.setTime(date);
        }
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();

    }

    /**
     * 获得两个日期之前最大一个
     * <p> 2012-01-02 < 2012-02-02</p>
     * @param date1
     * @param date2
     * @return
     */
    public static Date getMax(Date date1, Date date2) {

        if (date1 == null || date2 == null) {
            return null;
        }
        return date1.after(date2) ? date1 : date2;
    }
    
    /**
	 * 普通查询，以及对于年底与年初查询上月数据时，去月表查询
	 * @param date
	 * @return
	 */
	public static String getTableNameByDate(Date date){
		return getTableNameByDate(date,false);
	}
	
    /**
	 * 普通查询，以及对于年底与年初查询上月数据时，去月表查询
	 * @param date
	 * @param isCpc
	 * @return
	 */
	public static String getTableNameByDate(Date date,boolean isCpc){
		String baseTbName = "amortize_order_info_";
		if(isCpc){
			 baseTbName = "amortize_order_info_cpc_";
		}
		String tableName = "";
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int nYear = c.get(Calendar.YEAR);
		int nMonth = c.get(Calendar.MONTH) + 1;

		c.setTime(date);
		int oYear = c.get(Calendar.YEAR);
		int oMonth = c.get(Calendar.MONTH) + 1;

		/*if (nYear == oYear) {
			tableName = baseTbName + oMonth;
		} else if (nMonth == 1 && (oYear == nYear - 1) && oMonth == 12) {// 一月查询上年12月份的数据
			tableName = baseTbName + 12;
		} else {
			tableName = baseTbName + oYear;
		}*/
		
		if (nYear == oYear) {
			if(nMonth<12){
				tableName = baseTbName + oMonth;
			}else if(oMonth<12){
				tableName = baseTbName + oYear;
			}else{
				tableName = baseTbName + 12;
			}
		} else  {// 查询以前年的数据
			tableName = baseTbName + oYear;
		} 
		return tableName;
	}
	
	
	
	/**
	 * 对于年底和年初查询上一个月数据时，月表没有查到，再到年表查询
	 * @param date
	 * @return
	 */
	public static String getSpecialTableNameByDate(Date date){
		return getSpecialTableNameByDate(date,false);
	}
	
	
	/**
	 * 对于年底和年初查询上一个月数据时，月表没有查到，再到年表查询
	 * @param date
	 * @param isCpc
	 * @return
	 */
	public static String getSpecialTableNameByDate(Date date,boolean isCpc){
		String baseTbName = "amortize_order_info_";
		if(isCpc){
			baseTbName = "amortize_order_info_cpc_";
		}
		String tableName = "";
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int nYear = c.get(Calendar.YEAR);
		int nMonth = c.get(Calendar.MONTH) + 1;

		c.setTime(date);
		int oYear = c.get(Calendar.YEAR);
		int oMonth = c.get(Calendar.MONTH) + 1;
		
		/*if (nMonth == 1 && (oYear == nYear - 1)) {// 一月查询上年12月份的数据，
			// 数据可能进入年表
			tableName = baseTbName + oYear;
		} else if (nMonth == 12 && oYear == nYear && oMonth < 12) {// 十二月查询
			// 当年前11月的数据，数据可能进入了年表
			tableName = baseTbName + oYear;
		}*/ 
		if(oYear==nYear-1&&oMonth>nMonth){//查询还没进入年表
			tableName = baseTbName + oMonth;
		}else if (nMonth == 12 && oYear == nYear) {// 十二月查询还没进年表
			tableName = baseTbName + oMonth;
		}
		return tableName;
	}
	
	public static Date getFirstDayofYear(Date date){
		
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.HOUR,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.AM_PM,Calendar.AM);
		return c.getTime();
	}
	
	
	public static Date getFirstDayofYear(int year){
		Calendar c=Calendar.getInstance();
		c.set(Calendar.YEAR,year);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.HOUR,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.AM_PM,Calendar.AM);
		return c.getTime();
	}
    
    public static void main(String[] args) throws Exception {
        Date date = DateUtil.toDateTime("2012-12-28 00:00:00");
        System.out.println(DateUtil.simple(date));
        
        date = DateUtil.toDateTime("2012-12-28 23:59:59");
        System.out.println(DateUtil.simple(date));
        
        date = DateUtil.toDate(date);
        System.out.println(DateUtil.simple(date));
        
        date = DateUtil.getDiffHour(date, -2);
        System.out.println(DateUtil.simple(date));
        
    }
    
    public static long currentTime() {
        return System.currentTimeMillis();
    }

    public static long currentNanoTime() {
        return System.nanoTime();
    }

    /**
     * 格式化日期（yyyy-MM-dd）
     *
     * @param date 日期
     * @return 格式化日期字符串
     */
    public static String formatYYYYMMDD(Date date) {
    	if(date != null){
    		 SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DAY);
    	      return sdf.format(date);
    	}
    	return null;
    }

    public static String formatUTC(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULL_UTC);
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1970, 1, 1);
            date = calendar.getTime();
        }
        return sdf.format(date);
    }

    public static String formatYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULL);
        return sdf.format(date);
    }

    /**
     * 格式化日期（指定格式）
     *
     * @param date 日期
     * @return 格式化日期字符串
     */
    public static String formatDate(Date date, String pattern) {
    	if (date == null) {
			return "";
		}
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param date 时间
     * @return 格式化时间字符串
     */
    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULL);
        return sdf.format(date);
    }

    /**
     * 格式化时间（指定格式）
     *
     * @param date    时间
     * @param pattern 指定格式
     * @return 格式化时间字符串
     */
    public static String formatTime(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String formatYYYYMMDD_HHMMSS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULL);
        return sdf.format(date);
    }

    /**
     * 将以字符串形式表示的日期转换为Date类型
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期对象
     * @throws ParseException 
     */
    public static Date parseDate(String dateStr, String pattern) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    public static Date parseYYYYMMDDHHMMSS(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULL);
        return sdf.parse(dateStr);
    }

    public static Date parseYYYYMM(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_MONTH);
        return sdf.parse(dateStr);
    }

    public static String formatYYYYMM01(Date date) throws Exception {
        return DateUtil.formatDate(date, "yyyy-MM") + DateUtil.firstDay;

    }
    
	/** 为innerHTML格式化日期、时间
	 * <p>入参date为空时返回""</p>
	 * @param date
	 * @param useBrSplit 日期部分和时间部分之间插入换行标签"&lt;br /&gt;"
	 * @return
	 */
	public static String formatDtNewline4Html(Date date, boolean useBrSplit) {
		if (date == null) {
			return "";
		} else {
			try {
				if (useBrSplit) {
					return datePartFormat.format(date) + "<br />" + timePartFormat.format(date);
				} else {
					return defaultDatetimeFormat.format(date);
				}
			} catch (Exception e) {
				return date.toString();
			}
		}
	}
    
    public static Date parseYYYYMMDD(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DAY);
        return sdf.parse(dateStr);
    }
    
    public static Date parseDateStrToYYYYMMDD(String dateStr) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate= formatter.parse(dateStr);
        
        return newDate;
    }

    public static Date toYYYYMMDD(Date date) throws Exception {
        String dateStr = formatYYYYMMDD(date);

        return parseDate(dateStr, PATTERN_DAY);
    }

    /**
     * 将时间格式返回指定格式的日期格式时间
     *
     * @param date    日期对象
     * @param pattern 格式
     * @return
     * @throws Exception
     */
    public static Date parseDate(Date date, String pattern) throws Exception {
        String dateStr = formatDate(date, pattern);

        return parseDate(dateStr, pattern);
    }

    public static Date parseDate(Date date) throws Exception {
        String dateStr = formatDate(date, PATTERN_DAY);

        return parseDate(dateStr, PATTERN_DAY);
    }

    /**
     * 计算2个日期的差值（根据不同的时间单位计算出不同时间单位的差值）
     *
     * @param start
     * @param end
     * @param unit  时间单位
     * @return 时间差值（单位与unit参数单位相同.当end>start返回正值，否则返回负值）
     */
    public static long between(Date start, Date end, TimeUnit unit) {

        if (start == null || end == null || unit == null) {
            return 0;
        }

        long duration = end.getTime() - start.getTime();
        return unit.convert(duration, TimeUnit.MILLISECONDS);
    }

    /**
     * 计算2个日期的差值
     * @return 时间差值
     */
    public static String getTimeDifference(Date beginTime, Date endTime) {
        Long diff = DateUtil.between(beginTime,endTime, TimeUnit.SECONDS);
        Long day = (diff / (24 * 3600));
        Long hour = (diff % (24 * 3600) / 3600);
        Long minute = (diff % 3600 / 60);
        Long second = (diff % 60);

        StringBuilder sb = new StringBuilder();
        if(!day.equals(0L))
            sb.append(day).append("天");
        if(!hour.equals(0L))
            sb.append(hour).append("小时");
        if(!minute.equals(0L))
            sb.append(minute).append("分");
        if(!second.equals(0L))
            sb.append(second).append("秒");
        return sb.toString();
    }

    /**
     * 获得指定时间半年前时间
     *
     * @param day
     * @return
     * @throws Exception
     */
    public static Date halfYearAgo(Date day) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.MONTH, -6);

        return cal.getTime();
    }

    /**
     * 获得指定时间一年前时间
     *
     * @param day
     * @return
     * @throws Exception
     */
    public static Date oneYearAgo(Date day) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.YEAR, -1);

        return cal.getTime();
    }

    /**
     * 获得指定时间的前一天(yyyy-MM-dd)
     *
     * @param day 指定时间
     * @return
     */
    public static Date yesterday(Date day) throws Exception {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(day.getTime() - DAY_MS));

        String dateStr = formatYYYYMMDD(c.getTime());

        return parseDate(dateStr, PATTERN_DAY);
    }
    
    /**
     * 获得系统时间的前一天(yyyy-MM-dd)
     * @return
     * @throws Exception
     */
    public static Date yesterday() throws Exception {
    	Date day = new Date();
    	return yesterday(day);
    }

    /**
     * 返回当月第一天
     *
     * @return
     */
    public static String getBeginDayByMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        String beginTime1 = datef.format(beginTime);
        return beginTime1;
    }

    /**
     * 获得指定时间的前一天
     *
     * @param day     日期
     * @param include true:包含前一天时间, false:不包含前一天时间
     * @return
     * @throws Exception
     */
    public static Date yesterday(Date day, boolean include) throws Exception {
        Date date = yesterday(day);
        if (include) {
            StringBuilder temp = new StringBuilder();
            temp.append(formatDate(date, DateUtil.PATTERN_DAY));
            temp.append(MINUTE_MAX);
            return DateUtil.parseDate(temp.toString(), PATTERN_FULL);
        } else {
            return date;
        }
    }

    /**
     * 获得指定时间的后一天(yyyy-MM-dd)
     *
     * @param day 指定时间
     * @return
     */
    public static Date tomorrow(Date day) throws Exception {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(day.getTime() + DAY_MS));

        String dateStr = formatYYYYMMDD(c.getTime());

        return parseDate(dateStr, PATTERN_DAY);
    }

    /**
     * 获得今天日期
     *
     * @return 日期类型
     * @throws Exception
     */
    public static Date today() throws Exception {
        Date now = new Date();

        return parseDate(now);
    }

    /**
     * 获得当前月的第一天
     *
     * @return 日期类型
     * @throws Exception
     */
    public static Date firstDay() throws Exception {

        Date today = today();

        return firstDayOfMonth(today);
    }

    /**
     * 获得指定日期当前月第一天
     *
     * @param day
     * @return 日期类型
     * @throws Exception
     */
    public static Date firstDayOfMonth(Date day) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        // now是今天在本月的第几天
        int now = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE, 1 - now);

        Date firstday = parseDate(c.getTime());

        return firstday;
    }

    /**
     * 获得指定日期当前月第二天
     *
     * @param day
     * @return
     * @throws Exception
     * @author ZhangJi
     * @date 2013-5-24
     */
    public static Date secondDay(Date day) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        // now是今天在本月的第几天
        int now = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE, 2 - now);

        Date firstday = parseDate(c.getTime());

        return firstday;
    }

    /**
     * day - index天数
     *
     * @param day
     * @param index
     * @return
     * @throws Exception
     */
    public static Date getDateByDateIndex(Date day, int index) throws Exception {
        Calendar c = Calendar.getInstance();
        Date d = parseDate(day, PATTERN_FULL);
        c.setTime(new Date(d.getTime() + index * DAY_MS));
        String dateStr = formatYYYYMMDD_HHMMSS(c.getTime());
        Date date = parseDate(dateStr, PATTERN_FULL);
        return date;
    }

    public static Date getDateByMonthIndex(Date day, int index) throws Exception {
        Calendar c = Calendar.getInstance();
        Date firstDay = getFirstDayOfMonth(day);
        c.setTime(firstDay);
        c.add(Calendar.MONTH, index + 1);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }

    public static Date getFullDateByMonthIndex(Date day, int index) throws Exception {
        Calendar c = Calendar.getInstance();
        Date d = parseDate(day, PATTERN_FULL);
        c.setTime(d);
        c.add(Calendar.MONTH, index);

        return c.getTime();
    }

    /**
     * 获得某天(now)往前或往后的N天
     *
     * @param now
     * @param index 为负数，则为now往前算index天；为正数，则为now往后算index天
     * @return
     */
    public static Date getDateByIndex(Date now, int index) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(now);
        cale.add(Calendar.DATE, index);
        return cale.getTime();
    }

    /**
     * 返回当月最后一天
     *
     * @param number
     * @return
     * @throws Exception
     */
    public static String getLastDayStrOfMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        String endTime1 = datef.format(endTime);
        return endTime1;
    }

    /**
     * 返回当月最后一天
     *
     * @param day
     * @return
     */
    public static String getEndDayByMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        String endTime1 = datef.format(endTime);
        return endTime1;
    }

    /**
     * 返回当月最后一天
     *
     * @param day
     * @return
     */
    public static Date getEndDateByMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();
        return endTime;
    }

    /**
     * 返回当月第一天
     *
     * @return
     */
    public static String getFirstDayStrOfMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime = cal.getTime();
        String beginTime1 = datef.format(beginTime);
        return beginTime1;
    }

    /**
     * 方法描述：获取当天的日
     *
     * @return
     */
    public static Integer getTodayFormatDD() {
        try {
            SimpleDateFormat formater = new SimpleDateFormat("dd");
            String result = formater.format(new Date());
            Integer day = Integer.parseInt(result);
            return day;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ************************************************************************
     * 方法描述：获得当日期的前一个月日期，如当前是2011-4则返回2011-3
     *
     * @return
     */
    public static String getFrontDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (month == 0) {
            month = 12;
            year = year - 1;
        }
        if (month < 10) {
            String datetime = year + "-0" + month;
            return datetime;
        } else {
            String datetime = year + "-" + month;
            return datetime;
        }
    }

    public static String getToday(String parttern) {
        return formatDate(new Date(), parttern);
    }

    public static String getYesterDay(Date day, String parttern) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(day.getTime() - DAY_MS));

        return formatYYYYMMDD(c.getTime());
    }

    /**
     * 获得当前月的第一天
     *
     * @return yyyyMMdd 00:00:00.000
     */
    public static Date getFirstDayOfMonth() {
        Date date = new Date();
        return getFirstDayOfMonth(date);
    }

    /**
     * 获得指定日期所在月的第一天
     *
     * @param date 指定的日期时间
     * @return yyyyMMdd 00:00:00.000
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // 将时分秒毫秒设置为0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 获得指定日期上个月第一天
     *
     * @param date
     * @return
     */
    public static Date getLastMonth(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);

        return getFirstDayOfMonth(cal.getTime());
    }

    /**
     * 获得当前月的最后一天
     *
     * @return yyyyMMdd 23:59:59.999
     */
    public static Date getLastDayOfMonth() {
        Date date = new Date();
        return getLastDayOfMonth(date);
    }

    /**
     * 获得指定日期所在月的最后一天
     *
     * @param date 指定的日期时间
     * @return yyyyMMdd 23:59:59.999
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // 将日期设置为下个月的第一天
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // 将时分秒毫秒设置为0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // 减去1毫秒
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    /**
     * 获取当前日期的第一天，没有时分秒
     *
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static Date getFirstDateByYM(int year, int month) throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, 1);
        return parseDate(c.getTime());
    }

    /**
     * 返回两个指定月份之间的所有月份，如2013-05到2014-10之间的月份
     *
     * @param beginDate
     * @param endDate
     * @return
     * @author YangMin
     * @date 2013-7-11
     */
    public static String[] getYearMonth(String beginDate, String endDate) {
        Integer endMonth = Integer.valueOf(endDate.substring(endDate.length() - 2, endDate.length()));
        Integer beginMonth = Integer.valueOf(beginDate.substring(beginDate.length() - 2, beginDate.length()));
        Integer beginYear = Integer.valueOf(beginDate.substring(0, 4));
        Integer endYear = Integer.valueOf(endDate.substring(0, 4));
        int c = (endYear - beginYear) * 12 + endMonth - beginMonth + 1;
        StringBuffer dateList = new StringBuffer();
        for (int i = 0; i < c; i++) {
            String date = null;
            Integer newMonth = (beginMonth + i) % 12;
            Integer newYear = beginYear;
            int d = (beginMonth + i) / 12;
            if (newMonth == 0) {
                newMonth = new Integer(12);
                newYear = newYear - 1;
            }
            if (d > 0)
                newYear = newYear + d;
            if (newMonth < 10)
                date = newYear.toString() + "-0" + newMonth.toString() + "-01";
            else
                date = newYear.toString() + "-" + newMonth.toString() + "-01";
            dateList.append(date);
            if (i != c - 1)
                dateList.append(",");
        }
        return dateList.toString().split(",");
    }

    /**
     * @param date - 传入一个时间
     * @return - 返回这个时间所在日期的最小时间
     */
    public static Date getMinTimeOfDay(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMinTimeOfHour(date));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));

        return calendar.getTime();
    }

    private static Date getMinTimeOfHour(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * @param date - 传入一个时间
     * @return - 返回这个时间所在日期的最大时间
     */
    public static Date getMaxTimeOfDay(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.setTime(getMaxTimeOfHour(calendar.getTime()));

        return calendar.getTime();
    }

    private static Date getMaxTimeOfHour(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * @param date   传入一个时间
     * @param months 在date时间基础上进行增加or减少的月份
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }

    /**
     * 获得业绩计入月份
     * <p/>
     * <pre>
     * 扣款日期与提单日在同一个月：业绩计入月份 = 扣款月份
     *
     * 扣款日期超过提单月，判断是否过了业绩终止日
     * 过了：业绩计入月份=业绩终止日所在月份
     * 没过：业绩计入月份=扣款月前一个月
     *
     * </pre>
     *
     * @param rechargeTime 下单时间
     * @param operateTime  扣款时间
     * @return
     * @throws Exception
     */
    public static Date getAddMonth(Date rechargeTime, Date operateTime)
            throws Exception {
        String addMonth = DateUtil.formatDate(operateTime, "yyyy-MM") + DateUtil.firstDay;
        return DateUtil.parseDate(addMonth, DateUtil.PATTERN_DAY);
    }
    
    
}
