package com.miller.bean;

import lombok.Data;

/**
 * ����Ϣ���Ӧ��ʵ����
 * 
 * @author Miller
 *
 */
@Data
public class Message {
	/**
	 * ����
	 */
	private String id;

	/**
	 * ָ������
	 */
	private String command;
	
	/**
	 * ����
	 */
	private String description;
	
	/**
	 * ����
	 */
	private String content;
}
