package com.miller.proxy;

import java.util.List;

import com.miller.bean.Message;
import com.miller.dao.MessageMapper;

public class MyMain {
	public static void main(String[] args) {
		System.out.println("���������ļ�");
		System.out.println("ͨ������������Ϣ����һ��������Map");
		System.out.println("���map��ŵ��ǽӿ�Class��Ӧ�Ĵ�����");
		
		SqlSession session = new SqlSession();
		
		MessageMapper mapper = session.getMapper(MessageMapper.class);
		List<Message> list = mapper.queryMessageListByMybatis(null);
		System.out.println(list.size());
	}
}
