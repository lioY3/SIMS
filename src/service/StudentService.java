package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Page;
import model.Student;
import net.sf.json.JSONObject;

public class StudentService {

	StudentDao dao = new StudentDaoImpl();

	public String getStudentList(Student student, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM student ");
		//		+ " join class on student.clno=class.clno" + " join department on department.dno=class.dno ");
		
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
		List<Student> list = dao.getStudentList(sql, param);
		// 获取总记录数
		long total = getCount(student);
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
	 * @param page
	 * @return
	 */
	private long getCount(Student student) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM student");
		// 参数
		List<Object> param = new LinkedList<>();

		String sql = sb.toString().replaceFirst("AND", "WHERE");

		long count = dao.count(sql, param).intValue();

		return count;
	}

}
