package chenhs.thread.itcast;
/*
 * 生产者,消费者
 * 多生产者,多消费者的问题
 * if判断标记,只有一次,会导致不该运行的线程运行了.出现了数据错误的情况.
 * while判断标记,解决了线程获取执行权后,是否要运行!
 * 
 * notify:只能唤醒一个线程,如果本方唤醒了对方,没有意义。而且while判断标记+notify会导致死锁.
 * notifyAll:解决了本方线程一定会唤醒对方线程的问题.
 */

class Resource4{
	private String name;
	private int count;
	private boolean isHave = false;
	
	public synchronized void set(String name) {
		while(isHave) {
			try{this.wait();}catch(InterruptedException e) {};
		}
		
		this.name = name + count;
		count++;
		System.out.println(Thread.currentThread().getName()+"...生产..."+this.name);
		isHave = true;
		notifyAll();
	}
	
	public synchronized void out() {
		while(!isHave) {
			try {this.wait();}catch(InterruptedException e) {};
		}
		
		System.out.println(Thread.currentThread().getName()+"-----消费------"+this.name);
		isHave = false;
		notifyAll();
	}
}

class Producer implements Runnable{
	private Resource4 r;
	Producer(Resource4 r){
		this.r = r;
	}
	public void run() {
		while(true) {
			r.set("烤鸭");
		}
	}
}


class Consumer implements Runnable{
	private Resource4 r;
	Consumer(Resource4 r){
		this.r = r;
	}
	public void run() {
		while(true) {
			r.out();
		}
	}
}


public class ProducerConsumerDemo {
	
	public static void main(String[] args) {
		Resource4 r = new Resource4();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		
		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
	
}
