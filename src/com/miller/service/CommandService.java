package com.miller.service;

import java.util.ArrayList;
import java.util.List;

import com.miller.bean.Command;
import com.miller.bean.CommandContent;
import com.miller.dao.CommandContentDao;
import com.miller.dao.CommandDao;

public class CommandService {
	
	private CommandDao commandDao = new CommandDao();
	
	private CommandContentDao commandContentDao = new CommandContentDao();
	
	public void save(String name,String description,String[] contents) {
		
		Command command = new Command();
		command.setName(name);
		command.setDescription(description);
		commandDao.insert(command);
		
		List<CommandContent> list = new ArrayList<>();
		for(String content :contents) {
			CommandContent commandContent  = new CommandContent();
			
			commandContent.setCommandId(command.getId());
			commandContent.setContent(content);
			
			list.add(commandContent);
		}
		commandContentDao.insertBatch(list);
	}

}
