package dao;

import java.util.List;

import model.TeacherInfo;


/**
 * 操作教师的数据层接口
 *
 */
public interface TeacherDao extends BaseDao {
	
	public List<TeacherInfo> getTeacherList(String sql, List<Object> param);

}