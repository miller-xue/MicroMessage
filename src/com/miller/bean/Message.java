package com.miller.bean;

import lombok.Data;

/**
 * 与消息表对应的实体类
 * 
 * @author Miller
 *
 */
@Data
public class Message {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 指令名称
	 */
	private String command;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 内容
	 */
	private String content;
}
