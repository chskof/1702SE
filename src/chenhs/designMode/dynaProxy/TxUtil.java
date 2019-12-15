package chenhs.designMode.dynaProxy;

public class TxUtil {

	//第一个拦截器方法:模拟事物开始
	public void beginTx() {
		System.out.println("=====模拟事物开始======");
	}
	public void endTx() {
		System.out.println("-----模拟事物结束------\n");
	}
}
