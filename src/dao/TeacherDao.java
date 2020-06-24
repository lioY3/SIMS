package dao;

import java.util.List;

import model.Class;

import model.Teacher;


/**
 * 操作教师的数据层接口
 *
 */
public interface TeacherDao extends BaseDao {
	
	/**
	 * 获取教师信息，这里需要将教师所选择的课程查询出来
	 * @param sql
	 * @param param
	 * @param class 班级参数
	 * @return
	 */
	public List<Teacher> getTeacherList(String sql, Object[] param, String clno);
	
}