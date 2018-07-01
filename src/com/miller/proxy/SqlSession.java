package com.miller.proxy;

import java.lang.reflect.Proxy;

public class SqlSession {
	
	/**
	 * ����һ���ӿ�,����һ���ӿڵĴ���ʵ����
	 * @param mapper
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  <T> T getMapper(Class<T> type) {
		System.out.println("ͨ���ӿ�Class�Ӵ�����Mapȡ����Ӧ�Ĵ�����");
		System.out.println("ͨ��������ʵ����һ��������");
		System.out.println("���������������һ������ʵ��������");
		return (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy(type));
	}
}
