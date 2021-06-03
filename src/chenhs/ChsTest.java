package chenhs;

import java.io.File;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Test;


public class ChsTest {
	
	@Test
	public void test1() {
		Student stu = new Student(1,"小明",10);
		change(stu);
		System.out.println(stu.getName()+"----"+stu.getAge());
	}
	public void change(Student stu) {
		stu.setName("小红");
		stu.setAge(8);
	}
	
	@Test
	public void test2() {
		List<String> list = null;
		System.out.println(list.size());
	}
	
	@Test
	public void test3() {
		System.out.println("10245".matches("^\\d+$"));
		System.out.println("321ddfd".matches("^\\d+$"));
		System.out.println("53.65".matches("^\\d+$"));
	}
	
	@Test
	public void test4() {
		Long a = 3l;
		Long b = 3l;
		System.out.println(a == 3l);
	}
	
	@Test
	public void test5() {
		String a ="abc";
		String[] b =a.split(",");
		System.out.println(b.length);
		System.out.println(Arrays.toString(b));
	}
	
	@Test
	public void test6() {
		String ttt = "afd";
		System.out.println("[{\"reson\":\""+ttt+"\"}]");
	}
	
	@Test
	public void test7() {
		String a = "a";
		String b = "b";
		String c = "c";
		
		System.out.println(a+b+c);
	}
	
	@Test
	public void test8() throws ParseException {
		
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date date = sdf.parse("2019-10-17 23:59:59");//1571327940000
	 											  //1571327999000
	 Long time= date.getTime();
		System.out.println(time);
	}
	
	@Test
	public void test9() {
		String abc = "abc,";
		System.out.println(abc.charAt(abc.length()-1));
		System.out.println(abc.substring(0, abc.length()-1));
	}
	
	@Test
	public void test10() {
		String month1 = "1个月";
		String month2 = "10个月";
		String month3 = " 立行立改";
		System.out.println(month1.substring(0,1));
		System.out.println(month2.substring(0, 2));
		
		System.out.println(month3.indexOf("立行立改"));
	}
	
	@Test
	public void test11() {
		String state1 = "督办管理员签收";
		String state2 = "反馈完成";
		String state3 = "dfaf";
		
		System.out.println(state2.replace("反馈完成", "督办管理员签收"));
	}
	
	@Test
	public void test12() {
		String month1 = "2个月";
		String month2 = "12个月";
		
		System.out.println(month2.substring(0,month2.length()-2));
	}
	
	@Test
	public void test13() {
		Long a = 12L;
		Long b = 13L;
		System.out.println(a.equals(b));
	}
	
	private String removeprefix(String str){
		if(str.contains("【") && str.contains("】-") && str.contains("：")) {
			int i = str.indexOf("：")+1;
			int size = str.length();
			str = str.substring(str.indexOf("：")+1);
		}
		return str;
	}
	
	public  void test14(){
		String a = "abdc/dfd";
		String b = "fdf";
		if(b.indexOf("/") != -1) {
			System.out.println(b.substring(0, b.indexOf("/")));
		}else {
			System.out.println(b.indexOf("/"));
		}
		
	}
	
	@Test
	public void test15() {
//		 String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
	     String str = "中文12]3中文qwer";
	        // 去除中文
//	        Pattern pat = Pattern.compile(REGEX_CHINESE);
//	        Matcher mat = pat.matcher(str);
//	        String string = mat.replaceAll("");
	        
	        String string = str.replaceAll("[\u4e00-\u9fa5]", "");
	        System.out.println(string);
	}
	
	@Test
	public void test16() {
		
		String init = "0";
		for (int i =0 ;i<20;i++) {//模拟点击20次
			init =  String.valueOf((Integer.parseInt(init) + 1)); 
			init=String.format("%06d", Integer.parseInt(init));//第二个参数为int类型，0代表前面要补的字符 10代表字符串长度,d表示参数为整数类型
			System.out.println(init);
		}
		
	}
	
	@Test
	public void test17() {
		
		
		File[] list = new File("E:\\test").listFiles();
		List<String> fileNameList = new ArrayList<>();
//        for(File file : list)
//        {
//           if(file.isFile())
//           {
//               if (file.getName().endsWith(".tmp")) {
//                   // 就输出该文件的绝对路径
//                   System.out.println(file.getAbsolutePath());
////                   System.out.println(file.getName());
//                   fileNameList.add(file.getName());
//               }
//
//           }
//        }
		
		fileNameList.add("12.tmp");
		fileNameList.add("11.tmp");
		fileNameList.add("10.tmp");
		fileNameList.add("9.tmp");
		fileNameList.add("8.tmp");
		fileNameList.add("17.tmp");
		fileNameList.add("7.tmp");
		fileNameList.add("2.tmp");
		fileNameList.add("20.tmp");
		fileNameList.add("1.tmp");
		fileNameList.add("0.tmp");
		
	
		
		
        System.out.println(fileNameList);
        
        fileNameList =   fileNameList.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.substring(0, a.lastIndexOf("."))))
	            ).collect(Collectors.toList());
        
//        Collections.sort(fileNameList);
        
        System.out.println(fileNameList);
        
        
	}
	
	@Test
	public void test18() {
		String a = "1.tmp";
		String b = a.substring(0, a.lastIndexOf("."));
		System.out.println(b);
	}
	
	@Test
	public void test19() {
		HashSet<String> phoneList = new HashSet();
		phoneList.add("");
		phoneList.add("");
		phoneList.add("1");
		phoneList.add("2");
		phoneList.add("3");
		System.out.println(phoneList);
		
		phoneList.remove("");
		
		System.out.println(phoneList);
		
		String phones = "";
		for (String pho : phoneList) {
			phones = phones + pho + ",";
		}
		if(phones.endsWith(",")) {
			phones = phones.substring(0, phones.length()-1);
		}
		System.out.println(phones);
	}
	
	@Test
	public void test20() {
		List<String> list = new ArrayList<>();
		list.add("2020-01-01");
		list.add("2020-01-02");
		list.add("2020-01-03");
		list.add("2020-01-02");
		list.add("2020-01-03");
		list = list.stream().distinct().collect(Collectors.toList());
		System.out.println(list);
	}
	
	@Test
	public void test21() {
		String ruleSql = "delete";
		
		String reg = "(\\bdelete\\b)|(\\binsert\\b)|(\\bupdate\\b)";
		Pattern pattern=Pattern.compile(reg);
		Matcher matcher=pattern.matcher(ruleSql);
		
		System.out.println(matcher.find());
		
        if(matcher.find()){
           System.out.println("匹配到了");
        }else {
        	System.out.println("no");
        }
		
	}
	
	@Test
	public void test22() {
		
	    String ruleSql = "update";

        String reg = "(\\bdelete\\b)|(\\binsert\\b)|(\\bupdate\\b)";
        if(Pattern.matches(reg, ruleSql.toLowerCase())){
           System.out.println("000");
        }
		
	}
	
	@Test 
	public void test23() {
		 LocalDate today = LocalDate.now();
	        LocalDate lastWeekDay = today.minusWeeks(1);  //上周的此刻
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String lastWeekDaystr =  formatter.format(lastWeekDay);
	        System.out.println(lastWeekDaystr);
	}
	
	
	@Test
	public void getSize() {
		long size = 1500L;
		double s = (double) size;
		String unit;
		if (size != -1L) {
			int l;
			if (size < 1024L) {
				l = 0;
			} else if (size < 1024L * 1024L) {
				l = 1;
				s = (double) size / 1024L;
			} else {
				for (l = 2; size >= 1024L * 1024L; l++) {
					size = size / 1024L;
					if ((size / 1024L) < 1024L) {
						s = (double) size / 1024L;
						break;
					}
				}
			}

			switch (l) {
			case 0:
				unit = "Byte";
				break;

			case 1:
				unit = "KB";
				break;
			case 2:
				unit = "MB";
				break;
			case 3:
				unit = "GB";
				break;
			case 4:
				// 不可能也不该达到的值
				unit = "TB";
				break;
			default:
				// ER代表错误
				unit = "ER";

			}
			String format = String.format("%.2f", s);
			System.out.println(format + unit);
		}
		return;
	}
	
	@Test
	public void test24() {
		SecureRandom random  = new SecureRandom();
		
	}

	@Test
	public void test25() {
		String code1 = "999949";
	       String order = code1.substring(4);
           int numcode=Integer.valueOf(order).intValue()+1;

           String numStr = "";
           if(numcode < 10){//左补0
               numStr = "0" + String.valueOf(numcode);
           }else{
               numStr = String.valueOf(numcode);
           }

           String usercode = "9999" + numStr;
           System.out.println(usercode);
	}
	
	@Test
	public void test26() {
		 
			String a = "123456789";
			String b = a.substring(a.length());
			System.out.println(b);
	}
	
	@Test
	public void test27() {
		Map<String,Object> map = new HashMap<>();
		map.put("name", 1);
		String a = (String)map.get("role");
		System.out.println(a);
	}
	
	@Test
	public void test28() {
		

		String[] staffs = new String[]{"Tom", "Bob", "Jane"};
		 
		List staffsList = Arrays.asList(staffs);
		System.out.println(staffsList);
		 
//		Set result = new HashSet(staffsList);
//		System.out.println(result);
		
		 Collections.shuffle(staffsList);
		
		
//		List list = new ArrayList<>(result);
		System.out.println(staffsList);
		
	}
	
	@Test
	public void test29() {
		
		Random random = new Random();
		
		for (int i=0;i<80;i++) {
			int n = random.nextInt(60);
			System.out.println(n);
		}
		
	}
	
}




class Student {
	int id;
	String name;
	int age;
	
	public Student() {};
	
	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}