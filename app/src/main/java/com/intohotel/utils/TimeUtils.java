package com.intohotel.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *时间操作工具类
 */
public class TimeUtils {

	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"MM-dd HH:mm");
	public static SimpleDateFormat dateFormat3 = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat dateFormat4 = new SimpleDateFormat(
			"MM月dd日 HH:mm:ss");
	// 消息的修改时间为9-22 12:21的形式。
	public static SimpleDateFormat dateFormat5 = new SimpleDateFormat(
			"M-dd HH:mm");
	public static SimpleDateFormat dateFormat6 = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat dateFormat7 = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * 
	 * @Method: getCurrentTime
	 * @Description: 获取当前时间
	 * @throws
	 */
	public static String getCurrentTime(SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 
	 * @Method: getStringDate
	 * @Description: 将长整型时间格式字符串转换为时间 dateFormat 字符串
	 * @param @param date
	 * @return String 时间字符串
	 * @throws
	 */
	public String getStringDate(Long date, SimpleDateFormat dateFormat) {
		String dateString = dateFormat.format(date);
		return dateString;
	}

	/**
	 * 
	 * @Method: getStringDate
	 * @Description: 将长整型时间格式字符串转换为时间 dateFormat 字符串
	 * @param @param date
	 * @return String 时间字符串
	 * @throws
	 */
	public static Long getLongDate(String timeSource) {
		Long time = 0l;
		try {
			Date date = dateFormat.parse(timeSource);
			time = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return time;
	}

	/**
	 * 
	 * @Method: getVailidy
	 * @Description: 计算倒计时
	 * @param @param endTime 结束时间时间
	 * @param @param nowTime 当前时间
	 * @param @param format
	 * @return long 返回计算结果 以小时为单位
	 * @throws
	 */
	public static long getVailidy(String endTime, String nowTime,
			SimpleDateFormat format) {
		long quot = 0;
		try {
			Date currentDate = format.parse(getCurrentTime(format));
			Date endDate = format.parse(endTime);
			Date startDate = format.parse(nowTime);

			// 当前时间大于开始时间
			if (currentDate.getTime() >= startDate.getTime()) {
				quot = endDate.getTime() - currentDate.getTime();
			} else {
				quot = endDate.getTime() - startDate.getTime();
			}
			// 小时
			quot = quot / 1000 / 60;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 
	 * @Method: getTimeInterval
	 * @Description: 计算时间间隔
	 * @param @param endTime
	 * @param @param nowTime
	 * @param @param format
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getTimeInterval(String endTime, String nowTime,
			SimpleDateFormat format) {
		long quot = 0;
		try {
			Date endDate = format.parse(endTime);
			Date startDate = format.parse(nowTime);

			quot = startDate.getTime() - endDate.getTime();
			// 分
			quot = quot / 1000 / 60;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 
	 * @Method: formatStringDate
	 * @Description: 格式化String时间字符串
	 * @param @param dateFormat1
	 * @param @param dateFormat2
	 * @param @param dateStr
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String formatStringDate(SimpleDateFormat dateFormat1,
			SimpleDateFormat dateFormat2, String dateStr) {
		String formatDate = "";
		try {
			Date date = dateFormat1.parse(dateStr);
			formatDate = dateFormat2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formatDate;
	}

	/**
	 * 
	 * @Method: getTimeInterval
	 * @Description: 计算时间间隔天数
	 * @param @param endTime
	 * @param @param nowTime
	 * @param @param format
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getTimeInterval(Long endTime, Long startTime) {
		long quot = 0;

		quot = endTime - startTime;
		// 测试时间
		quot = quot / 1000 / 60 / 60 / 24;
		// quot = quot / 1000 / 60 ;

		return quot;
	}

	// 根据业务规则返回时间格式
	public static String getTimeFormat(Object souce) {
		// 返回的时间格式串
		Date souceDate = null;
		String timeStr = "";
		try {
			if (souce instanceof String) {
				String souceStr = (String) souce;
				souceDate = dateFormat.parse(souceStr);
			}
			if (souce instanceof Long) {
				Long timeLong = (Long) souce;
				souceDate = new Date(timeLong);
			}
			// 当前时间，用于取当天的零点和当年的零点时间
			Date newTime = new Date();
			// 将下面的 理解成 yyyy-MM-dd 00：00：00 更好理解点
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String todayStr = format.format(newTime);
			Date today = format.parse(todayStr);
			// 将下面的 理解成 yyyy-00-00 00：00：00 更好理解点 新年的第一天
			format = new SimpleDateFormat("yyyy");
			String yearStr = format.format(newTime);
			Date year = format.parse(yearStr);
			// 昨天 86400000=24*60*60*1000 一天
			// 大于零点，至少是今天
			if (souceDate.getTime() >= today.getTime()) {
				format = new SimpleDateFormat("HH:mm");
				timeStr = format.format(souceDate);
			} else if (today.getTime() - souceDate.getTime() > 0
					&& today.getTime() - souceDate.getTime() < 24 * 60 * 60 * 1000) { // 昨天的时间
				timeStr = "昨天";
				// 表示今年的时间
			} else if (souceDate.getTime() - year.getTime() > 0) {
				format = new SimpleDateFormat("MM-dd");
				timeStr = format.format(souceDate);
			} else {
				format = new SimpleDateFormat("yyyy-MM-dd");
				timeStr = format.format(souceDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			timeStr = "";
		}

		return timeStr;
	}
	
	
	// 显示昨天，今天，明天，否则显示YYYY-MM-DD
	public static String getCNDateFormat(Object souce) {
		// 返回的时间格式串
		Date souceDate = null;
		String timeStr = "";
		// 将下面的 理解成 yyyy-MM-dd 00：00：00 更好理解点
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (souce instanceof String) {
				String souceStr = (String) souce;
				souceDate = format.parse(souceStr);
			}
			if (souce instanceof Long) {
				Long timeLong = (Long) souce;
				souceDate = new Date(timeLong);
			}
			// 当前时间，用于取当天的零点和当年的零点时间
			Date newTime = new Date();
			
			String todayStr = format.format(newTime);
			Date today = format.parse(todayStr);
			
			// 昨天 86400000=24*60*60*1000 一天
			if (today.getTime() - souceDate.getTime() > 0
					&& today.getTime() - souceDate.getTime() <= 24 * 60 * 60 * 1000) { // 昨天的时间
				timeStr = "昨天";
			} else if (souceDate.getTime() - today.getTime() >= 0
					&& souceDate.getTime() - today.getTime() < 24 * 60 * 60 * 1000) { // 昨天的时间
				timeStr = "今天";
			} else if (souceDate.getTime() - today.getTime() >= 24 * 60 * 60 * 1000
					&& souceDate.getTime() - today.getTime() < 24 * 2 * 60 * 60 * 1000) { // 昨天的时间
				timeStr = "明天";
			} else {
				format = new SimpleDateFormat("yyyy-MM-dd");
				timeStr = format.format(souceDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			timeStr = "";
		}

		return timeStr;
	}
	
	/**
	 * 
	 * @Method: isTimeExpired 
	 * @Description: 判断是否过期
	 * @param @param endTime 截止日期
	 * @param @param format 日期格式
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isTimeExpired(String endTime,SimpleDateFormat format){
		try {
			if(format.parse(getCurrentTime(format)).getTime()>format.parse(endTime).getTime()){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
