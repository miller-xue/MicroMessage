package com.miller.proxy;

import java.util.List;

import com.miller.bean.Message;
import com.miller.dao.MessageMapper;

public class MyMain {
	public static void main(String[] args) {
		System.out.println("加载配置文件");
		System.out.println("通过加载配置信息加载一个代理工厂Map");
		System.out.println("这个map存放的是接口Class对应的代理工厂");
		
		SqlSession session = new SqlSession();
		
		MessageMapper mapper = session.getMapper(MessageMapper.class);
		List<Message> list = mapper.queryMessageListByMybatis(null);
		System.out.println(list.size());
	}
}
