package chenhs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;








public class IOStreamTest {
	
	/** 使用文件输出节点流写文件  */
	@Test
	public void test1() throws IOException {
		//利用文件节点流打开一个文件 ,当文件不存在时候，会自动创建文件,文件存在时候将文件替换为新文件
		FileOutputStream out = new FileOutputStream("test/fos.dat"); //当文件不能写时候，出现异常
		out.write(65);  //测试基本的 byte 数据写出方法, 将byte写到文件中有效范围（0~255）
		out.write(66);
		out.close();//关闭文件
	}
	
	/** 测试 文件输入节点流 的读取文件*/
	@Test
	public void test2() throws IOException {
		//用文件节点输入流打开文件,如果文件不能打开或者文件不存在就抛出异常！
		FileInputStream in = new FileInputStream("test/fos.dat");
		int b1 = in.read(); //测试基本的节点流读取方法 ,每次从文件中读取一个byte(0~255)
		int b2 = in.read();
		System.out.println(b1);
		System.out.println(b2);
		in.close();
	}
	
	/**利用文件流实现文件的复制功能   在不使用缓冲流的情况下，读写性能很差！ */
	@Test
	public void test3() throws IOException {

		//打开原始输入文件
		FileInputStream fis = new FileInputStream("d:/TETRIS.zip");
		BufferedInputStream in = new BufferedInputStream(fis);
				
		//打开目标的输出文件
		FileOutputStream fos = new FileOutputStream("d:/TETRIS_new.zip");
		BufferedOutputStream out=new BufferedOutputStream(fos); 
			
		//从in里读取每个byte写到out流中
		int b;
		while((b=in.read())!=-1){   //b代表原始文件中的每个byte
			out.write(b); 
		}
		
		in.close();  //关闭in，out即可
		out.close();
		System.out.println("成功！");
	}
	
	@Test
	public void test4() throws IOException{
		//打开原始输入文件
		FileInputStream in = new FileInputStream("d:/TETRIS.zip");
		//打开目标的输出文件
		FileOutputStream out = new FileOutputStream("d:/TETRIS_new.zip");
		byte[] buf=new byte[1024*8];//1K byte
		//从输入流in中读取尽可能多的byte填充到缓存 buf 中，返回读取个数 1024
		//int n = in.read(buf);//1024
		//int n = in.read(buf);//1024
		//...
		//n = in.read(buf); // 1 ~ 1024
		//n = in.read(buf); // -1
		int n;
		while((n = in.read(buf))!=-1){  //将buf中从0开始的连续n个byte,写到 文件流out中
			out.write(buf, 0, n);
		}
		in.close();
		out.close();
		System.out.println("OK!");
	}
	
	
	/** 缓存对IO的影响 */
	@Test
	public void test5() throws IOException {
		String file="test/test.txt";
		FileOutputStream fos = new FileOutputStream(file);	
		BufferedOutputStream out=new BufferedOutputStream(fos);
		//缓存流将数据写到缓存中，当缓存满了的时候在成批（8K）的写到文件中 
		out.write(65); 
		out.write(66);
		out.write(66);
		//将缓存清空，数据写到文件中清空缓存，文件不关闭可以继续写文件
		out.flush();
		out.write(67); 
		//关闭方法包含清空缓存的功能
		//out.close();
		//out.write(67); //不能再写文件了
	}
	
	
	/** 对象输出流  */
	@Test
	public void test6() throws IOException {
		Person p1 = new Person("范传奇", 10);
		Person p2 = new Person("李洪鹤", 10);
		p1.friend = p2;
		String file="test/obj.dat";
		//将对象写到文件中
		FileOutputStream fos= new FileOutputStream(file);
		BufferedOutputStream bos= new BufferedOutputStream(fos);
		//对象输出流
		ObjectOutputStream oos= new ObjectOutputStream(bos);
		//将对象写到文件中
		oos.writeObject(p1);
		oos.writeObject(p2);
		//关闭高级流就会自动关闭全部流
		oos.close();		
	}
	
	/** 对象反序列 */
	@Test
	public void test7() throws ClassNotFoundException, IOException {
		String file = "test/obj.dat";
		
		FileInputStream fis= new FileInputStream(file);
		BufferedInputStream bis= new BufferedInputStream(fis);
		ObjectInputStream ois= new ObjectInputStream(bis);
		//读取对象
		//从文件读取一系列byte数据拼接为对象
		Person p1 = (Person)ois.readObject();
		Person p2 = (Person)ois.readObject();
		System.out.println(p1);
		System.out.println(p2);
		ois.close();
	}
	
	/** 字符输出流 */
	@Test
	public void test8() throws IOException {
		String file = "test/osw.txt";
		FileOutputStream fos= new FileOutputStream(file);
		BufferedOutputStream bos= new BufferedOutputStream(fos);
		//OutputStreamWriter是高级流，必须依赖其他的字节流，扩展的字符编码功能
		OutputStreamWriter writer= new OutputStreamWriter( bos, "utf-8");
		//将字符输出到文件
		//将30000（田）编码为UTF-8的byte数据 并且将byte写到文件中。
		writer.write(30000); 
		writer.write("田地");
		char[] chs = 
			{'你','好','J','A','V','A'};
		writer.write(chs);
		writer.write(chs,2,4);
		//关闭高级流就可以关闭文件了
		writer.close();
	}
	
	/** 读取文本信息 */
	@Test
	public void test9() throws IOException {
		String file="test/osw.txt";
		FileInputStream fis= new FileInputStream(file);
		BufferedInputStream bis= new BufferedInputStream(fis);
		//使用 InputStreamReader
		//InputStreamReader 是高级流，必须依赖基本的字节流，扩展了字符的解码功能
		//将字节流中的byte数据读取后进行字符解码 得到解码以后的字符数据
		InputStreamReader reader=new InputStreamReader(bis, "utf-8");//GBK
		//读取字符,返回0~65535之间的字符
//		int c = reader.read();
//		char ch = (char)c;
//		System.out.println(ch);
//		c = reader.read();
//		ch = (char)c;
//		System.out.println(ch);
		int c;
		while((c = reader.read())!=-1){
			char ch = (char)c;
			System.out.println(ch); 
		}
		reader.close();
	}
	
	/** 利用PrintWriter 写文本文件 */
	@Test
	public void test10() throws IOException {
		String file="test/pw.txt";
		FileOutputStream fos= new FileOutputStream(file);
		BufferedOutputStream bos= new BufferedOutputStream(fos);
		OutputStreamWriter osw = new OutputStreamWriter( bos, "utf-8");
		//PrintWriter：是高级流，扩展了println方法和print
		// true 自动清理缓存功能，每个println方法之后会执行一个  flush方法
		PrintWriter out=new PrintWriter(osw, true);
		out.println("Hello World!");
		out.close();
	}
	
	/** 利用BufferedReader读取文本文件  */
	@Test
	public void test11() throws IOException {
		String file = "test/text.txt";
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis= new BufferedInputStream(fis);
		InputStreamReader isr =  new InputStreamReader( bis,"UTF-8");
		BufferedReader in= new BufferedReader(isr); //BufferedReader  是高级流
		
		//readLine 返回文本文件中的一行数据 不包含回车字符，当读取到文件末尾时候 返回null
		String str;
		while((str=in.readLine())!=null){
			System.out.println(str);
		}
		in.close();
	}
	
	/** 将控制台输入的信息保存文本文件中  */
	@Test
	public void test12() throws IOException {
		Scanner in = new Scanner(System.in); 
		String file="abc/mail.txt";
		
		File mail = new File(file);
		if(mail.exists()){
			System.out.print(
				"旧文件存在，还要吗(y/n)？");
			String s = in.nextLine();
			if(s.equals("n")){
				if(mail.delete()){
					System.out.println("已经删除");
				}
			}
		}
		
		//true: 表示追加方式写文件
		FileOutputStream fos= new FileOutputStream(file, true);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		OutputStreamWriter osw =  new OutputStreamWriter( bos, "UTF-8");
		PrintWriter out= new PrintWriter(osw, true);
		//从控制台读取一行，向文件中写一行
		while(true){
			String str = in.nextLine();
			if(str.equals(":exit")){
				break;
			}
			out.println(str); 
		}
		out.close();
	}
	
	
	/** 异常处理 */
	@Test
	public void test13() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String str = "A2017-03-01";//"一九九七年";
			String s = "a55";
			Date d=null;
			int age=0;
			try{
				d = fmt.parse(str);
				age = Integer.parseInt(s);
			}catch(ParseException e){
				System.out.println("替换系统时间");
				d = new Date();
			}catch(NumberFormatException e){
				System.out.println("处理数字解析异常");
				age = 18;
			}
			System.out.println(d);
			System.out.println(age); 
	}
	
	/** 系统不可恢复的错误：堆内存溢出（内存泄漏） */
	@Test
	public void test14() {
		List<Foo> list= new ArrayList<Foo>();
			for(int i=0; i<50000000; i++){
				list.add(new Foo());
				//new Foo();
			}
	}
	class Foo{
		long a;
		long b;
		long c;
		long d;
	}
	
	/** try catch finally 的规则  */
	@Test
	public void test15() {
		System.out.println(test("5A")); 
		System.out.println(test(null)); 
		System.out.println(test("")); 
	}
	public  int test(String s){
		try{
			int n = s.charAt(0)-'0';
			return n;
		}catch(NullPointerException e){
			return -1;
		}catch (RuntimeException e) {
			return -2;
		}finally{//最终的，最终执行代码块
			//无论是否出现异常永远执行
			//用于执行回收资源：关闭文件等
			System.out.println("finally"); 
		}
	}
	
	/** 一个完整的异常处理案例  */
	@Test
	public void test16() {
		String from = "abc";
		String to = "abc/ok.new";
		try{
			//Java编译器会检查：如果方法有异常声明就必须进行处理
			copy(from, to);
			System.out.println("完成");
		}catch(RuntimeException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	//方法中如果抛出了异常，就需要使用throws声明方法有意外情况发生，否则会有编译错误
	public static void copy(String from,
			String to) throws IOException,
		RuntimeException {
		File file = new File(from);
		if(! file.exists()){
			throw new RuntimeException("源文件不存在！"+from);
		}
		if(! file.isFile()){
			throw new RuntimeException("源不是文件！"+from);	
		}
		//...可以增加其他异常处理逻辑
		//必须在try
		FileInputStream in = null;
		FileOutputStream out = null;
		try{
			in=new FileInputStream(from);
			out=new FileOutputStream(to);
			byte[] buf = new byte[1024*8];
			int n;
			while((n=in.read(buf))!=-1){
				out.write(buf, 0, n);
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			//复制期间的读写失败！
			e.printStackTrace();
			//复制期间出现的意外情况，必须
			//再次抛给调用者，告诉调用者复制失败了
			throw e;
		}finally{
			//回收系统资源
			//close()方法本身也会抛出异常，必须进行捕获处理
			try{
				//由于in可能为null，为了避免空指针异常，必须检查是否为空
				if(in!=null) in.close();
				if(out!=null) out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}






/**
 * 实现序列化接口时候Java自动添加两个方法：
 * 一个是对象序列化方法（将对象变成byte）
 * 一个是对象反序列化方法（将byte拼接为对象）
 * 
 * 建议:(必须遵守) 实现序列化接口是添加序列化
 * 序列化 版本号，可以保证对象序列化、反序列化 
 * 的稳定。减少更改类对序列化的影响。
 */
class Person implements Serializable {
	private static final long  serialVersionUID = -44741053724L;
	String name;
	String sex;
	int age;
	//friend 是瞬态属性，系列时候会被忽略反序列化结果是 null
	transient Person friend;
 
	public Person(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + ", friend=" + friend + "]";
	}
 
 
}
