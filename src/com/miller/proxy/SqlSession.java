package com.miller.proxy;

import java.lang.reflect.Proxy;

public class SqlSession {
	
	/**
	 * 接收一个接口,返回一个接口的代理实现类
	 * @param mapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  <T> T getMapper(Class<T> type) {
		System.out.println("通过接口Class从代理工厂Map取出对应的代理工厂");
		System.out.println("通过代理工厂实例化一个代理类");
		System.out.println("用这个代理类生成一个代理实例并返回");
		return (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy(type));
	}
}
