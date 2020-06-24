package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import model.Course;
import model.Teacher;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DBUtil;
import utils.StringTool;

public class TeacherService {

	private TeacherDao dao;
	private Object number;
	
	public TeacherService(){
		dao = new TeacherDaoImpl();
	}
	
	/**
	 * 获取教师信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getTeacherList(Page page) {
		//sql语句
		String sql = "SELECT * FROM teacher ORDER BY id DESC LIMIT ?,?";
		//获取数据
		List<Teacher> list = dao.getTeacherList(sql, new Object[]{page.getStart(), page.getSize()}, null);
		//获取总记录数
		long total = dao.count("SELECT COUNT(*) FROM teacher", new Object[]{});
		//定义Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();  
		//total键 存放总记录数，必须的
        jsonMap.put("total", total);
        //rows键 存放每页记录 list 
        jsonMap.put("rows", list); 
        //格式化Map,以json格式返回数据
        String result = JSONObject.fromObject(jsonMap).toString();
        
        //返回
		return result;
	}
	
	/**
	 * 获取某个老师的具体信息：包括所选课程
	 * @param number
	 * @return
	 */
	public Teacher getTeacher(String number) {
		//sql语句
		String sql = "SELECT * FROM teacher WHERE number=?";
		//获取数据
		List<Teacher> list = dao.getTeacherList(sql, new Object[]{number}, null);
        //返回
		return list.get(0);
	}
	
	/**
	 * 获取某年级下老师的班级
	 * @param number
	 * @param grade
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getExamClazz(String tno) {
		//sql语句
		String sql = "SELECT * FROM teacher WHERE tno=?";
		//获取数据
		Teacher list = dao.getTeacherList(sql, new Object[]{tno}, null).get(0);
		
		List<Class> clazzList = new LinkedList<>();
		List<Course> courseItem = list.getCourseList();
		for(Course item : courseItem){
			boolean flag = true;
			for(Class clazz : clazzList){
				if(clazz.getId() == item.getClazzid()){
					flag = false;
					break;
				}
			}
			if(flag){
				clazzList.add(item.getClass());
			}
		}
		String result = JSONArray.fromObject(clazzList).toString();
		
        //返回
		return result;
	}
	
	/**
	 * 查询考试下老师的课程
	 * @param number
	 * @param grade
	 * @param clazz
	 * @return
	 */
	public String getCourse(String tno, String clno) {
		//sql语句
		String sql = "SELECT * FROM teacher WHERE tno=?";
		//获取数据
		Teacher list = dao.getTeacherList(sql, new Object[]{tno}, clno).get(0);
		
		List<Course> courseList = new LinkedList<>();
		List<Course> courseItem = list.getCourseList();
		for(Course item : courseItem){
			courseList.add(item.getCno());
		}
		String result = JSONArray.fromObject(courseList).toString();
		
        //返回
		return result;
	}
	
	/**
	 * 获取老师详细信息
	 * @param number
	 * @return
	 */
	public String getTeacherResult(String number) {
		Teacher teacher = getTeacher(number);
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
			
			String sql = "INSERT INTO teacher(tnumber, tname, tsex, tphone) value(?,?,?,?)";
			Object[] param = new Object[]{
					teacher.getTno(), 
					teacher.getTname(), 
					teacher.getTsex(), 
					teacher.getTphone(),
				};
			//添加教师信息
			int teacherid = dao.insertReturnKeysTransaction(conn, sql, param);
			//设置课程
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					int gradeid = Integer.parseInt(gcc[0]);
					int clazzid = Integer.parseInt(gcc[1]);
					int courseid = Integer.parseInt(gcc[2]);
					
					dao.insertTransaction(conn, 
							"INSERT INTO clazz_course_teacher(clazzid, gradeid, courseid, teacherid) value(?,?,?,?) ", 
							new Object[]{clazzid, gradeid, courseid, teacherid});
				}
			}
			//添加用户记录
			dao.insertTransaction(conn, "INSERT INTO user(account, name, type) value(?,?,?)", 
					new Object[]{
						teacher.getTno(),
						teacher.getTname(),
						//User.USER_TEACHER
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
			
			String sql = "UPDATE teacher set name=?,sex=?,phone=?,qq=? WHERE id=?";
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
			dao.deleteTransaction(conn, "DELETE FROM clazz_course_teacher WHERE teacherid =?", new Object[]{teacher.getTno()});
			//设置课程
			if(teacher.getCourse() != null && teacher.getCourse().length > 0){
				for(String course : teacher.getCourse()){
					String[] gcc = course.split("_");
					int gradeid = Integer.parseInt(gcc[0]);
					int clazzid = Integer.parseInt(gcc[1]);
					int courseid = Integer.parseInt(gcc[2]);
					
					dao.insertTransaction(conn, 
							"INSERT INTO clazz_course_teacher(clazzid, gradeid, courseid, teacherid) value(?,?,?,?) ", 
							new Object[]{clazzid, gradeid, courseid, teacher.getTno()});
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
		
		dao.update("UPDATE user SET name=? WHERE tno=?", 
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
		Integer tid[] = new Integer[ids.length];
		for(int i =0 ;i < ids.length;i++){
			tid[i] = Integer.parseInt(ids[i]);
		}
		//获取连接
		Connection conn = DBUtil.getConnection();
		//开启事务
		DBUtil.startTransaction();
		try {
			//删除教师与课程的关联
			dao.deleteTransaction(conn, "DELETE FROM clazz_course_teacher WHERE teacherid IN("+mark+")", tid);
			//删除教师
			dao.deleteTransaction(conn, "DELETE FROM teacher WHERE id IN("+mark+")", tid);
			//删除系统用户
			dao.deleteTransaction(conn, "DELETE FROM user WHERE account IN("+mark+")",  numbers);
			
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
