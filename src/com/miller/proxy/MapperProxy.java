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
		System.out.println("通过接口与Method获得对应配置文件的信息");
		
		System.out.println("接口名.方法名 == namespace.id");
		System.out.println(mapperInterface.getName()+ "." + method.getName());
		
		System.out.println("通过配置文件信息获得SQL语句类型");
		System.out.println("根据SQL语句类型调用SqlSession对应的增删改查方法");
		System.out.println("当前SQL语句类型是查询");
		
		System.out.println("返回值类型是：" + method.getReturnType().getName());
		System.out.println("根据返回值是List,Map,Object");
		System.out.println("分别调用selectList, selectMap, selectObject");
		//返回查询的结果
		List<Object> list = new ArrayList<>();
		list.add("1");
		list.add("1");
		list.add("1");
		return list;
	}

}
