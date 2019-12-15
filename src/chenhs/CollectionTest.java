package chenhs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;


public class CollectionTest {
	
	@Test
	public void test1() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("炸弹", "大王,小王");
		map.put("同花顺", "方块3, 方块4, 方块5, 方块6");
		map.put("拖拉机", "梅花5,红桃5,梅花6,红桃");
		Set<String> keySet=map.keySet(); //获取全部key的集合
		System.out.println(keySet);
		for(String key:keySet){  //遍历集合中全部的Key
			System.out.println(key);
			System.out.println(map.get(key));  //在迭代期间可以利用get方法获取value的值，性能没有问题
		}
		/*利用Entry遍历Map集合
		entrySet() 可以从map集合中获取全部 key:value 条目的集合，每个key:value对的对象类型是 Entry，
		 而Entry类型上定义了getKey和getValue方法，可以获取key:value对中的key和Value */
		Set<Entry<String,String>> entries= map.entrySet();
		for(Entry<String, String> entry:entries){
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+":"+value);
		}
		
		//遍历List
	/*	List<String> list = new ArrayList<String>();
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}*/
		
	}
	
	
	
	@Test
	public void test2() {

		//"海淀:180,顾家庄:100,香山:78,驻马店:120,石家庄:125,海淀:156,香山:298,海淀:356"
		//1. 解析字符串得到每个地点和其PM2.5值
		//2. 创建散列表 map
		//3. 遍历输入数据
		//4. 如果有地点，取出比较在存入
		//5. 如果没有地点，直接存入
		
		String pm25="海淀:180,顾家庄:100,香山:78,驻马店:120,石家庄:125,海淀:156,香山:298,海淀:356";
		String[] data = pm25.split("[,:]");
		System.out.println(Arrays.toString(data));
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for(int i=0; i<data.length; i+=2){
			String loc=data[i];
			String str=data[i+1];
			//System.out.println(loc);
			int val = Integer.parseInt(str);
			if(map.containsKey(loc)){
				int v = map.get(loc);
				if(val>v){
					map.put(loc, val);
				}
 			}else{
 				map.put(loc, val);
 			}
		}
		
		//查询
		int val = map.get("海淀");
		System.out.println(val);  //输出全部结果？
		
	}
	
	
	@Test
	public void test3() {
		Product p1 = new Product("新西兰空气", 5.9);
		Product p2 = new Product("加勒比海海水", 6);
		Product p3 = new Product("喜马拉雅山雪", 6);
		ByWeight byWeight = new ByWeight();
		int n1 = byWeight.compare(p1, p2);
		int n2 = byWeight.compare(p2, p1);
		int n3 = byWeight.compare(p2, p3);
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
	
		List<Product> list = new ArrayList<Product>();
		list.add(p3);
		list.add(p2);
		list.add(p1);
		list.add(new Product("阿拉斯加的土", 2)); 
		//Java中的自定义排序 将集合中的元素按照给定的自定义比较器的比较结果进行排序。
		Collections.sort(list, byWeight);
		System.out.println(list);
	}
	class ByWeight implements Comparator<Product>{
		public int compare(Product o1, Product o2) {
			double val = o1.weight-o2.weight;
			// 按照商品重量进行自定义比较 从大到小排序
			if(val>0){return -1;}
			if(val<0){return 1;}
			return 0;
		}
	}
	class Product{ //产品
		String name;
		double weight;//重量
		public Product(String name, double weight) {
			this.name = name;
			this.weight = weight;
		}
		public String toString() {
			return "Product [name=" + name + ", weight=" + weight + "]";
		}
	}
	
	/** Deque双端队列 */
	@Test
	public void test4() {
		Deque<String> stack = new LinkedList<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack);
		while(! stack.isEmpty()){
		String s = stack.pop();
		System.out.println(s);
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test5() {
		Collection col=new ArrayList();
		col.add(new Person("Tom", 5));
		col.add(new Person("Jerry", 4));
		col.add(new Person("Andy", 5));
		System.out.println(col);
		Person p=new Person("Tom", 5);
		System.out.println(p);
		
		//集合元素类型Person上没有很好的 重写equals造成contains方法的失效！
	
		boolean b=col.contains(p);
		System.out.println(b); 
		
		Collection c = new ArrayList();
		c.add(new User("Tom", 1));
		c.add(new User("Jerry", 2));
		c.add(new User("Andy", 3));
		System.out.println(c);
		User user = new User("Tom", 1);
		// 集合中的元素类型User很好重写了 equals，保证了contains方法的正确 执行： /
		b = c.contains(user);
		System.out.println(b);
		
		System.out.println(c.size()); //3
		System.out.println(c.isEmpty());//flase
		c.clear();//清空集合中的引用
		System.out.println(c.size()); //0
		System.out.println(c.isEmpty());//true
	}
	class User{
		int id;
		String name;
		public User(String name, int id) {
			this.id=id;
			this.name = name;
		}
		public boolean equals(Object obj){
			if(obj==null) return false;
			if(this==obj) return true;
			if(obj instanceof User){
				User other = (User)obj;
				return id == other.id;
			}
			return false;
		}
		public String toString() {
			return "("+name+","+id+")";
		}
	}
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
	
	
	@Test
	public void test6() {
		//key对象类型如果不重写equals hashCode将对象map集合产生严重影响，照成map工作异常
		Map<Key,String> map = new HashMap<Key, String>();
		//没有重新hashCode和equals会出现重复添加
		map.put(new Key(1, "老师"),  "范传奇，王克晶，李洪鹤");
		map.put(new Key(1, "老师"),  "范老师，王老师，李老师");
		System.out.println(map); 
		//没有重新hashCode和equals会出现查询失败
		String val =  map.get(new Key(1, "老师"));
		System.out.println(val); 
	}
	class Key{
		int id;
		String name;
		public Key(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (id != other.id)
				return false;
			return true;
		}
		public String toString() {
			return "Key [id=" + id + ", name=" + name + "]";
		}
	}
	
	
	@Test
	public void test7() {
		//测试 List 的 get 方法和 set方法 
		List<String> list = new LinkedList<String>();
		list.add("Tom");
		list.add("Jerry");
		list.add("Andy");
		System.out.println(list);
		//将集合中序号为0的元素引用复制到str
		String str = list.get(0);
		System.out.println(str);
		str= list.get(1);
		System.out.println(str);
		//置换集合中的元素
		String name = list.set(2, "Wang");
		System.out.println(name);
		System.out.println(list);
	}
	
	@Test
	public void test8() {
		List<String> list = new ArrayList<String>();
		for(int i =0; i<10;i++){
			list.add("黑桃"+i);
		}
		System.out.println(list);
		//从list的3位置开始抽取3个元素为sub List 
		List<String> sub = list.subList(3, 3+3);
		System.out.println(sub);
		//由于共享存储空间，修改subList影响原list集合
		sub.remove(0);
		sub.add(1, "0000");
		sub.clear();
		System.out.println(sub);
		System.out.println(list);
	}
	
	
	/**集合转换为数组*/
	@Test
	public void test9() {
		Collection<Integer> col= new ArrayList<Integer>();
		col.add(1);
		col.add(2);
		col.add(3);
		col.add(4);
		
		Integer[] ary = new Integer[10];
		for(int i=0; i<ary.length; i++){
			ary[i]=0;
		}
		//将集合col中的引用复制到ary中
		Integer[] a=col.toArray(ary);
		
		
		for(Integer n:ary){
			System.out.println(n);
		}
		for(Integer n:a){
			System.out.println(n);
		}
		
		Integer[] arr = col.toArray(new Integer[0]);
		System.out.println(Arrays.toString(arr));
	}
	
	
	@Test
	public void test10() {
		Integer[] ary = {2,3,4,8};
		List<Integer> list = Arrays.asList(ary); //asList返回长度固定的List，与数组共享存储空间
		System.out.println(list); 
		ary[0]=9;
		//不能改变此List的长度
		System.out.println(list);
	}
	
	/** Java提供了Queue API，由LinkedList实现  队列先进先出      */
	@Test
	public void test11() {
		Queue<String> queue = new LinkedList<String>();
		System.out.println(queue);
		//offer将数据追加到队列中
		queue.offer("Tom");
		queue.offer("Jerry");
		queue.offer("Andy");
		System.out.println(queue); 
		//peek检查队首，元素并不出列
		String first = queue.peek();
		System.out.println(first);//Tom
		System.out.println(queue);//不变
		//poll 是拉出一个元素，元素出列
		String element=queue.poll();
		System.out.println(element);//Tom
		System.out.println(queue);
		//拉取队列中全部的数据
		while(! queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}
	
	
	@Test
	public void test12() {
		// Deque 接口中定义了 栈操作方法LinkedList 实现了栈操作方法
		Deque<String> stack = new LinkedList<String>();
		//栈提供了 push方法可以将数据“压”入栈中。
		stack.push("Tom");
		stack.push("Jerry");
		stack.push("Andy");
		//先压入的数据在栈的最底部
		System.out.println(stack);
		//出栈的顺序相反：后进先出
		while(! stack.isEmpty()){
			//利用pop可以从栈中弹出数据 弹出顺序与进入顺序相反
			String s = stack.pop();
			System.out.println(s); 
		}
	}
}
