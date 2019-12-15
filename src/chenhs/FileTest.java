package chenhs;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

public class FileTest {
	
	
	@Test
	public void demo1() {
		//创建file 对象，代表硬盘上的文件
		File file=new File("D:\\Program Files\\Oxygen\\workspace\\1702SE\\resources\\demo.txt");
		System.out.println(file.getPath());
		
//		boolean success = file.delete(); //delete：删除file所代表的硬盘文件
//		System.out.println(success);  //当删除成功时候返回 true，删除失败时候返回false
		
		boolean b = file.exists();  //检查file对象在硬盘上对应的文件是否存在
		System.out.println(b);
	}
	
	@Test
	public void demo2() {
		File file=new File("resources/demo.txt");
		boolean b = file.exists();   //exists: 检查file对象代表的硬盘文件是否存在，如果存在则true，否则false
		System.out.println(b);//true
//		file.delete();
//		b = file.exists();
//		System.out.println(b);//false
		String path = file.getPath();
		System.out.println(path);
	}
	
	/**
	  文件分隔符问题：
	  1. Windows 的文件分隔符 ：\
	  	  - D:\demo\test.txt
	     - demo\test.txt
	     - File.separator = \
	  2. Linux 的文件分隔符 ：/
	 	 - /home/soft01/demo/test.txt
	     - demo/test.txt
	     - File.separator = /
	  3. File 类提供了自动适应操作系统的文件分隔符变量File.separator或随着操作系统自动变化，
	            可以利用这变量，编写跨系统的程序："demo"+File.separator+"test.txt"
	  4. 当使用 “/” 时候 Java 会自动使用所有操作系统，这样更加方便！
	 */
	@Test
	public void demo3() {
		String path= "resources"+File.separator+"test.txt";
		File file = new File(path);
		boolean b = file.exists();
		System.out.println(b);
	}
	
	@Test
	public void demo4() {
		File dir=new File("resources");  //dir 就代表硬盘上的一个文件夹
		boolean b = dir.isDirectory(); //File API 提供了检查是否是文件夹的方法,isDirectory 返回true是文件夹
		System.out.println(b); //true
		dir.delete();   //删除文件夹！只能删除空文件夹！
		b = dir.isDirectory();
		System.out.println(b); 
	}
	
	/**  不能删除非空文件夹首先准备实验素材：test/demo.txt  */
	@Test
	public void demo5() {
		File dir=new File("test");  //创建file对象代表文件夹
		boolean b=dir.isDirectory();
		System.out.println(b);
		boolean success=dir.delete();
		System.out.println(success); //test 文件夹中包含文件，这时不能直接删除。delete()返回false
	}
	
	@Test
	public void demo6() {
		File dir=new File("resources");   //创建file对象代表文件夹
		File file=new File(dir, "demo.txt");  //创建file对象代表 文件夹中的文件
		boolean b = file.exists();//如上file代表 test文件夹中的demo.txt文件
		System.out.println(b); 
	}
	
	/** 读取一个文件夹、文件的全部属性 素材：test/demo.txt    */
	@Test
	public void demo7() { 
		File dir=new File("resources");     //dir代表一个文件夹
		File file=new File(dir, "demo.txt");  //file 代表一个文件
		System.out.println(dir.isFile());//检查是否是文件
		System.out.println(file.isFile());
		
		long length = file.length();//检查文件的长度
		System.out.println(length); 
		
		long time = file.lastModified();//检查文件的最后修改时间
		Date date = new Date(time);
		System.out.println(date);
		
		boolean read=file.canRead();//检查文件的读写属性
		boolean write=file.canWrite();
		boolean hidden = file.isHidden();
		
		System.out.println(read);
		System.out.println(write);
		System.out.println(hidden);
	}
	
	/** 创建文件夹 */
	@Test
	public void demo8() {
		File dir = new File("test");
		boolean b = dir.mkdir(); //mkdir()用于创建文件夹，如果成功 创建文件夹，则返回true，否则false
		System.out.println(b);
		
		File dir2=new File("test/demo/abc");  //创建系列文件夹
		b = dir2.mkdirs();//mkdirs 用于创建一系列父子文件夹
		System.out.println(b); 
		
		//使用绝对路径创建文件夹
		File file3=new File("D:/test/file");
		//new File("/home/soft01/demo/test");
		file3.mkdirs();
	}
	
	/** 创建文件 
	 * @throws IOException */
	@Test
	public void demo9() throws IOException {
		File dir = new File("test");
		dir.mkdir();
		
		File file = new File("test/abc.txt");
		/*调用file对象的API方法
		create:创建
		createNewFile: 创建新文件
		如果创建成功返回true，创建失败返回false，如果没有写入权限，则出现异常！！      */
		boolean b = file.createNewFile();
		System.out.println(b);
	}
	
	/** 文件的改名操作 */
	@Test
	public void demo10() {
		File file1=new File("test/abc.txt");
		File file2=new File("test/def.txt");
		//file1 是存在的文件，file2 是一个不存在的文件  将file1的名字改名为file2对应的名字
		boolean b = file1.renameTo(file2);
		//改名之后：file1就不存在了（abc.txt）         file2存在(def.txt)
		System.out.println(b);//true
	}
	
	/** 显示文件夹的内容 */
	@Test
	public void demo11() {
		// new File("D:/");
		// new File("/home/soft01/");
		File dir=new File("test");  //创建file对象代表test文件夹
		
		//查询 test文件夹的内容列表      返回值是File类型的数组，有的是文件有的是文件夹
		File[] files=dir.listFiles();
		
		for(File file:files){     //在控制台输出文件夹中全部的内容
			//文件夹显示为： [文件夹名]
			if(file.isDirectory()){
				System.out.println(
						"["+file.getName()+"]"); 
			}else{
				System.out.println(
						file.getName());
			}
		}
	}
	
	
	/**  删除一个包含文件的文件夹   */
	@Test
	public void demo12() {
		File file = new File("test");
		boolean b=delete(file);
		System.out.println(b); 
	}
	// 删除一个包含文件的文件夹 
	public static boolean delete(File dir){
		//删除一个文件夹的步骤：
		//1. 列出文件夹的全部内容
		//2. 遍历每个文件，并且调用文件的detete()
		//3. 删除文件夹
		//4. 返回删除的结果
		if(! dir.exists()){
			return false;
		}
		File[] files=dir.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				//删除子文件夹
				//删除子文件的算法与删除当前文件夹的算法是一致的。利用递归删除子文件夹
				delete(file);
			}else{
				file.delete(); //删除文件
			}
		}
		return dir.delete();
	}
	
	/** 统计一个文件夹中全部文件的总长度  */
	@Test
	public void demo13() {
		File dir = new File("test");
		long n = count(dir);
		System.out.println(n); 
	}
	//统计dir对应的文件夹中文件的总大小
	public static long count(File dir){
		//1. 查找dir的全部内容
		//2. 遍历每个文件，累加文件的大小
		//3. 返回统计结果
		if(! dir.exists()){	return 0;}
		if(dir.isFile()){return dir.length();}
		File[] files=dir.listFiles();
		long sum = 0;
		for(File file:files){
			if(file.isDirectory()){
				//统计子文件夹的总大小:统计子文件夹时候和统计当前文件夹的算法是一样的！
				long l = count(file);
				sum += l;
			}else{
				//统计一个文件
				sum+=file.length();	
			}
		}
		return sum;
	}
	
	
	/**
	 * 设置文件的过滤条件
	 * File： 文件
	 * Filter: 过滤器
	 */
	@Test
	public void demo14() {
		//FileFilter 是一个接口
		//new FileFilter(){} 创建匿名内部类
		FileFilter filter = new FileFilter(){
			//accept 方法是在FileFilter中定义的抽象方法。
			//accept: 接受，测试文件是否被接受
			public boolean accept(File file){
				//接受一个文件的名是以.txt为结尾的。
				String name=file.getName();
				return name.endsWith(".txt"); 
			}
		};
		
		File file1 = new File("test/demo1.txt");
		File file2 = new File("test/test.dat");
		
		System.out.println(filter.accept(file1));   //检查 file1 使用以 .txt 为结尾
		System.out.println(filter.accept(file2));  //检查 file2 使用以 .txt 为结尾
		
		
		//listFiles 重载方法，与filter配合可以过滤文件夹内容列表，列出满足条件的文件
		File dir=new File("test");
		File[] files=dir.listFiles(filter);  //满足过滤条件的全部文件（或文件夹）
		for(File file:files){
			System.out.println(file.getName());
		}
		//有过滤条件的列目录方法
		//listFiles(过滤条件);
	}
	
 	
	/**获取当前类所在的工程路径*/
	@Test
	public void test1() {
		File file = new File(this.getClass().getResource("/").getPath());
		System.out.println(file); //D:\Program%20Files\Oxygen\workspace\1702SE\bin
	}
	
	/**获取当前类的绝对路径*/
	@Test
	public void test2() {
		File file = new File(this.getClass().getResource("").getPath());
		System.out.println(file); //D:\Program%20Files\Oxygen\workspace\1702SE\bin\File
	}

	/**获取当前类所在的工程路径,两种方法皆可*/
	@Test
	public void test3() throws IOException {
		File file = new File("");
		String path  = file.getCanonicalPath();
		System.out.println(path);  //D:\Program Files\Oxygen\workspace\1702SE
		
		System.out.println(System.getProperty("user.dir")); //D:\Program Files\Oxygen\workspace\1702SE
	}
	
	/**获取当前src下面的文件*/
	@Test
	public void test4() {
		URL url = this.getClass().getClassLoader().getResource("test.dat");
		System.out.println(url);  // file:/D:/Program%20Files/Oxygen/workspace/1702SE/bin/test.dat
	}
	
	@Test
	public void test6() throws IOException {
		Properties properties = new Properties();
		//使用这种方式可以获取文件对应的输出流
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(inputStream);
		System.out.println(properties.getProperty("driver"));
	}
}
