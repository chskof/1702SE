package chenhs.designMode.dynaProxy;

public class Test {
	public static void main(String[] args) {
		//创建一个原始的GunDog对象,作为target
		Dog target = new GunDog();
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.info();
		dog.run();
	}
}
