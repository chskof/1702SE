package chenhs.designMode;

/** 单例模式*/

 
/** 饿汉式*/       
class SingleHungry{
	private static final SingleHungry s = new SingleHungry();
	private SingleHungry() {};
	public static SingleHungry getInstance() {
		return s;               //s是final的 此处只有一句代码 没有线程安全问题
	}
}

/** 懒汉式*/
class SingleLazy{
	private static SingleLazy s  =null;
	private SingleLazy() {};
	public static SingleLazy getInstance() {   //如果在此处加synchronized能解决线程问题但效率低
		if(s==null) {
			s = new SingleLazy();     //多条语句操作共享数据s
		}
		return s;
	}
}



/** 懒汉式(同步安全)*/
class SingleLazySync{
	private static SingleLazySync s = null;
	private SingleLazySync() {};
	public static SingleLazySync getInstance() {
		if(s==null) {
			synchronized(SingleLazySync.class) {
				if(s==null) {
					s = new SingleLazySync();
				}
			}
		}
		return s;
	}
	
}


public class SingleDemo {
	public static void main(String[] args) {
		
	}
}