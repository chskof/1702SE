package coreJava.ioFile;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 此程序提示输入一个模式,然后提示输入用于匹配的字符串,随后将打印出输入是否与模式相匹配,
 * 并且模式包含群组,那么这个程序将用括号打印出群组边界,例如:((11):(59))am 
 */
public class RegexTest {
	public static void main(String[] args) throws PatternSyntaxException{
		Scanner in = new Scanner(System.in);
		System.out.println("输入匹配模式: ");
		String patternString = in.nextLine();
		Pattern pattern = Pattern.compile(patternString);
		
		while(true) {
			System.out.println("输入字符串去进行匹配: ");
			String input = in.nextLine();
			Matcher matcher = pattern.matcher(input);
			if(matcher.matches()) {
				System.out.println("匹配");
				int g = matcher.groupCount();
				if(g>0) {
					for(int i=0;i<input.length();i++) {
						 // Print any empty groups
						for(int j=1;j<g;j++) {
							if(i==matcher.start(j) && i==matcher.end(j))
								System.out.println("()");
						}
						// Print ( for non-empty groups starting here
						for(int j=1;j<g;j++) {
							if(i==matcher.start(j) && i!=matcher.end(j))
								System.out.println("(");
							System.out.println(input.charAt(i));
						}
						// Print ) for non-empty groups ending here
						for(int j=1;j<=g;j++) {
							if(i+1 !=matcher.start(j) && i+1 == matcher.end(j))
								System.out.println(")");
						}
					}
					System.out.println();
				}
			}
			else {
				System.out.println("没有匹配");
			}
		}
	}
}
