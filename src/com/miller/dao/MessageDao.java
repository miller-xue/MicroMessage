package com.miller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.miller.bean.Message;
import com.miller.db.DBAccess;
import com.miller.entity.Page;
import com.miller.util.MessageUtils;

import lombok.Cleanup;
import lombok.extern.java.Log;

/**
 * 和Message表相关的数据库操作
 * @author Miller
 *
 */
@Log
public class MessageDao {
	
	/**
	 * 根据查询条件查询消息列表
	 * @param command
	 * @param description
	 * @return 消息列表
	 */
	public List<Message> queryMessageList( String command,String description){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Message> result = null;
		try {
			// 创建连接
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.163.135:3306/micro_message","xuehui","xuehui");
			// 拼接sql
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1 = 1");
			List<String> paramsList = new ArrayList<>();
			if(!MessageUtils.isEmpty(command)) {
				sql.append(" and COMMAND = ?");
				paramsList.add(command);
			}
			if(!MessageUtils.isEmpty(description)) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramsList.add(description);
			}
			stat = conn.prepareStatement(sql.toString());
			for (int i = 0 ; i < paramsList.size(); i++) {
				stat.setString(i+ 1, paramsList.get(i));
			}
			paramsList.clear();
			// 执行查询			
			rs = stat.executeQuery();
			result = new ArrayList<Message>();
			// 封装结果
			while(rs.next()) {
				Message message = new Message();
				message.setId(rs.getString("ID"));
				message.setContent(rs.getString("CONTENT"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				result.add(message);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MessageUtils.close(rs);
			MessageUtils.close(stat);
			MessageUtils.close(conn);
		}
		return result;
	}
	
	
	public List<Message> queryMessageListByMybatis( Map map){
		List<Message> messageList = null;
		try {
			@Cleanup SqlSession sqlSession = DBAccess.getSqlSession();
			// 获得代理接口的实现类
			MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
			System.out.println(mapper.getClass().getName());
			
			//调用接口方法执行invoke方法,但是不会进行执行,因为没有被代理的实现类,根据方法名和配置文件去判断执行sql语句
			messageList = mapper.queryMessageListByMybatis(map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messageList;
	}
	
	public List<Message> queryMessageListByPage(Map map){
		List<Message> messageList = null;
		try {
			@Cleanup SqlSession sqlSession = DBAccess.getSqlSession();
			MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
			
			messageList = mapper.queryMessageListByPage(map);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return messageList;
	}
	
	/**
	 * 单条删除
	 * @param id
	 * @return
	 */
	public int deleteById(int id) {
		int result = 0;
		try {
			@Cleanup SqlSession sqlSession = DBAccess.getSqlSession();
			result = sqlSession.delete("Message.deleteById",id);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids) {
		int result = 0;
		try {
			@Cleanup SqlSession sqlSession = DBAccess.getSqlSession();
			result = sqlSession.delete("com.miller.dao.MessageMapper.deleteBatch",ids);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int count(Message message) {
		int result = 0;
		try {
			@Cleanup
			SqlSession sqlSession = DBAccess.getSqlSession();
		    MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
		    result = mapper.count(message);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
