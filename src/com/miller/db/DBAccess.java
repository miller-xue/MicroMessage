package com.miller.db;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** �������ݿ���
 * @author Miller
 *
 */
public class DBAccess {
	private DBAccess() {
		
	}
	
	private static final String CONFIG_PATH = "com/miller/config/Configuration.xml";
	
	private static SqlSessionFactory factory = null;
	
	static {
		// ͨ�������ļ���ȡ���ݿ�������Ϣ
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(CONFIG_PATH);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static SqlSession getSqlSession(){
		// ͨ��������Ϣ����SqlSessionFactory
		return factory.openSession();
	}
}
