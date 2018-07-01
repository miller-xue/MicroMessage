package com.miller.bean;

import java.util.List;

import lombok.Data;

/**
 * @author Miller
 *	��ָ����Ӧʵ����
 */
@Data
public class Command {
	
	private int id;
	
	/**
	 * ָ������
	 */
	private String name;
	
	/**
	 * ����
	 */
	private String description;
	
	/**
	 * һ��ָ���Ӧ�Զ��ظ��б�
	 */
	private List<CommandContent> contentList;
}
