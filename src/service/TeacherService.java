package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import model.Page;
import model.Teacher;
import model.TeacherInfo;
import net.sf.json.JSONObject;

public class TeacherService {

	TeacherDao dao = new TeacherDaoImpl();
	
	/**
	 * 获取教师信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getTeacherList(TeacherInfo teainfo, String tno, String tname, String cname, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM teacherinfo ");

		// 参数
		List<Object> param = new LinkedList<>();

		System.out.println(tno + "+" + tname);

		if (teainfo != null) {
			if ((tno != null) && (tno != "")) {// 条件查询
				param.add(tno);
				sb.append("AND tno=? ");
			} else if ((tname != null) && (tname != "")) {
				param.add(tname);
				sb.append("AND tname=? ");
			} else if ((cname != null) && (cname != "")) {
				param.add(cname);
				sb.append("AND tcourse=? ");
			} 
		}

		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		System.out.println("全部查询：" + sql);

		// 获取数据
		List<Teacher> list = dao.getTeacherList(sql, param);
		// 获取总记录数
		long total = getCount(tno, tname, cname, teainfo);
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
	 */
	private long getCount(String tno, String tname, String cname, TeacherInfo teainfo) {
		// SQL语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM teacherinfo ");
		// 参数
		List<Object> param = new LinkedList<>();

		if ((tno != null) && (tno != "")) {// 条件查询
			param.add(tno);
			sb.append("AND tno=? ");
		} else if ((tname != null) && (tname != "")) {
			param.add(tname);
			sb.append("AND tname=? ");
		} else if ((cname != null) && (cname != "")) {
			param.add(cname);
			sb.append("AND cname=? ");
		} 

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		System.out.println("条件查询：" + sql);

		long count = dao.count(sql, param);

		System.out.println(count);

		return count;
	}
	
	/**
	 * 获取某个老师的具体信息：包括所选课程
	 * @param number
	 * @return
	 */
	public TeacherInfo getTeacher(String tno) {
		//sql语句
		String sql = "SELECT * FROM teacherinfo WHERE tno=?";
		//获取数据
		List<TeacherInfo> list = dao.getTeacherList(sql, new Object[]{tno});
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
		TeacherInfo teainfo = getTeacher(tno);
		String result = JSONObject.fromObject(teainfo).toString();
        //返回
		return result;
	}
	
	/**
	 * 添加老师信息
	 * @param teacher
	 * @throws Exception 
	 */
	public void addTeacher(TeacherInfo teainfo) {
		//Class class1 = getClass(stuinfo.getClname());

		//String clno = class1.getClno();

		//Student stu = new Student();
		//stu.setClno(clno);

		// 添加教师记录
		dao.insert("INSERT INTO teacher(tno, tname, tsex, tcourse) value(?,?,?,?)",
				new Object[] { teainfo.getTno(), teainfo.getTname(), teainfo.getTsex(), teainfo.getCname() });

	}
	
	/**
	 * 修改教师信息
	 * @param teacher
	 * @throws Exception
	 */
	public void editTeacher(TeacherInfo teainfo, String tno) throws Exception {

		String uid = tno;

		List<Object> params = new LinkedList<>();
		params.add(teainfo.getTno());
		params.add(teainfo.getTname());
		params.add(teainfo.getTsex());
		params.add(teainfo.getCname());
		params.add(uid);

		String sql = "update teacherinfo "
				+ "set tno = ?,tname = ?,tsex =?,tname = ? "
				+ "where tno = ?";

		// 更新教师信息
		dao.update(sql, params);
		
	}

	
	/**
	 * 教师修改个人信息
	 * @param teacher
	 */
//	public void editTeacherPersonal(Teacher teacher){
//		
//		String sql = "UPDATE teacher SET tname=?, tsex=?, tphone=? WHERE tno=?";
//		
//		//更新信息
//		dao.update(sql, new Object[]{
//				teacher.getTname(), 
//				teacher.getTsex(),
//				teacher.getTphone()});
//		
//		dao.update("UPDATE user SET username=? WHERE accound=?", 
//				new Object[]{teacher.getTname(), teacher.getTno()});
//	}
	
	/**
	 * 删除教师
	 * @throws Exception 
	 */
	public void deleteTeacher(String tno) {
		// 删除课程
		dao.delete("DELETE FROM course WHERE tno =? ", new Object[] { tno });
		// 删除教师
		dao.delete("DELETE FROM teacher WHERE tno =? ", new Object[] { tno });

	}
	
}
