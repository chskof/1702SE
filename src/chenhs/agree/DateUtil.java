package chenhs.agree;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/** 日期格式 :yyyy-MM-dd */
	private static String defaultDatePattern = "yyyy-MM-dd";

	/** 日期格式 :yyyyMMdd */
	private static String easyDatePattern = "yyyyMMdd";
	
	/** 日期格式 : yyyy年MM月dd日 */
	private static String dateChinaPattern = "yyyy年MM月dd日";

	/** 日期格式 : yyyy年MM月dd日  HH时mm分ss秒 */
	private static String chinaDateTime = "yyyy年MM月dd日  HH时mm分ss秒";

	/** 日期格式 : yyyy-MM-dd hh:mm:ss*/
	public static String complicatedDatePattern = "yyyy-MM-dd hh:mm:ss";

	/** 日期格式 :yyyy-MM-dd-hh.mm.ss.SSS */
	public static String timeDatePattern = "yyyy-MM-dd-hh.mm.ss.SSS";
	
	/**
	 * 得到目前系统时间，格式为 Date
	 * @return Date
	 */
	public static Date getNowSystemTimeForDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 得到目前系统时间，格式为："yyyyMMddHHmmss"
	 * @return 系统时间的字符串表示形式，格式为："yyyyMMddHHmmss"
	 */
	public static String getNowSystemTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 得到目前系统时间，格式为："HHmmss"
	 * @return 系统时间的字符串表示形式，格式为："HHmmss"
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 得到目前系统时间，格式为："HHfmmfss"
	 * @return 系统时间的字符串表示形式，格式为："HHfmmfss"
	 */
	public static String getCurrentTime(String f) {
		String format = "HH" + f + "mm" + f + "ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 得到目前系统时间，格式为："yyyyMMdd"
	 * @return 系统时间的字符串表示形式，格式为："yyyyMMdd"
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 得到目前系统时间的3位毫秒值，格式为："SSS"
	 * @return 系统时间的毫秒值，格式为："SSS"
	 */
	public static String getNowSystemMillisecond() {
		SimpleDateFormat sdf = new SimpleDateFormat("SSS");
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 将Date转换成字符串yyyy-MM-dd
	 * @param  Date date  
	 * @return yyyy-MM-dd 时间字符串
	 */
	public static String getTimeString(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 将yyyy-MM-dd字符串解析为一个Date
	 * @param  String dateStr
	 * @return Date date
	 */
	public static Date getDateTime(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm 字符串格式
	 * @param date
	 * @return String
	 */
	public static String getTimeString2(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}
	
	/**
	 * 将字符串yyyy-MM-dd HH:mm 解析成一个日期
	 * @param dateStr
	 * @return Date
	 */
	public static Date getStringTime2(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将日期转换成 yyyy-MM-dd HH:mm:ss 字符窜形式
	 * @param date
	 * @return String
	 */
	public static String getTimeString3(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将日期转换成 HH:mm:ss字符串
	 * @param date
	 * @return String
	 */
	public static String getTimeString4(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 将字符串 yyyy-MM-dd HH:mm:ss 转换成日期
	 * @param dateStr
	 * @return Date
	 */
	public static Date getStringTime3(String dateStr) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断yyyyMMdd的字符串是否是工作日
	 */
	public static boolean isWorkDay(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return isWorkDay(sdf.parse(dateStr));
	}
	/**
	 * 判断Date是否是工作日
	 */
	public static boolean isWorkDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isWorDay(calendar);
	}
	public static boolean isWorDay(Calendar calendar) {
		int n = calendar.get(Calendar.DAY_OF_WEEK);
		if (n == 1)  //周日
			return false;
		if (n == 7)  //周六
			return false;
		return true;
	}
	
	/**
	 * 获取当前这个月的第一天的日期 yyyy-MM-dd
	 * @return
	 */
	public static String getFirstDayThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01");
		return sdf.format(DateUtil.getNowSystemTimeForDate());
	}
	
	/**
	 * 日期比较方法，字符串参数日期和当前日期的比较。
	 * @param anotherCalendar 日期参数的格式为"yyyy-MM-dd"
	 * @return 如果参数表示的时间等于当前时间，则返回 0 值； 如果当前时间在参数表示的时间之前，则返回小于 0 的值；
	 *         如果当前时间在参数表示的时间之后，则返回大于 0 的值。
	 */
	public static int DateCompareTo(String yyyyMMdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf.format(DateUtil.getNowSystemTimeForDate());
		return nowDate.compareTo(yyyyMMdd);
	}

	/**
	 * 得到当前日期加上dateNum天数的日期
	 * @param dateNum  天数，可以是正整书，或者负整数
	 * @return yyyy-MM-dd 字符串日期
	 */
	public static String getAddDateString(int dateNum) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, dateNum);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 得得到当前日期加上dateNum天数的日期
	 * @param dateNum 天数，可以是正证书，或者负整数  
	 * @param format 想返回的格式
	 * @return 字符串日期
	 */
	public static String getAddTimeString(String format, int timenum) {
		long ld = (new Date().getTime() + timenum);
		Date da = new Date(ld);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(da);
	}

	/**
	 * 在现有日期上加整数天
	 * @param String yyyyMMdd  
	 * @param int  dateNum
	 * @return yyyy-MM-dd 
	 * @throws Exception
	 */
	public static String getAddDateString(String dateString, int dateNum)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date= sdf.parse(dateString);
	    sdf = new SimpleDateFormat("yyyy-MM-dd");
	    dateString = sdf.format(date);
		String[] arry= dateString.split("-");
		int nian = Integer.parseInt(arry[0]);
		int yue = Integer.parseInt(arry[1]);
		int ri = Integer.parseInt(arry[2]);
		Calendar cal = Calendar.getInstance();
		cal.set(nian, yue, ri);
		cal.add(Calendar.MONTH, -1);
		cal.add(Calendar.DAY_OF_MONTH, dateNum);
		
		return sdf.format(cal.getTime());
	}

	/**
	 * yyyy年MM月dd日 HH时mm分ss秒 
	 * @return String
	 */
	public static String getChinaDateTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(chinaDateTime);
		return simpleDateFormat.format(new Date());
	}

	/** yyyy-MM-dd-hh.mm.ss.SSS
	 * @return String
	 */
	public static String getDateTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeDatePattern);
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 格式化日期
	 * @param pattern 匹配模式
	 * @param datestr 字符串日期
	 * @return 字符串日期
	 */
	public static String format(String pattern, String datestr)
			throws Exception {
		SimpleDateFormat simpleDateFormat = null;
		if (datestr.indexOf("-") > 0) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		}
		Date date = simpleDateFormat.parse(datestr);
		simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}



	/**
	 * 格式化日期  将日期转换成指定模式的字符串形式
	 * @param pattern 格式化模式
	 * @param date 日期
	 * @return String 字符串形式日期
	 */
	public static String format(String pattern, Date date) {
		return new SimpleDateFormat(pattern == null ? defaultDatePattern
				: pattern).format(date);
	}

	/**
	 * 转换日期 将字符串解析成指定模式的日期
	 * @param pattern  匹配模式
	 * @param datestr 字符串日期
	 * @return Date 
	 */
	public static Date parse(String pattern, String datestr)throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(datestr);
	}

	/**
	 * 转换日期    将yyyy-MM-dd字符串转换成Date
	 * @param datestr  字符串yyyy-MM-dd
	 * @return  Date
	 */
	public static Date parse(String datestr) throws Exception {
		return parse(defaultDatePattern, datestr);
	}

	/**
	 * 比较两个日期，返回它们之间相差的天数,不足一天返回0
	 * @param date1 Date
	 * @param date2 Date
	 * @return 相差的天数，如果 date1 小于 date2 返回 负数 <br>
	 */
	public static int compareDateOnDay(Date date1, Date date2) {
		long ss = date1.getTime() - date2.getTime();  //相差毫秒数
		long day = 24 * 60 * 60 * 1000;            //每天毫秒数
		return Integer.parseInt(ss / day + "");
	}

	/**
	 * 比较两个日期，返回它们之间相差的天数,不足一天返回0
	 * @param dateString1  yyyyMMdd
	 * @param dateString2  yyyyMMdd
	 * @return 相差的天数，如果 date1 小于 date2 返回 负数 
	 */
	public static int compareDateOnDay(String dateString1, String dateString2)throws Exception {
		Date date1 = parse(easyDatePattern, dateString1);
		Date date2 = parse(easyDatePattern, dateString2);
		return compareDateOnDay(date1, date2);
	}

	/**
	 * 格式化日期,返回中文日期
	 * @param dateStr  字符串日期
	 * @return String
	 */
	public static String printDate(String dateStr) {
		try {
			dateStr = dateStr.replace("-", "");
			Date date = parse(easyDatePattern, dateStr);
			return format(dateChinaPattern, date);
		} catch (Exception e) {
			return dateStr;
		}
	}

	/**
	 * 判断日期大小
	 * @param dateString1
	 * @param dateString2
	 * @param flag   A:需要判断dateString1>=dateString2 ;
	 *               B:判断dateString1>dateString2;
	 *               C:判断dateString1==dateString2
	 * @return boolean
	 */
	public static boolean compareDate(String dateString1, String dateString2,
			int flag) throws Exception {
		int dateString_1 = 0;
		int dateString_2 = 0;
		boolean _flag = false;
		String dateString11 = objectToString(dateString1);
		String dateString21 = objectToString(dateString2);
		dateString_1 = "".equals(dateString11) ? 0 : Integer
				.parseInt(dateString11);
		dateString_2 = "".equals(dateString21) ? 0 : Integer
				.parseInt(dateString21);
		if (flag == 1) {
			if (dateString_1 >= dateString_2) {
				_flag = true;
			}
		} else if (flag == 2) {
			if (dateString_1 > dateString_2) {
				_flag = true;
			}
		} else if (flag == 3) {
			if (dateString_1 == dateString_2) {
				_flag = true;
			}
		}
		return _flag;
	}
	
	/** 如果是yyyy-MM-dd格式将其转化为 yyyyMMdd */
	private static String objectToString(String dateString) throws Exception {
		if (dateString.indexOf("-") >= 0) {
			Date e = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			dateString = new SimpleDateFormat(easyDatePattern).format(e);
		}
		return dateString;
	}

	/**
	 * 针对字符串格式的时间
	 * 功能说明： 转换时间格式 HHmmss —> HH：mm：ss 字符串</DD>
	 * @param time HHmmss 字符串
	 * @return HH：mm：ss 字符串
	 */
	public static String changeTimeString(String time) throws Exception {
		SimpleDateFormat simpleDateFormat = null;
		simpleDateFormat = new SimpleDateFormat("HHmmss");
		Date date = simpleDateFormat.parse(time);
		return DateUtil.getTimeString4(date);
	}
	
	
	/**
	 * 返回两个日期之间的月份数 截止日期必须大于起始日期 
	 * @param qsrq 起始日期 yyyy-MM-DD
	 * @param jzrq 截止日期 yyyy-MM-DD
	 * @return int  两个日期之间的月份数
	 */
	public static int getMonths(String qsrq,String jzrq) throws Exception {
		//拆分 0 年份 1 月份 2日期
		String arrQs[] = qsrq.split("-");
		String arrJz[] = jzrq.split("-");
		int months = 0;
		// 截止年份减去起始年份 乘 12
		months = (Integer.valueOf(arrJz[0]) - Integer.valueOf(arrQs[0]))*12; 
		//如果截止年份小于起始年份 所等为负数
		months = months + (Integer.valueOf(arrJz[1]) - Integer.valueOf(arrQs[1])); 
		//如果截止日期的天数大于起始日期的天数 月期限+1
		if (Integer.valueOf(arrJz[2])>=Integer.valueOf(arrQs[2])){
			months = months+1;
		}
		return months;
	}
	
	
}
