package com.miller.dao;

import java.util.List;
import java.util.Map;

import com.miller.bean.Message;

/**
 * @author Miller
 * 与message配置文件相对应的接口
 * namespace = com.miller.dao.MessageMapper
 */
public interface MessageMapper {
	
	/**
	 * 方法名 == sql中的id 参数 == parameterType resultMap == 返回值
	 * @param message
	 * @return
	 */
	public List<Message> queryMessageListByMybatis(Map map);
	
	
	
	public List<Message> queryMessageListByPage(Map map);
	
	
	public int count(Message message);

}
