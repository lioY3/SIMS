package service;

import java.sql.Connection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.BaseDao;
import dao.TeacherDao;
import dao.impl.BaseDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Class;
import model.Course;
import model.Page;
import model.Student;
import model.Teacher;
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
	public String courseList(Course course,String cno,String cname, Page page){
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM course ");
		
		// 参数
		List<Object> param = new LinkedList<>();
		if (course != null) {
			if ((cno != null) && (cno != "")) {// 条件查询
				param.add(cno);
				sb.append("AND cno=? ");
			} else if ((cname != null) && (cname != "")) {
				param.add(cname);
				sb.append("AND cname=? ");
			} 
		}
		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}

		String sql = sb.toString().replaceFirst("AND", "WHERE");
		
		System.out.println(sql);

		// 获取数据
		List<Course> list = dao.getallCourseList(sql, param);
		// 获取总记录数
		long total = getCount(course,cno,cname);
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
	private long getCount(Course course,String cno,String cname) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM course");
		// 参数
		List<Object> param = new LinkedList<>();
		if ((cno != null) && (cno != "")) {// 条件查询
			param.add(cno);
			sb.append("AND cno=? ");
		} else if ((cname != null) && (cname != "")) {
			param.add(cname);
			sb.append("AND cname=? ");
		} 

		String sql = sb.toString().replace("AND", "WHERE");

		long count = dao.count(sql, param).intValue();

		return count;
	}

	/**
	 * 添加课程
	 * @param course
	 */
	public void addCourse(Course course) {
		Teacher teacher1 = getTeacher(course.getCname());

		String tno = teacher1.getTno();
        Course stu=new Course();
		stu.setTno(tno);
		dao.insert("INSERT INTO course(cno,cname,credit,term,hours,tno) value(?,?,?,?,?,?)", new Object[]{course.getCno(),course.getCname(),course.getCredit(),course.getTerm(),course.getHours(),course.getTno()});
	}

	/**
	 * 删除课程
	 * @param courseid
	 * @throws Exception 
	 */
	public void deleteCourse(String cno) {
		// 删除成绩
		dao.delete("DELETE FROM score WHERE cno =? ", new Object[] { cno });
		// 删除课程
		dao.delete("DELETE FROM course WHERE cno =? ", new Object[] { cno });

	}
	
	/**
	 * 获取课程详细信息
	 * 
	 * @param cno
	 * @return
	 */
	public Teacher getTeacher(String tname) {

		TeacherDao dao = new TeacherDaoImpl();

		Teacher teacher1 = (Teacher) dao.getObject(Teacher.class, "SELECT * FROM teacher WHERE tname=?",
				new Object[] { tname });

		return teacher1;
	}

	
	public void editCourse(Course course, String cno) {
		Teacher teacher1 = getTeacher(course.getTno());

		String tno = teacher1.getTno();
		String uid = cno;
        Course stu=new Course();
		stu.setTno(tno);

		List<Object> params = new LinkedList<>();
		params.add(course.getCno());
		params.add(course.getCname());
		params.add(course.getCredit());
		params.add(course.getTerm());
		params.add(course.getHours());
		params.add(course.getTno());
		params.add(uid);
		String sql = "update course " + "set cno = ?,cname = ?,credit = ?,term = ?,hours = ?,tno = ? "
				+ "where cno = ?";

		// 更新课程信息
		dao.update(sql, params);

	}
	
	
}	
	
	

