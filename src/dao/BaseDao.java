package dao;

import java.util.List;

/**
 * 基础dao
 *
 */
@SuppressWarnings("rawtypes")
public interface BaseDao {
	
	/**
	 * 获取一个对象 *****
	 * 
	 * @param type
	 * @param sql
	 * @param param
	 * @return
	 */
	Object getObject(Class type, String sql, Object[] param);

	/**
	 * 获取记录数 *****
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	Long count(String sql, List<Object> param);

	/**
	 * 插入或更新一条数据 *****
	 * 
	 * @param sql
	 * @param param
	 */
	void update(String sql, Object[] param);

	/**
	 * 插入或更新一条数据 *****
	 * 
	 * @param sql
	 * @param param
	 */
	void update(String sql, List<Object> param);

	/**
	 * 插入一条数据 *****
	 * 
	 * @param sql
	 * @param param
	 */
	void insert(String sql, Object[] param);

	/**
	 * 删除 *****
	 * 
	 * @param sql
	 * @param param
	 */
	void delete(String sql, Object[] param);

}
