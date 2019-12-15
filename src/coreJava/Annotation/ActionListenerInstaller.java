package coreJava.Annotation;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionListenerInstaller {
	/**
	 * 为给定的对象处理所有的监听器注解
	 */
	public static void processAnnotations(Object obj) {
		try {
			Class<?> cl = obj.getClass();
			for(Method m : cl.getDeclaredMethods()) {
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if(a != null) {
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj),obj,m);
				}
			}
		}catch(ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 为给定的调用方法增加动作监听器
	 * @param source  需要添加事件监听器的事件源
	 * @param param   监听器调用的方法的隐式参数
	 * @param m		     监听器调用的方法
	 */
	public static void addListener(Object source,final Object param,final Method m) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy,Method mm , Object[] args)throws Throwable{
				return m.invoke(param);
			}
		};
		Object listener = Proxy.newProxyInstance(null, new Class[] {java.awt.event.ActionListener.class}, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		adder.invoke(source,listener);
	}
	
}
	
	

