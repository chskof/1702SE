package chenhs;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

public class NewDateTime {
	
	@Test
	public void test1() {
		/** Clock的用法 */
		// 获取当前Clock
		Clock clock = Clock.systemUTC();
		// 通过Clock获取当前时刻
		System.out.println("当前时刻为:"+clock.instant());
		// 获取clock对应的毫秒数,与System.currentTimeMills()输出相同
		System.out.println(clock.millis());
//		System.out.println(System.currentTimeMillis());
		
		/** Duration的用法 */
		Duration d = Duration.ofSeconds(6000);
		System.out.println("6000秒相当于" + d.toMinutes() + "分");
		System.out.println("6000秒相当于" + d.toHours() + "小时");
		System.out.println("6000秒相当于" + d.toDays() + "天");
		//在clock基础上增加6000秒,返回新的Clock
		Clock clock2 = Clock.offset(clock, d);
		//可以看到clock2与clock相差1小时40分
		System.out.println("当前时刻加6000秒为:" + clock2.instant());
		
		/** Instant的用法 */
		Instant instant = Instant.now();
		System.out.println(instant);
		Instant instant2 = instant.plusSeconds(6000);
		System.out.println(instant2);
		Instant instant3 = Instant.parse("2014-02-23T10:12:35.342Z");
		System.out.println(instant3);
		//在instant3的基础上添加5小时4分钟
		Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
		System.out.println(instant4);
		//获取instant4的5天前的时刻
		Instant instant5 = instant4.minus(Duration.ofDays(5));
		System.out.println(instant5);
		
		/** LocalDate的用法 */
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		//获取2014年的第146天
		localDate = LocalDate.ofYearDay(2014, 146);
		System.out.println(localDate);
		//设置为2014年5月21日
		localDate = LocalDate.of(2014, Month.MAY, 21);
		System.out.println(localDate);
		
		/** LocalTime的用法 */
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);
		localTime = LocalTime.of(22, 33);
		System.out.println(localTime);
		//返回一天中的第5503秒
		localTime  = localTime.ofSecondOfDay(5503);
		System.out.println(localTime);
		
		/** LocalDateTime的用法 */
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
		//当前日期时间加上25小时3分钟
		LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);
		System.out.println(future);
		
		/** 下面是关于Year YearMonth MonthDay的用法示例 */
		Year year = Year.now();
		System.out.println("当前年份:"+year);
		year = year.plusYears(5);
		System.out.println(year);
		//根据指定月份获取YearMonth
		YearMonth ym = year.atMonth(10);
		System.out.println(ym);
		//当前年月再加5年 减3个月
		ym = ym.plusYears(5).minusMonths(3);
		System.out.println(ym);
		MonthDay md = MonthDay.now();
		System.out.println(md); //--MM-dd
		//设置为5月23日
		MonthDay md2 = md.with(Month.MAY).withDayOfMonth(23);
		System.out.println(md2);
	}
	
	@Test
	public void test2() {
		DateTimeFormatter[] formatters = new DateTimeFormatter[] {
			//直接使用常量创建DateTimeFormatter格式器
			DateTimeFormatter.ISO_LOCAL_DATE,
			DateTimeFormatter.ISO_LOCAL_TIME,
			DateTimeFormatter.ISO_LOCAL_DATE_TIME,
				
			//使用本地化的不同风格来创建DateTimeFormatter
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,FormatStyle.MEDIUM),
			DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),
			
			//根据模式字符串来创建DateTimeFormatter格式器
			DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
		};
		
		LocalDateTime date = LocalDateTime.now();
		
		//依次使用不同的格式器对LocalDateTime进行格式化
		for(int i = 0 ; i < formatters.length ; i++) {
			//下面两行代码的作用相同
			System.out.println(date.format(formatters[i]));
			System.out.println(formatters[i].format(date));
		}
	}
	
	@Test
	public void test3() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String nowStr = formatter.format(now);
		System.out.println(nowStr);
		
		LocalDateTime parse = LocalDateTime.parse("2020-10-01 13:40:50", formatter);
		System.out.println(parse);
	}
	
	
	/**
	  Instant：瞬时实例。
		LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
		LocalTime：本地时间，不包含日期。
		LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
		ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
	 */
	@Test
	public void test4() {
		//Java 8 中的 LocalDate 用于表示当天日期。和 java.util.Date不同，LocalDate只有日期，不包含时间。
		//当你仅需要表示日期时就用这个类。
		LocalDate today = LocalDate.now();
		System.out.println(today);
		
		//对比:
		Date date = new Date();
		System.out.println(date);
	}
	
	@Test
	public void test5() {
		//获取年、月、日信息
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		
		System.out.printf("Year : %d Month : %d : day :%d  %n",year,month,day);
		
		System.out.println(today.getMonth());
	}
	
	@Test
	public void test6() {
		/*	在第一个例子里，我们通过静态工厂方法now()非常容易地创建了当天日期。\ 我们还可以调用另一个有用的工厂方法 LocalDate.of() 创建任意日期， 
		 * 该方法需要传入年、月、日做参数，返回对应的LocalDate实例。这个方法的好处是没再犯老API的设计错误，比如年度起始于1900，月份是从 0 开始等等。
		 * 日期所见即所得，就像下面这个例子表示了1月21日，直接明了。
		 */
		LocalDate dateOfbirth = LocalDate.of(2018, 01, 21);
		System.out.println(dateOfbirth);
	}
	
	@Test
	public void test7() {
		//判断两个日期是否相等
		LocalDate today = LocalDate.now();
		LocalDate date1 = LocalDate.of(2020, 06, 30);
		if(date1.equals(today)) {
			System.out.println(today + "  -----  " + date1);
		}
	}
	
	@Test
	public void test8() {
		/*
		检查像生日这种周期性事件
		Java 中另一个日期时间的处理就是检查类似生日、纪念日、法定假日（国庆以及春节）、或者每个月固定时间发送邮件给客户 这些周期性事件。
		Java中如何检查这些节日或其它周期性事件呢？答案就是 MonthDay类。这个类组合了月份和日，去掉了年，这意味着你可以用它判断每年都会发生事件。
		和这个类相似的还有一个 YearMonth类。这些类也都是不可变并且线程安全的值类型。
		*/
		LocalDate today = LocalDate.now();
		LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);
		MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(today);
		if(currentMonthDay.equals(birthday)) {
			System.out.println("happy birthday");
		}
	}
	
	@Test
	public void test9() {
		//获取当前时间
		LocalTime time = LocalTime.now();
		System.out.println(time);
	}
	
	@Test
	public void test10() {
		//在现有的时间上增加小时
		LocalTime time = LocalTime.now();
		LocalTime newTime = time.plusHours(2).plusMinutes(10);
		System.out.println(newTime);
	}
	
	@Test
	public void test11() {
		//计算一个星期之后的日期
		LocalDate today = LocalDate.now();
		LocalDate nextWeek = today.plus(1,ChronoUnit.WEEKS);
		LocalDate nextWeek2 = today.plusWeeks(1);
		System.out.println("Today is : " + today );
		System.out.println("Date after 1 week:"+ nextWeek);
		System.out.println(nextWeek2);
	}
	
	@Test
	public void testMinus() {
		LocalDate today = LocalDate.now();
		LocalDate lastWeek = today.minusWeeks(1);
		
		
		System.out.println(lastWeek);
		
		
	
		
	}
	
	@Test
	public void test12() {
		LocalDate today = LocalDate.now();
		LocalDate previousYear = today.minus(1,ChronoUnit.YEARS);
		System.out.println("Date before 1 year : " + previousYear);
		
		LocalDate plusMonths = today.plusMonths(2);
		System.out.println("after 2 Months : " + plusMonths);
		
		LocalDate nextYear = today.plus(1,ChronoUnit.YEARS);
		System.out.println("Date after 1 year : "+nextYear);
	}
	
	@Test
	public void test13() {
		/**
		 * Java 8增加了一个 Clock 时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。
		 * 以前用到System.currentTimeInMillis() 和 TimeZone.getDefault() 的地方都可用Clock替换。
		 */
		
		//根据系统时间返回当前时间并设置为UTC
		Clock clock = Clock.systemUTC();
		System.out.println("Clock : " + clock);
		
		// 根据系统时钟区域返回时间
		Clock defaultClock = Clock.systemDefaultZone();
		System.out.println("Clock : " + clock);
	}
	
	@Test
	public void test14() {
		//LocalDate 类有两类方法 isBefore() 和 isAfter() 用于比较日期
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = LocalDate.of(2018, 1, 29);
		if(tomorrow.isAfter(today)) {
			System.out.println("tomorrow comes ater today");
		}
		
		//减去一天
		LocalDate yesterday = today.minus(1,ChronoUnit.DAYS);
		if(yesterday.isBefore(today)) {
			System.out.println("Yesterday is day before today");
		}
	}
	
	@Test
	public void test15() {
		//设置时区
		ZoneId america = ZoneId.of("America/New_York");
		LocalDateTime localDateAndTime = LocalDateTime.now();
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateAndTime,america);
		System.out.println("现在的日期和时间在特定的时区:" + dateAndTimeInNewYork);
	}
	
	@Test
	public void test16() {
		//YearMonth 实例的 lengthOfMonth() 方法可以返回当月的天数，在判断2月有28天还是29天时非常有用
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month Year %s : %d%n", currentYearMonth,currentYearMonth.lengthOfMonth());
		
		YearMonth creditCardExpiry  = YearMonth.of(2028, Month.FEBRUARY);
		System.out.printf("your credit card expires on %s %n",creditCardExpiry);
	}
	
	@Test
	public void test17() {
		//检查闰年
		LocalDate today = LocalDate.now();
		if(today.isLeapYear()) {
			System.out.println("This year is Leap year");
		}else {
			System.out.println(today.getYear() + "is not a Leap year");
		}
	}
	
	@Test
	public void test18() {
		//计算两个日期之间的天数和月数
		LocalDate today = LocalDate.now();
		LocalDate java8Release = LocalDate.of(2018, Month.MAY, 14);
		Period periodToNextJavaRelease = Period.between(today, java8Release);
		System.out.println("Months left between today an Java 8 Release : " + periodToNextJavaRelease.getYears());
	}
	
	@Test
	public void test19() {
		/**
		 * 包含时差信息的日期和时间
			ZoneOffset类用来表示时区，举例来说印度与GMT或UTC标准时区相差+05:30，
			可以通过ZoneOffset.of()静态方法来 获取对应的时区。一旦得到了时差就可以
			通过传入LocalDateTime和ZoneOffset来创建一个OffSetDateTime对象。
		 */
		
		LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY,14,19,30);
		System.out.println(datetime);
		
		ZoneOffset offset = ZoneOffset.of("+05:30");
		OffsetDateTime date = OffsetDateTime.of(datetime, offset);
		System.out.println("Date and Time with timezone offset in Java : " + date);
	}
	
	@Test
	public void test20() {
		//Instant类有一个静态工厂方法now()会返回当前的时间戳
		Instant timestamp = Instant.now();
		System.out.println(timestamp);
	}
	
	@Test
	public void test21() {
		//只获取年月日
		LocalDate localDate = LocalDate.now();
		LocalDate localDate1 = LocalDate.of(2019, 9, 10);
		
		//获取年 月 日 星期几
		int year = localDate.getYear();
		int year1 = localDate.get(ChronoField.YEAR);
		
		Month month = localDate.getMonth();
		int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
		
		int day = localDate.getDayOfMonth();
		int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
		
		DayOfWeek dayofWeek = localDate.getDayOfWeek();
		int dayOfweek1 = localDate.get(ChronoField.DAY_OF_WEEK);
		
		//获取时 分 秒
		LocalTime localTime = LocalTime.now();
		LocalTime localTime1 = LocalTime.of(13, 51,10);
		
		
		int hour = localTime.getHour();
		int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
		
		int minute = localTime.getMinute();
		int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
		
		int second = localTime.getMinute();
		int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
		
		//获取年月日时分秒
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER,10,14,46,50);
		LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
		LocalDateTime localDateTime3 = localDate.atTime(localTime);
		LocalDateTime localDateTime4 = localTime.atDate(localDate);
		
//		System.out.println(localTime + "  ---  " + localDateTime3);
//		System.out.println(localDate + "  ---  " + localDateTime4);
		
		LocalDate localDate2 = localDateTime.toLocalDate();
		LocalTime localTime2 = localDateTime.toLocalTime();
		
		//获取秒数
		Instant instant = Instant.now();
		Long currentSecond = instant.getEpochSecond();
//		System.out.println(currentSecond);
		Long currentMilli = instant.toEpochMilli();
		
	}
}
