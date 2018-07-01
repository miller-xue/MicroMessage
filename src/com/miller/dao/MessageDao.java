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
 * ��Message����ص����ݿ����
 * @author Miller
 *
 */
@Log
public class MessageDao {
	
	/**
	 * ���ݲ�ѯ������ѯ��Ϣ�б�
	 * @param command
	 * @param description
	 * @return ��Ϣ�б�
	 */
	public List<Message> queryMessageList( String command,String description){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Message> result = null;
		try {
			// ��������
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.163.135:3306/micro_message","xuehui","xuehui");
			// ƴ��sql
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
			// ִ�в�ѯ			
			rs = stat.executeQuery();
			result = new ArrayList<Message>();
			// ��װ���
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
			// ��ô���ӿڵ�ʵ����
			MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
			System.out.println(mapper.getClass().getName());
			
			//���ýӿڷ���ִ��invoke����,���ǲ������ִ��,��Ϊû�б������ʵ����,���ݷ������������ļ�ȥ�ж�ִ��sql���
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
	 * ����ɾ��
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
	 * ����ɾ��
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
