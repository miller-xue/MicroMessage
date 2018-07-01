package com.miller.service;

import java.util.List;
import java.util.Random;

import com.miller.bean.Command;
import com.miller.constants.Iconst;
import com.miller.dao.CommandDao;
import com.miller.dao.MessageDao;

public class QueryService {
	private MessageDao messageDao = new MessageDao();
	
	private CommandDao commandDao = new CommandDao();
	
	/**
	 *  ͨ��ָ���ѯ�Զ��ظ�������
	 * @param command ָ��
	 * @return �Զ��ظ�������
	 */
	public String queryByCommand(String name){
		
		if(Iconst.HELP_COMMAND.equals(name)) {
			
			List<Command> commandList =  commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i = 0 ; i < commandList.size() ; i ++) {
				if(i != 0 ) {
					result.append("<br/>");
				}
				result.append("�ظ���").append(commandList.get(i).getName()).append("�����Բ鿴")
				.append(commandList.get(i).getDescription());
			}
		 return	result.toString();
		}
		
		List<Command> commandList =  commandDao.queryCommandList(name, null);
		if(commandList != null && !commandList.isEmpty()) {
			int random = new Random().nextInt(commandList.get(0).getContentList().size());
			
			return commandList.get(0).getContentList().get(random).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
}
