package dao;

import java.util.List;

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
	 * @param grade 年级参数
	 * @param clazz 班级参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Teacher> getTeacherList(String sql, Object[] param, Class clno);
	
}