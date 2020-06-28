package chenhs;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;


public class ChsTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
	}

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