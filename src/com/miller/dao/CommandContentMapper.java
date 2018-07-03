package com.miller.dao;

import java.util.List;

import com.miller.bean.CommandContent;

/**
 * @author Miller
 * 与CommandContent配置文件相对应的接口
 */
public interface CommandContentMapper {
	/**
	 * 单条新增
	 * @param commandContent
	 * @return
	 */
	public int insertOne(CommandContent commandContent);
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public int insertBatch(List<CommandContent> list);
}
