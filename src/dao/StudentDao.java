package dao;

import java.util.List;

import model.StudentInfo;


/**
 * 操作学生的数据层接口
 */

public interface StudentDao extends BaseDao {
	
	/**
	 * 获取学生详细信息,从视图中查询
	 */
	public List<StudentInfo> getStudentInfoList(String sql, List<Object> param);

	
}