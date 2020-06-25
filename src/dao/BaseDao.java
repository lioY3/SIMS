package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Course;


/**
 * 基础dao
 *
 */
@SuppressWarnings("rawtypes")
public interface BaseDao {
	
	/**
	 * 获取数据集合
	 * @param type 返回对象的class
	 * @param sql 要执行的sql语句
	 * @return
	 */
	List<Object> getList(Class type, String sql);
	
	/**
	 * 获取数据集合
	 * @param type 返回对象的class
	 * @param sql 执行的sql语句
	 * @param param Object[] 数组参数
	 * @return
	 */
	List<Object> getList(Class type, String sql, Object[] param);
	
	/**
	 * 获取数据集合
	 * @param type 返回对象的class
	 * @param sql 执行的sql语句
	 * @param param List<Object>集合参数
	 * @return
	 */
	List<Object> getList(Class type, String sql, List<Object> param);
	
	/**
	 * 获取数据集合
	 * @param conn 数据库连接
	 * @param type
	 * @param sql
	 * @return
	 */
	List<Object> getList(Connection conn, Class type, String sql);
	
	/**
	 * 获取数据集合
	 * @param conn
	 * @param type
	 * @param sql
	 * @param param 将参数封装到数组对象中
	 * @return
	 */
	List<Object> getList(Connection conn, Class type, String sql, Object[] param);
	
	/**
	 * 获取数据集合
	 * @param conn
	 * @param type
	 * @param sql
	 * @param param 将参数封装到集合对象中
	 * @return
	 */
	List<Object> getList(Connection conn, Class type, String sql, List<Object> param);
	
	/**
	 * 获取一个对象 *****
	 * @param type
	 * @param sql
	 * @param param
	 * @return
	 */
	Object getObject(Class type, String sql, Object[] param);
	
	/**
	 * 获取一个对象
	 * @param conn 数据库连接
	 * @param type
	 * @param sql
	 * @param param
	 * @return
	 */
	Object getObject(Connection conn, Class type, String sql, Object[] param);
	
	/**
	 * 获取记录数
	 * @param sql
	 * @return
	 */
	Long count(String sql);
	
	/**
	 * 获取记录数
	 * @param sql
	 * @param param
	 * @return
	 */
	Long count(String sql, Object[] param);
	
	/**
	 * 获取记录数 *****
	 * @param sql
	 * @param param
	 * @return
	 */
	Long count(String sql, List<Object> param);
	
	
	/**
	 * 插入或更新一条数据 *****
	 * @param sql
	 * @param param
	 */
	void update(String sql, Object[] param);
	
	/**
	 * 插入或更新一条数据 *****
	 * @param sql
	 * @param param
	 */
	void update(String sql, List<Object> param);
	
	
	/**
	 * 插入一条数据 *****
	 * @param sql
	 * @param param
	 */
	void insert(String sql, Object[] param);

	/**
	 * 删除 *****
	 * @param sql
	 * @param param
	 */
	void delete(String sql, Object[] param);

}
