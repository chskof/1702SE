package chenhs.thread.itcast;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk1.5以后将同步和锁封装成了对象.
 * 并将操作锁的隐式方式定义到了该对象中,将隐式动作变成了现实动作.
 * 
 * Lock接口:出现了替代了同步代码块或者同步函数.将同步的隐式锁操作变成现实锁操作.
 * 同时更为灵活,可以一个锁上加上多个监视器.
 * lock():获取锁
 * unlock():释放锁
 * 
 * Condition接口:出现了替代Object中的wait notify notifyAll方法.
 *              将这些监视器方法单独进行了封装,变成Condition监视器对象,可以任意锁进行组合.
 * await();
 * signal();
 * signalAll();
 */

class Resource5{
	private String name;
	private int count = 1;
	private boolean isHave= false;
	
	//创建一个锁对象
	Lock lock = new ReentrantLock();
	//通过已有的锁获取两组的监视器,一组监视生产者,一组监视消费者.
	Condition producer_con = lock.newCondition();
	Condition consumer_con = lock.newCondition();
	public void set(String name) {
		lock.lock();
		try {
			while(isHave) {
				try{producer_con.await();}catch(InterruptedException e) {};
			}
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName()+"...生产..."+this.name);
			isHave = true;
			consumer_con.signal();
		}finally {
			lock.unlock();
		}
	}
	public void out() {
		lock.lock();
		try {
			while(!isHave) {
				try {consumer_con.await();}catch(InterruptedException e) {};
			}
			System.out.println(Thread.currentThread().getName()+"-----消费-----"+this.name);
			isHave = false;
			producer_con.signal();
		}finally {
			lock.unlock();
		}
	}
}

class Producer2 implements Runnable{
	private Resource5 r;
	Producer2(Resource5 r){
		this.r = r;
	}
	public void run() {
		while(true) {
			r.set("烤鸭");
		}
	}
}

class Consumer2 implements Runnable{
	private Resource5 r;
	Consumer2(Resource5 r){
		this.r = r;
	}
	public void run() {
		while(true) {
			r.out();
		}
	}
}


public class ProducerConsumerDemo2 {
	public static void main(String[]  args) {
		//创建资源
		Resource5 r = new Resource5();
		
		//创建任务
		Producer2 pro = new Producer2(r);
		Consumer2 con = new Consumer2(r);
		
		//创建线程
		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		
		//启动线程
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		
	}
}
