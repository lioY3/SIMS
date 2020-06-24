package dao;

import java.util.List;

/**
 * 班级数据层接口
 *
 */

public interface ClassDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	List<Class> getClazzDetailList(String clno);
	
	/**
	 * 获取班级详细信息
	 * @param Clno 班级ID
	 * @return
	 */
	
}