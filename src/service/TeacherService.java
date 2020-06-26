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

	public String getTeacherList(Teacher teacher, String tname, String tno, String cname, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM teacherinfo ");

		// 参数
		List<Object> param = new LinkedList<>();

		//System.out.println(tname+" "+tno+" "+cname);

		if (teacher != null) {
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
		}

		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		//System.out.println("全部查询：" + sql);

		// 获取数据
		List<TeacherInfo> list = dao.getTeacherList(sql, param);
		// 获取总记录数
		long total = getCount(tno, tname, cname, teacher);

		// 定义Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		// code键 存放状态值，0为正常
		jsonMap.put("code", 0);
		// count键 存放总记录数，必须的
		jsonMap.put("count", total);
		// msg键 存放消息，可空
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
	private long getCount(String tno, String tname, String cname, Teacher teacher) {
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

		//System.out.println("条件查询：" + sql);

		long count = dao.count(sql, param);

		//System.out.println(count);

		return count;
	}
		
	/**
	 * 添加老师信息
	 * @param teacher
	 * @throws Exception 
	 */
	public void addTeacher(Teacher teacher) {
		

		// 添加教师记录
		dao.insert("INSERT INTO teacher(tno, tname, tsex) value(?,?,?)",
				new Object[] { teacher.getTno(), teacher.getTname(), teacher.getTsex()});

	}
	
	
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
