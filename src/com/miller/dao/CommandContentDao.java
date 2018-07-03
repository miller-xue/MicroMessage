package com.miller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.miller.bean.CommandContent;
import com.miller.db.DBAccess;

import lombok.Cleanup;

/**
 * @author Miller
 *	内容列表 TODO 测试
 */
public class CommandContentDao {
	
	public void insertBatchByJdbc(List<CommandContent> commandContents) {
		
		try {
			// 创建连接
			Class.forName("com.mysql.jdbc.Driver");
			@Cleanup
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.163.135:3306/micro_message","xuehui","xuehui");
			
			StringBuilder sql = new StringBuilder("insert into command_content(content,command_id) value(?,?)");
			@Cleanup
			PreparedStatement stat = conn.prepareStatement(sql.toString());
			
			for(CommandContent content: commandContents) {
				stat.setString(1,  content.getContent());
				stat.setInt(2, content.getCommandId());
				stat.addBatch();
			}
			stat.executeBatch();
			// 执行查询			
			stat.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertBatch(List<CommandContent> commandContents) {
		@Cleanup
		SqlSession session = DBAccess.getSqlSession();
		try {
			CommandContentMapper mapper = session.getMapper(CommandContentMapper.class);
			mapper.insertBatch(commandContents);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
