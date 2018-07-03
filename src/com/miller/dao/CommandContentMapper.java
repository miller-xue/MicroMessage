package com.miller.dao;

import java.util.List;

import com.miller.bean.CommandContent;

/**
 * @author Miller
 * ��CommandContent�����ļ����Ӧ�Ľӿ�
 */
public interface CommandContentMapper {
	/**
	 * ��������
	 * @param commandContent
	 * @return
	 */
	public int insertOne(CommandContent commandContent);
	
	/**
	 * ��������
	 * @param list
	 * @return
	 */
	public int insertBatch(List<CommandContent> list);
}
