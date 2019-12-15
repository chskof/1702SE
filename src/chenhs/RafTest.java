package chenhs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;



public class RafTest {

	/** RAF 写出文件 */
	@Test
	public void test1() throws IOException {
		String file = "test/demoRaf.txt";
		// 创建RAF对象，以读写方式创建对象时候
		// 如果文件不存在，则在磁盘上自动创建文件，文件默认的指针位置在0
		// 如果文件与文件夹同名或者不能写文件则抛出异常
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		long p = raf.getFilePointer();// 检查文件的读写指针位置
		System.out.println(p);// 0

		// 将数据写到文件中
		raf.write(65); // 有效范围：0~255
		p = raf.getFilePointer();
		System.out.println(p);

		raf.write(66);
		p = raf.getFilePointer();
		System.out.println(p);

		long l = raf.length();// 检查文件的长度
		System.out.println(l);

		raf.close();// raf必须关闭
	}

	/** RAF 读取文件 */
	@Test
	public void test2() throws IOException {

		String file = "test/demoRaf.txt";
		RandomAccessFile raf = new RandomAccessFile(file, "r"); // 以只读访问打开文件
		long p = raf.getFilePointer(); // 刚刚打开的文件读写指针位置是0
		System.out.println(p);// 0

		// 读取0位置上的数据(65)
		int b = raf.read(); // 将byte填充到int
		// 占用int： 0~255 范围
		System.out.println(b);// 65

		// 读取以后，文件指针位置自动移动一下
		// 检查文件指针位置
		p = raf.getFilePointer();
		System.out.println(p);// 1
		// 读取下一个byte数据: 66
		b = raf.read();
		System.out.println(b);// 66

		// 文件指针位置：
		p = raf.getFilePointer();
		System.out.println(p);// 2
		// 在文件末尾时候调用raf.read()
		// 返回一个特殊值：-1 表示读取到文件末尾了
		b = raf.read();
		System.out.println(b);// -1

		p = raf.getFilePointer();
		System.out.println(p);// 2

		raf.close();
	}

	/**
	 * 随机读写文件
	 * 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		String file = "test/myfile.dat";

		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		// 将0~255写入到文件 myfile.dat
		for (int i = 0; i <= 255; i++) {
			raf.write(i);
		}

		// 移动读写位置指针到0
		raf.seek(0);// 可以在任意位置开始读写

		int b;
		// 经典的模式写法：
		while ((b = raf.read()) != -1) {
			System.out.println(b);
		}

		raf.close();
	}

	/** 整数数据读取 */
	@Test
	public void test4() throws IOException {
		String file = "test/integer.dat";
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		// 将int数据126712 拆分为4个byte写到文件中，文件指针连续移动4次
		raf.writeInt(126712);
		long p = raf.getFilePointer();
		System.out.println(p);
		
		
		raf.seek(0);
		// 读取一个整数: 连续读取4个byte，拼接为一个int数据, 文件指针连续移动4次
		int n = raf.readInt();
		p = raf.getFilePointer();
		System.out.println(p);// 4
		System.out.println(n);// 126712
		raf.close();
	}

	/** 字符串类型的写出 */
	@Test
	public void test5() throws IOException {
		String file = "test/str.txt";
		RandomAccessFile raf = new RandomAccessFile(file, "rw");

		String str = "你好ABC";

		// 对字符串进行UTF-8编码
		byte[] bytes = str.getBytes("UTF-8");
		System.out.println(bytes.length);// 9

		// 将文字的编码写入到文件中
		raf.write(bytes);// 将数组中全部的byte数据写入到文件中。

		long p = raf.getFilePointer();
		System.out.println(p);// 9

		// 从头读取
		raf.seek(0);
		byte[] buf = new byte[(int) raf.length()]; // 新建一个空数组

		// read(bytes) 从文件中连续读取byte数据将读取结果填充到 byte数组中
		raf.read(buf);
		// buf 中是按照UTF-8编码的字符数据,解码：
		String s = new String(buf, "UTF-8");
		System.out.println(s);

		raf.close();
	}

	/** 写员工信息 */
	@Test
	public void test6() throws IOException {
		String file = "test/Emp.dat";
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		writeEmp(raf, 0, "Jack", 10, "男", 100, new Date());
		writeEmp(raf, 1, "杰克逊", 30, "男", 200, new Date());
		raf.close();
	}
	public static void writeEmp(RandomAccessFile raf, int n,String name, int age, String sex, int salary, Date hiredate) throws IOException {
		int start=n*80;//n=0,0  n=1,80  n=2,160
		raf.seek(start);//将文件指针移动到每行起始位置
		
		//将name编码，然后写到文件中
		byte[] bytes=name.getBytes("UTF-8");
		raf.write(bytes); //3 9 10
		
		//写出age
		raf.seek(start+32);//跳到age位置
		raf.writeInt(age);//写出年龄
		
		//写出性别
		bytes = sex.getBytes("UTF-8");
		raf.write(bytes);
		
		//写出薪水
		raf.seek(start+46);
		raf.writeInt(salary); 
		
		//写出日期
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String d = fmt.format(hiredate);
		bytes = d.getBytes("UTF-8");
		raf.write(bytes);
	}
   
	
	/** 读取员工信息  */
	@Test
	public void test7() throws Exception {
		String file="test/Emp.dat";  //打开文件 emp.dat
		RandomAccessFile raf=new RandomAccessFile(file, "r");
	
		
		Emp e1 = readEmp(raf, 0);//读取第一行 （Tom）
		Emp e2 = readEmp(raf, 1);//读取第二行 （范传奇）
		
		//显示读取结果
		System.out.println(e1);
		System.out.println(e2);
		
		raf.close();  //关闭文件
	}
	public static Emp readEmp(RandomAccessFile raf, int n) throws Exception {	
			int start = n*80;//读取的起始位置
			raf.seek(start);//找到name的起始位置
			
			byte[] bytes = new byte[32];
			raf.read(bytes);//读取32个byte  	//bytes=[T,o,m,0,0,0,0,0,...0]
			String name = new String(bytes,"UTF-8").trim();
			
			//读取年龄
			int age = raf.readInt();
			
			//读取性别...
			bytes = new byte[10];
			raf.read(bytes);
			String sex=new String(bytes,"UTF-8").trim();
			
			//读取薪水
			int salary = raf.readInt();
			
			//读取日期
			bytes = new byte[30];
			raf.read(bytes);
			String str = new String(bytes, "UTF-8").trim();
			
			//解析日期
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date hiredate=fmt.parse(str);
			
			return new Emp(name,age,sex,salary,hiredate);
		}
	
	
	
	
	
	
}



/** 员工信心类 */
class Emp{
	String name;
	int age;
	String sex;
	int salary;
	Date hiredate;
	public Emp(String name, int age, String sex, int salary, Date hiredate) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
		this.hiredate = hiredate;
	}
	public String toString() {
		return "Emp [name=" + name + ", age=" + age + ", sex=" + sex + ", salary=" + salary + ", hiredate=" + hiredate
				+ "]";
	}
}