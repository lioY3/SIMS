package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import model.Page;
import model.Teacher;
import model.User;
import net.sf.json.JSONObject;
import utils.DBUtil;
import utils.StringTool;

public class TeacherService {

	TeacherDao dao = new TeacherDaoImpl();
	
	/**
	 * 获取教师信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getTeacherList(Teacher teacher,Page page) {
		//sql语句
		//String sql = "SELECT * FROM teacher ORDER BY tno DESC LIMIT ?,?";
		StringBuffer sb = new StringBuffer("SELECT * FROM teacher ");
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
		List<Teacher> list = dao.getTeacherList(sql, param);
		// 获取总记录数
		long total = getCount(teacher);
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
	 * @param teacher
	 * @return
	 */
	private long getCount(Teacher teacher) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM teacher");
		// 参数
		List<Object> param = new LinkedList<>();

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		long count = dao.count(sql, param).intValue();

		return count;
	}
	
	/**
	 * 获取某个老师的具体信息：包括所选课程
	 * @param number
	 * @return
	 */
	public Teacher getTeacher(String tno) {
		//sql语句
		String sql = "SELECT * FROM teacher WHERE tno=?";
		//获取数据
		List<Teacher> list = dao.getTeacherList(sql, new Object[]{tno});
        //返回
		return list.get(0);
	}
	
	
//	/**
//	 * 查询课程下老师的课程
//	 * @param tno
//	 * @param class
//	 * @return
//	 */
//	public String getCourse(String tno, String clno) {
//		//sql语句
//		String sql = "SELECT * FROM teacher WHERE tno=?";
//		//获取数据
//		Teacher list = dao.getTeacherList(sql, new Object[]{tno}).get(0);
//		
//		List<Course> courseList = new LinkedList<>();
//		List<Course> courseItem = list.getCourseList();
//		for(Course item : courseItem){
//			courseList.add(item.getCno());
//		}
//		String result = JSONArray.fromObject(courseList).toString();
//		
//        //返回
//		return result;
//	}
	
	/**
	 * 获取老师详细信息
	 * @param number
	 * @return
	 */
	public String getTeacherResult(String tno) {
		Teacher teacher = getTeacher(tno);
		String result = JSONObject.fromObject(teacher).toString();
        //返回
		return result;
	}
	
	/**
	 * 添加老师信息
	 * @param teacher
	 * @throws Exception 
	 */
	public void addTeacher(Teacher teacher) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			//开启事务
			DBUtil.startTransaction();
			
			String sql = "INSERT INTO teacher(tno, tname, tsex, tphone) value(?,?,?,?)";
			Object[] param = new Object[]{
					teacher.getTno(), 
					teacher.getTname(), 
					teacher.getTsex(), 
					teacher.getTphone(),
				};
			//添加教师信息
			int tno = dao.insertReturnKeysTransaction(conn, sql, param);
			//设置课程
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					String clno = gcc[1];
					String cno = gcc[2];
					
					dao.insertTransaction(conn, 
							"INSERT INTO clazz_course_teacher(clno, cno, tno) value(?,?,?) ", 
							new Object[]{clno, cno, tno});
				}
			}
			//添加用户记录
			dao.insertTransaction(conn, "INSERT INTO user(account, name, type) value(?,?,?)", 
					new Object[]{
						teacher.getTno(),
						teacher.getTname(),
						User.TEACHER
				});
			
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
	 * 修改教师信息
	 * @param teacher
	 * @throws Exception
	 */
	public void editTeacher(Teacher teacher) throws Exception {
		Connection conn = DBUtil.getConnection();
		try {
			//开启事务
			DBUtil.startTransaction();
			
			String sql = "UPDATE teacher set tname=?,tsex=?,tphone=? WHERE tno=?";
			Object[] param = new Object[]{
					teacher.getTname(), 
					teacher.getTsex(), 
					teacher.getTphone(),
				};
			//修改教师信息
			dao.updateTransaction(conn, sql, param);
			//修改系统用户信息
			dao.update("UPDATE user SET tname=? WHERE tno=?", 
					new Object[]{teacher.getTname(), teacher.getTno()});
			//删除教师与课程的关联
			dao.deleteTransaction(conn, "DELETE FROM class_course_teacher WHERE tno =?", new Object[]{teacher.getTno()});
			//设置课程
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					String clno = gcc[0];
					String cno = gcc[1];
					
					dao.insertTransaction(conn, 
							"INSERT INTO class_course_teacher(clno, cno, tno) value(?,?,?) ", 
							new Object[]{clno, cno, teacher.getTno()});
				}
			}
			
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
	 * 教师修改个人信息
	 * @param teacher
	 */
	public void editTeacherPersonal(Teacher teacher){
		
		String sql = "UPDATE teacher SET tname=?, tsex=?, tphone=? WHERE tno=?";
		
		//更新信息
		dao.update(sql, new Object[]{
				teacher.getTname(), 
				teacher.getTsex(),
				teacher.getTphone()});
		
		dao.update("UPDATE user SET username=? WHERE accound=?", 
				new Object[]{teacher.getTname(), teacher.getTno()});
	}
	
	/**
	 * 删除教师
	 * @param ids 教师ID数组
	 * @param numbers 教师工号数组
	 * @throws Exception 
	 */
	public void deleteTeacher(String[] ids, String[] numbers) throws Exception{
		//获取占位符
		String mark = StringTool.getMark(ids.length);
		String tno[] = new String[ids.length];
		for(int i =0 ;i < ids.length;i++){
			tno[i] = ids[i];
		}
		//获取连接
		Connection conn = DBUtil.getConnection();
		//开启事务
		DBUtil.startTransaction();
		try {
			//删除教师与课程的关联
			dao.deleteTransaction(conn, "DELETE FROM class_course_teacher WHERE tno IN("+mark+")", tno);
			//删除教师
			dao.deleteTransaction(conn, "DELETE FROM teacher WHERE tno IN("+mark+")", tno);
			//删除系统用户
			dao.deleteTransaction(conn, "DELETE FROM user WHERE account IN("+mark+")",  tno);
			
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
	
}
