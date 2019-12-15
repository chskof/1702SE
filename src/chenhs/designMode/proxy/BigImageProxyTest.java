package chenhs.designMode.proxy;

class BigImage implements Image{
	public BigImage() {
		try {
			//程序暂停3s模式模拟系统开销
			Thread.sleep(3000);
			System.out.println("图片装载成功");
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		System.out.println("绘制图片");
	}
	
}

class ImageProxy implements Image {
	private Image image; 
	public ImageProxy(Image image) {
		this.image = image;
	}
	
	//重写Image接口的show()方法,该方法用于控制对呗代理对象的访问,并根据需要负责创建和删除被代理对象
	public void show() {
		//只有当真正需要调用image的show()方法时才创建被代理对象
		if(image == null) {
			image = new BigImage();
		}
		image.show();
	}
}


public class BigImageProxyTest{
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Image image = new ImageProxy(null);
		System.out.println("系统得到Iamge对象的开销:"+(System.currentTimeMillis() - start));
		image.show();
	}
}
