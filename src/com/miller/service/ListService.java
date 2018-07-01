package com.miller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miller.bean.Message;
import com.miller.dao.MessageDao;
import com.miller.entity.Page;

/**
 * �б���ص�ҵ����
 * @author Miller
 *
 */
public class ListService {
	
	private MessageDao dao = new MessageDao();
	
	public List<Message> queryMessageList( String command,String description ,Page page){
		//��֯��Ϣ����
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		//��ѯ����
		int totalNumber = dao.count(message);
		page.setTotalNumber(totalNumber);
		
		//ƴװ��ѯ����
		Map<String,Object> params = new HashMap<>();
		params.put("message", message);
		params.put("page", page);
		return dao.queryMessageListByMybatis(params);
	}
	
	public List<Message> queryMessageListByPage(String command,String description ,Page page){
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		
		Map<String,Object> params = new HashMap<>();
		params.put("message", message);
		params.put("page", page);
		
		return dao.queryMessageListByPage(params);
	}
}
