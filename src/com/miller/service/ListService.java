package com.miller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miller.bean.Message;
import com.miller.dao.MessageDao;
import com.miller.entity.Page;

/**
 * 列表相关的业务功能
 * @author Miller
 *
 */
public class ListService {
	
	private MessageDao dao = new MessageDao();
	
	public List<Message> queryMessageList( String command,String description ,Page page){
		//组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		//查询总数
		int totalNumber = dao.count(message);
		page.setTotalNumber(totalNumber);
		
		//拼装查询参数
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
