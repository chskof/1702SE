package chenhs.designMode.dynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
	//为指定target生成个动态代理对象
	public static Object getProxy(Object target) {
		//创建一个MyInvokationHandler对象
		MyInvokationHandler handler = new MyInvokationHandler();
		//为MyinvokationHandler设置target对象
		handler.setTarget(target);
		//创建 并返回一个动态代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}
