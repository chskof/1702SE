package chenhs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test1() {
		String regStr  = "Java is very easy";
		System.out.println("目标字符串是:" + regStr);
		Matcher m = Pattern.compile("\\w+").matcher(regStr);
		while(m.find()) {
			System.out.println(m.group() + "子串的起始位置:" + m.start() + ",其结束位置:"+m.end());
		}
	}
	
	@Test
	public void test2() {
		String[] mails = {"kongyeeku@163.com","kongyeeku@gmai","ligang@crazyit.org","wawa@abc.xx"};
		String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
		Pattern mailPattern = Pattern.compile(mailRegEx);
		Matcher matcher = null;
		for(String mail : mails) {
			if(matcher == null) {
				matcher = mailPattern.matcher(mail);
			}else {
				//rest()方法可以将现有的Matcher对象应用于新的字符串序列
				matcher.reset(mail);
			}
			String result = mail + (matcher.matches() ? "是" : "不是") + "一个有效的邮件地址";
			System.out.println(result);		
		}
	}
}
