package com.miller.db;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/** 访问数据库类
 * @author Miller
 *
 */
public class DBAccess {
	private DBAccess() {
		
	}
	
	private static final String CONFIG_PATH = "com/miller/config/Configuration.xml";
	
	private static SqlSessionFactory factory = null;
	
	static {
		// 通过配置文件获取数据库连接信息
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
		// 通过配置信息构建SqlSessionFactory
		return factory.openSession();
	}
}
