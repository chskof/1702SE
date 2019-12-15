package chenhs.thread.itcast;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ArrayResource {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	
	final Object[] items = new Object[100];
	int putptr, takeptr, count;
	
	public void put(Object x) throws InterruptedException{
		lock.lock();
		try {
			while(count == items.length) {
				notFull.await();
			}
			
			items[putptr] = x;
			System.out.print(Thread.currentThread().getName()+" ...制作... "+"["+putptr+"] ");
			if(++putptr == items.length) putptr = 0;
			++count;
			System.out.println(" 现有总数:"+ count);
			notEmpty.signal();
			
		}finally {
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException{
		lock.lock();
		try {
			while(count == 0) {
				notEmpty.await();
			}
			
			Object x = items[takeptr];
			System.out.print(Thread.currentThread().getName()+" ------售出------ "+"["+takeptr+"] ");
			if(++takeptr == items.length) takeptr = 0;
			--count;
			System.out.println(" 现有总数:"+ count);
			notFull.signal();
			return x;
		}finally {
			lock.unlock();
		}
	}
}

class Puter implements Runnable{
	ArrayResource r;
	Puter(ArrayResource r){
		this.r = r;
	}
	public void run() {
		try {
			while(true) {
				r.put(new Object());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Taker implements Runnable{
	ArrayResource r;
	Taker(ArrayResource r){
		this.r = r;
	}
	public void run() {
		try {
			while(true) {
				r.take();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ArrayThreadTest{
	
	public static void main(String[] args) {
		//创建资源
		ArrayResource r = new ArrayResource();
		
		//创建任务
		Puter put = new Puter(r);
		Taker tak = new Taker(r);
		
		//创建线程
		Thread t0 = new Thread(put);
		Thread t1 = new Thread(put);
		
		Thread t2 = new Thread(tak);
		Thread t3 = new Thread(tak);
		
		//启动线程
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
}
