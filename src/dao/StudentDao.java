package dao;

import java.util.List;

import model.StudentInfo;


/**
 * 操作学生的数据层接口
 *
 */

public interface StudentDao extends BaseDao {
	
	/**
	 * 获取学生详细信息
	 * @param sql 要执行的sql语句
	 * @param param 参数
	 * @return
	 */
	//public List<Student> getStudentList(String sql, List<Object> param);
	public List<StudentInfo> getStudentInfoList(String sql, List<Object> param);
	
}