package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.ClassDao;
import dao.ClassDaoImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
import model.Page;
import model.Student;
import model.Class;
import model.StudentInfo;
import net.sf.json.JSONObject;

public class StudentService {

	StudentDao dao = new StudentDaoImpl();

	/**
	 * 获取记录数
	 */
	private long getCount(String sno, String sname, String clname, Student student) {
		// SQL语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM studentinfo ");
		// 参数
		List<Object> param = new LinkedList<>();

		if ((sno != null) && (sno != "")) {// 条件查询
			param.add(sno);
			sb.append("AND sno=? ");
		} else if ((sname != null) && (sname != "")) {
			param.add(sname);
			sb.append("AND sname=? ");
		} else if ((clname != null) && (clname != "")) {
			param.add(clname);
			sb.append("AND clname=? ");
		}

		String sql = sb.toString().replace("AND", "WHERE");

		//System.out.println("条件查询：" + sql);

		long count = dao.count(sql, param);

		//System.out.println(count);

		return count;
	}

	public void addStudent(StudentInfo stuinfo) {
		Class class1 = getClass(stuinfo.getClname());

		String clno = class1.getClno();

		Student stu = new Student();
		stu.setClno(clno);

		// 添加学生记录
		dao.insert("INSERT INTO student(sno, sname, ssex, sbirthday, sid, snation, clno) value(?,?,?,?,?,?,?)",
				new Object[] { stuinfo.getSno(), stuinfo.getSname(), stuinfo.getSsex(), stuinfo.getSbirthday(),
						stuinfo.getSid(), stuinfo.getSnation(), stu.getClno() });

	}

	/**
	 * 获取班级详细信息
	 * 
	 * @param account
	 * @return
	 */
	public Class getClass(String clname) {

		ClassDao dao = new ClassDaoImpl();

		Class class1 = (Class) dao.getObject(Class.class, "SELECT * FROM class WHERE clname=?",
				new Object[] { clname });

		return class1;
	}

	public void deleteStudent(String sno) {
		// 删除成绩
		dao.delete("DELETE FROM score WHERE sno =? ", new Object[] { sno });
		// 删除学生
		dao.delete("DELETE FROM student WHERE sno =? ", new Object[] { sno });

	}

	public void editStudent(StudentInfo stuinfo, String sno) {
		Class class1 = getClass(stuinfo.getClname());

		String clno = class1.getClno();
		String uid = sno;

		Student stu = new Student();
		stu.setClno(clno);

		List<Object> params = new LinkedList<>();
		params.add(stuinfo.getSno());
		params.add(stuinfo.getSname());
		params.add(stuinfo.getSsex());
		params.add(stuinfo.getSbirthday());
		params.add(stuinfo.getSid());
		params.add(stuinfo.getSnation());
		params.add(stu.getClno());
		params.add(uid);

		String sql = "update student "
				+ "set sno = ?,sname = ?,ssex =?,sbirthday = ?,sid=?,snation=?,clno=? "
				+ "where sno = ?";

		// 更新学生信息
		dao.update(sql, params);

	}

	public String getStudentList(Student student, String sno, String sname, String clname, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM studentinfo ");

		// 参数
		List<Object> param = new LinkedList<>();

		//System.out.println(sno + "+" + sname + "+" + clname);

		if (student != null) {
			if ((sno != null) && (sno != "")) {// 条件查询
				param.add(sno);
				sb.append("AND sno=? ");
			} else if ((sname != null) && (sname != "")) {
				param.add(sname);
				sb.append("AND sname=? ");
			} else if ((clname != null) && (clname != "")) {
				param.add(clname);
				sb.append("AND clname=? ");
			}
		}

		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}

		String sql = sb.toString().replace("AND", "WHERE");

		//System.out.println("全部查询：" + sql);

		// 获取数据
		List<StudentInfo> list = dao.getStudentInfoList(sql, param);
		// 获取总记录数
		long total = getCount(sno, sname, clname, student);
		
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

}
