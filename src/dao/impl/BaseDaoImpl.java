package dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.BaseDao;
import utils.DBUtil;


/**
 * 基础Dao层
 *
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl implements BaseDao{
		
	@SuppressWarnings("rawtypes")
	public Object getObject(Class type, String sql, Object[] param) {
		QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
		Object obj = new LinkedList<>();
		try {
			obj = qr.query(sql, new BeanHandler(type), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;

	}
	
	
	@SuppressWarnings("rawtypes")
	public Long count(String sql, Object[] param) {
		QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
		Long count = 0L;
		try {
			count = (Long) qr.query(sql, new ScalarHandler(), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Long count(String sql, List<Object> param) {
		//将集合中的参数封装到数组对象中
		Object[] params = new Object[param.size()];
		for(int i = 0;i < param.size();i++){
			params[i] = param.get(i);
		}
		return count(sql, params);
	}
	
	public void update(String sql, Object[] param) {
		QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String sql, List<Object> param) {
		Object[] params = new Object[param.size()];
		for(int i = 0;i < param.size();i++){
			params[i] = param.get(i);
		}
		update(sql, params);
	}
	
	
	public void insert(String sql, Object[] param) {
		update(sql, param);
	}
	
	
	public void delete(String sql, Object[] param) {
		update(sql, param);
	}
	
}
