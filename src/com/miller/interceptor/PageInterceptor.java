package com.miller.interceptor;

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
import com.mysql.jdbc.Connection;

import lombok.Cleanup;

/**
 * @author Miller
 *	分页拦截器
 */

@Intercepts(value = {@Signature(type = StatementHandler.class , method="prepare", args= { Connection.class} )})
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		//配置文件中sql的id
		String sqlId = mappedStatement.getId();
		if(sqlId.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// 获得原始sql语句
			String sql = boundSql.getSql();
			//获取参数
			Map<String,Object> parameter = (Map<String, Object>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			
			
			//查询总条数
			String countSql = "select count(*) from (" + sql + ")a";
			
			@Cleanup
			Connection conn = (Connection)invocation.getArgs()[0];	
			@Cleanup
			PreparedStatement countStatement =  conn.prepareStatement(countSql);
			//注入参数
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			@Cleanup
			ResultSet rs = countStatement.executeQuery();
			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// 获得参数map
			
			String pageSql = sql + "limit" + page.getDbIndex() + "," + page.getDbNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return null;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}
	
}
