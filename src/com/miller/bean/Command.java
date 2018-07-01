package com.miller.bean;

import java.util.List;

import lombok.Data;

/**
 * @author Miller
 *	与指令表对应实体类
 */
@Data
public class Command {
	
	private int id;
	
	/**
	 * 指令名称
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 一条指令对应自动回复列表
	 */
	private List<CommandContent> contentList;
}
