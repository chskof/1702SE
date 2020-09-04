package chenhs.thread.itcast;

public class ResourceDemo3 {
	public static void main(String[] args) {
		// 创建资源
		Resource3 r = new Resource3();
		// 创建任务
		Input3 in = new Input3(r);
		Output3 out = new Output3(r);
		// 创建线程 执行路径
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		// 开启线程
		t1.start();
		t2.start();
	}
}

class Resource3 {
	private String name;
	private String sex;
	private boolean flag = false;

	public synchronized void set(String name, String sex) {
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		this.name = name;
		this.sex = sex;

		flag = true;
		this.notify();
	}

	public synchronized void out() {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		System.out.println(name + "------" + sex);

		flag = false;
		notify();
	}
}

// 输入
class Input3 implements Runnable {
	Resource3 r;

	Input3(Resource3 r) {
		this.r = r;
	}

	public void run() {
		int x = 0;
		while (true) {
			if (x == 0) {
				r.set("Jack", "man");
			} else {
				r.set("露丝", "女女女女女女");
			}
			x = (x + 1) % 2;
		}
	}
}

// 输出
class Output3 implements Runnable {
	Resource3 r;

	Output3(Resource3 r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			r.out();
		}
	}
}