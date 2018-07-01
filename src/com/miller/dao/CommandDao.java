package com.miller.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.miller.bean.Command;
import com.miller.db.DBAccess;

import lombok.Cleanup;

/**
 * @author Miller
 * 与指令对应的数据库操作
 */
public class CommandDao {

	
	public List<Command> queryCommandList(
			String name,String description){
		List<Command> commandList = null;
		try {
			@Cleanup SqlSession sqlSession = DBAccess.getSqlSession();
			
			Command command = new Command();
			command.setName(name);
			command.setDescription(description);
			
			commandList = sqlSession.selectList("Command.queryCommandList",command);
		} catch (Exception e) {
		}
		return commandList;
	}
	
	public int insert(String name, String description) {
		Command command = new Command();
		command.setName(name);
		command.setDescription(description);
		SqlSession sqlSession = DBAccess.getSqlSession();
		int insert = sqlSession.insert("Command.insert",command);
		sqlSession.commit();
		return insert;
	}
	public static void main(String[] args) {
		CommandDao commandDao = new CommandDao();
		commandDao.insert("21", "21");
	}
}
