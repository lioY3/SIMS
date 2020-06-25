package dao;

import java.util.List;

import model.Course;

public interface CourseDao extends BaseDao {

	List<Course> getallCourseList(String sql, List<Object> param);

}
