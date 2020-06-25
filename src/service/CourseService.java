package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.impl.BaseDaoImpl;
import model.Class;
import model.Course;
import model.Page;
import model.Student;
import model.StudentInfo;
import net.sf.json.JSONObject;
import utils.DBUtil;

/**
 * 课程服务层
 *
 */

public class CourseService {

	BaseDao dao = new BaseDaoImpl();
	
	/**
	 * 获取所有课程
	 * @return
	 */
	public String getCourseList(Course course, Page page){
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM course ");
		
		// 参数
		List<Object> param = new LinkedList<>();

		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}

		// String sql = sb.toString().replaceFirst("AND", "WHERE");
		String sql = sb.toString();
		
		System.out.println(sql);

		// 获取数据
		List<Course> list = dao.getallCourseList(sql, param);
		// 获取总记录数
		long total = getCount(course);
		// 定义Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// code键 存放状态值，0为正常
		jsonMap.put("code", 0);
		// count键 存放总记录数，必须的
		jsonMap.put("count", total);
		// msg键 存放消息，空
		jsonMap.put("msg", "cwj笨蛋");
		// data键 存放每页记录 list
		jsonMap.put("data", list);
		// 格式化Map,以json格式返回数据
		String result = JSONObject.fromObject(jsonMap).toString();
		// 返回结果
		return result;
	}


	/**
	 * 获取记录数
	 * 
	 * @param student
	 * @return
	 */
	private long getCount(Course course) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM course");
		// 参数
		List<Object> param = new LinkedList<>();

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		long count = dao.count(sql, param).intValue();

		return count;
	}

	/**
	 * 添加课程
	 * @param course
	 */
	public void addCourse(Course course) {
		//dao.insert("INSERT INTO course(name) value(?)", new Object[]{course.getCname()});
	}

	/**
	 * 删除课程
	 * @param courseid
	 * @throws Exception 
	 */
	public void deleteCourse(String cno) throws Exception {
		//获取连接
		Connection conn = DBUtil.getConnection();
		try {
			//开启事务
			DBUtil.startTransaction();
			//删除成绩表
			dao.deleteTransaction(conn, "DELETE FROM score WHERE cno=?", new Object[]{cno});
			//删除班级的课程和老师的关联
			dao.deleteTransaction(conn, "DELETE FROM class_course_teacher WHERE cno=?", new Object[]{cno});
			//最后删除课程
			dao.deleteTransaction(conn, "DELETE FROM course WHERE cno=?",  new Object[]{cno});
			
			//提交事务
			DBUtil.commit();
		} catch (Exception e) {
			//回滚事务
			DBUtil.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.closeConnection();
		}
	}
	
	/**
	 * 获取课程详细信息
	 * 
	 * @param cno
	 * @return
	 */
	public Course getCourse(String cno) {

		BaseDao dao = new BaseDaoImpl();

		Course course = (Course) dao.getObject(Course.class, "SELECT * FROM course WHERE cno=?",
				new Object[] { cno });

		return course;
	}

	
	public void editCourse(Course course, String cno) {
		Course course1 = getCourse(course.getCname());

		String cno1 = course1.getCno();


		Student stu = new Student();
//		stu.setClno(clno);
//
//		List<Object> params = new LinkedList<>();
//		params.add(stuinfo.getSno());
//		params.add(stuinfo.getSname());
//		params.add(stuinfo.getSsex());
//		params.add(stuinfo.getSbirthday());
//		params.add(stuinfo.getSid());
//		params.add(stuinfo.getSnation());
//		params.add(stu.getClno());
//		params.add(uid);

		//String sql = "update student " + "set sno = ?,sname = ?,ssex =?,sbirthday = ?,sid=?,snation=?,clno=? "
				//+ "where sno = ?";

		// 更新课程信息
		//dao.update(sql, params);

	}
	
	
}	
	
	

