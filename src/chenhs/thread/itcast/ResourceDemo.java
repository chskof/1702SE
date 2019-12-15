package chenhs.thread.itcast;

/**
   线程间通讯:多个线程在处理同一资源,但是人物却不同.
 * */

public class ResourceDemo {
	public static void main(String[] args) {
		//创建资源
		Resource r = new Resource();
		//创建任务
		Input in  = new Input(r);
		Output out = new Output(r);
		//创建线程,执行路经过
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//开启线程
		t1.start();
		t2.start();
		
	}
}

//资源
class Resource{
	String name;
	String sex;
}

//输入
class Input implements Runnable{
	Resource r;
	Input(Resource r){
		this.r = r;
	}
	public void run() {
		int x = 0;
		while(true) {
			synchronized(r) {
				if(x==0) {
					r.name= "Jack";
					r.sex = "man";
				}
				else {
					r.name="露丝";
					r.sex="女女女女女女女女";
				}
			}
			x = (x+1)%2;
		}
	}
}


//输出
class Output implements Runnable{
	Resource r;
	Output(Resource r){
		this.r = r;
	}
	public void run() {
		while(true) {
			synchronized(r){
				System.out.println(r.name+"-----"+r.sex);
			}
		}
	}
}
