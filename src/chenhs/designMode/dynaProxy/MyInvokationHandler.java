package chenhs.designMode.dynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokationHandler implements InvocationHandler {
	//需要被代理的对象
	private Object target;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	//执行动态代理对象的所有方法时,都会被替换成执行如下的invoke方法
	public Object invoke(Object proxy,Method method,Object[] args) throws Exception{
		TxUtil tx = new TxUtil();
		tx.beginTx();
		 
		Object result = method.invoke(target , args);
		
		tx.endTx();
		
		return result;
	}
}
