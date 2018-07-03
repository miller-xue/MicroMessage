package com.miller.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import com.miller.entity.Page;

/**
 * @author Miller
 *	��ҳ������
 */

@Intercepts(value = {@Signature(type = StatementHandler.class , method="prepare", args= { Connection.class} )})
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//������ض���Ŀ��
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		//�����ļ���sql��id
		String sqlId = mappedStatement.getId();
		if(sqlId.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// ���ԭʼsql���
			String sql = boundSql.getSql();
			
			//��ѯ������
			String countSql = "select count(*) from (" + sql + ")a";
			
			Connection conn = (Connection)invocation.getArgs()[0];	
			PreparedStatement countStatement =  conn.prepareStatement(countSql);
			//ע�����
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			
			//��ȡ����
			Map<?,?> parameter = (Map<?, ?>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			
			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// ��ò���map
			
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String property = properties.getProperty("test");
		System.out.println(property);
	}
	
}
