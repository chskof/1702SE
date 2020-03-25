package chenhs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.Test;

public class DateTime {
	
	/** 自定义时间格式化 */
	@Test
	public void test1(){
		Date date = new Date();
		System.out.println(date);
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss:SSS");
		System.out.println(fmt.format(date));
	}
	
	
	/** 获取当前月的最大日期值 */
	@Test
	public void test2() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, 0); //设置为1月份
		System.out.println(cal.getTime());
		//Actual 实际 Maximum最大
		//获取cal中当前月的最大日期值
		int n = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(n); 
		
		System.out.println(cal.getTime());
		
		cal.set(Calendar.MONTH, 1);  //设置为2月份
		cal.set(Calendar.DATE, 1);   //设置为1号
		System.out.println(cal.getTime());
		//Actual 实际 Maximum最大获取cal中当前月的最大日期值
		n = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(n); 
	}
	
	
	@Test
	public void test3() {
		Calendar cal = Calendar.getInstance();
		
		int year=cal.get(Calendar.YEAR); //获取年份分量
		System.out.println(year); 
		
		int month = cal.get(Calendar.MONTH); //获取月份分量
		System.out.println(month);
		
		int d = cal.get(Calendar.DATE);  //获取日期分量
		System.out.println(d); 
		
		int w = cal.get(Calendar.DAY_OF_WEEK); //获取星期
		System.out.println(w); 
	}
	
	
	/** 历法类的使用   历法是替代 Date 类型的API，用于替换Date类型的那些过时的API方法 */ 
	@Test
	public void test4() {
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = Calendar.getInstance();
		//默认的Calendar里面封装是当前系统时间
		System.out.println(c1);
		System.out.println(c2);
		//将Calender转换为Date类型
		Date date = c1.getTime();
		SimpleDateFormat fmt =new SimpleDateFormat();
		System.out.println(fmt.format(date));
		
		//创建指定年月日的Calender
		Calendar cal = new GregorianCalendar(
				2017, Calendar.MARCH, 6);
		//输出结果：
		date = cal.getTime();
		System.out.println(fmt.format(date)); 
	}
	
	@Test
	public void test5() throws ParseException {
		
//		Date date = new Date();
//		
//		long now = date.getTime();
//		System.out.println(now);
//		System.out.println(System.currentTimeMillis());
//		long y = now/1000/60/60/24-(2017-1970)*365;
//		System.out.println(y);
//		System.out.println(date);
//		int year = date.getYear();
//		System.out.println(year);
//		
//		SimpleDateFormat fmt =new SimpleDateFormat("yyyy年MM月dd日");
//		String str = fmt.format(date);
//		System.out.println(str);
//		String sdf = "yyyy年MM月dd日";
//		Date data = fmt.parse(str);
//		System.out.println(data);
//		
		String str = "1980年5月6日";
		String pattern = "yyyy年MM月dd日";
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		Date date1  = fmt.parse(str);
		System.out.println(date1);
		System.out.println(str);
	}
	
	/** Date 类型  
        1. 默认的Date类型对象中封装当前系统时间毫秒
        2. getTime 获取时间毫秒数 
        3. 修改date中的毫秒数
        4. Date 重写了toString 方法     */
	@Test
	public void test6() {
		//默认的Date类型中封装了系统时间毫秒
		Date date = new Date();
		//获取时间毫秒数
		long now = date.getTime();
		System.out.println(now);
		long y=now/1000/60/60/24/365 + 1970;
		System.out.println(y); 
		//date 重写了toString();
		System.out.println(date); 
		
		int year = date.getYear();
		System.out.println(year); 
		//修改date中的毫秒数
		//0毫秒代表 GTM 时间的1970年元旦
		date.setTime(-1000L*60*60*24);
		System.out.println(date); 
		year = date.getYear();
		System.out.println(year); 
	}
	
	/** 将字符串解析为 系统时间（毫秒数） */
	@Test
	public void test7() throws ParseException {
		String str = "1980-5-6";
		String pattern = "yyyy-M-d";
		SimpleDateFormat fmt= new SimpleDateFormat(pattern);
		//将字符串时间解析为计算机时间
		Date date=fmt.parse(str);
		System.out.println(date);
		System.out.println(date.getTime());
		
		String id="12345619891405123122x";
		//         012345678901234
		String s = id.substring(6,6+8);
		System.out.println(s); 
		pattern = "yyyyMMdd";
		fmt=new SimpleDateFormat(pattern);
		date = fmt.parse(s);
		System.out.println(date); 
	}
	
	
	@Test
	public void test8() {
		Calendar dat = new GregorianCalendar(1992,Calendar.FEBRUARY,1);
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.getTime());
//		
//		cal.set(2000,3, 1, 16, 30, 21);
//		
//		Date date= cal.getTime();
//		System.out.println(date);
//		
//		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(fmt.format(date));
//		
//		int year = cal.get(Calendar.YEAR);
//		System.out.println(year);
//		
//		int month = cal.get(Calendar.MONTH);
//		System.out.println(month);
//		
//		int day = cal.get(Calendar.DATE);
//		System.out.println(day);
//		
//		int w = dat.get(Calendar.DAY_OF_WEEK);
//		System.out.println(w);
		
		Date date = dat.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(fmt.format(date));
		int w = dat.get(Calendar.DAY_OF_WEEK);
		System.out.println(w);
	}
	
	
	@Test
	public void test9() {
		Calendar cal = Calendar.getInstance();
		for(int i = 0; i<12;i++){
		cal.set(Calendar.MONTH,i);
		cal.set(Calendar.DATE,1);
	//	System.out.println(cal.getTime());
		int n = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(n);
		}
	}
	
	
	@Test
	public void test10() {
		Calendar cal = Calendar.getInstance();
		printData(cal);

		cal.add(Calendar.MONTH, 5);
		printData(cal);

		cal.add(Calendar.DATE, 10);
		printData(cal);

		cal.add(Calendar.YEAR, -3);
		printData(cal);
	}
	public static void printData(Calendar cal) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = cal.getTime();
		System.out.println(fmt.format(date));
	}
	
	/** 日期相对增加方法 add */
	@Test
	public void test11() {
		Calendar cal=Calendar.getInstance();
		print(cal); 
		
		//将cal中的当前时间的月份分量上
		//增加5个月的时间。
		cal.add(Calendar.MONTH, -5);
		print(cal); 
		
		cal.add(Calendar.DATE, 5);
		print(cal); 
	}
	public static void print(Calendar cal){ 
		SimpleDateFormat fmt = 
				new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = cal.getTime();
		System.out.println(fmt.format(date)); 
	}
	
	
	@Test
	public void test13() {
//		Calendar cal = new GregorianCalendar(2018,6,1);
//		print(cal);
		
		Calendar dat = Calendar.getInstance();
		dat.set(Calendar.YEAR,2018);
		dat.set(Calendar.MONTH,11);
		dat.add(Calendar.MONTH, 1);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
		
		Date date = dat.getTime();
		
		System.out.println(fmt.format(date));
	}
	
	@Test
	public void test12() throws ParseException {
		//逻辑分析：
		//输入： "2016-8-1"
		//date -> Calender
		//计算出到期时间  cal.add(YEAR,3)
		//计算提示时间 cal.add(MONTH, -2);
		//w = cal.get(DAY_OF_WEEK);
		//if(w 是星期六) cal.add(DATE,2)
		//if(w 是星期日) cal.add(DATE,1)
		//找到提醒时间
		
		//代码实现：
		Scanner in = new Scanner(System.in);
		System.out.print(
			"输入入职时间(yyyy-MM-dd)：");
		String str = in.nextLine();
		SimpleDateFormat fmt=
			new SimpleDateFormat("yyyy-MM-dd");
		Date date = fmt.parse(str);
		Calendar cal=Calendar.getInstance();
		//修改cal中的时间，为date的时间
		cal.setTime(date);
		print(cal);//入职
		cal.add(Calendar.YEAR, 3);//3年后到期
		print(cal);
		cal.add(Calendar.MONTH, -2);
		print(cal);
		int w = cal.get(Calendar.DAY_OF_WEEK);
		if(w == Calendar.SATURDAY){
			cal.add(Calendar.DATE, 2);
		}else if(w == Calendar.SUNDAY){
			cal.add(Calendar.DATE, 1);
		}
		System.out.print("提醒时间：");
		print(cal);
	}
	
	@Test
	public void test14() {
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,7);
		Date date = cal.getTime();
		print(cal);
	}
	
}
