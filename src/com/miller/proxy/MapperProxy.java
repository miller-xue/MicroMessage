package com.miller.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MapperProxy<T> implements InvocationHandler {

	private final Class<T> mapperInterface;
	
	public MapperProxy(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("ͨ���ӿ���Method��ö�Ӧ�����ļ�����Ϣ");
		
		System.out.println("�ӿ���.������ == namespace.id");
		System.out.println(mapperInterface.getName()+ "." + method.getName());
		
		System.out.println("ͨ�������ļ���Ϣ���SQL�������");
		System.out.println("����SQL������͵���SqlSession��Ӧ����ɾ�Ĳ鷽��");
		System.out.println("��ǰSQL��������ǲ�ѯ");
		
		System.out.println("����ֵ�����ǣ�" + method.getReturnType().getName());
		System.out.println("���ݷ���ֵ��List,Map,Object");
		System.out.println("�ֱ����selectList, selectMap, selectObject");
		//���ز�ѯ�Ľ��
		List<Object> list = new ArrayList<>();
		list.add("1");
		list.add("1");
		list.add("1");
		return list;
	}

}
