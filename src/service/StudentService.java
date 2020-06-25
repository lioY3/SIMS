package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.ClassDao;
import dao.StudentDao;
import dao.impl.ClassDaoImpl;
import dao.impl.StudentDaoImpl;
import model.Page;
import model.Student;
import model.Class;
import model.StudentInfo;
import net.sf.json.JSONObject;

public class StudentService {

	StudentDao dao = new StudentDaoImpl();

	public String getStudentList(Student student, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM studentinfo ");
		
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
		List<StudentInfo> list = dao.getStudentInfoList(sql, param);
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

	public void addStudent(StudentInfo stuinfo) {
		Class class1 = getClass(stuinfo.getClname());
		
		String clno = class1.getClno();
		
		Student stu = new Student();
		stu.setClno(clno);
		
		//添加学生记录
				dao.insert("INSERT INTO student(sno, sname, ssex, sbirthday, sid, snation, clno) value(?,?,?,?,?,?,?)", 
						new Object[]{
							stuinfo.getSno(), 
							stuinfo.getSname(), 
							stuinfo.getSsex(), 
							stuinfo.getSbirthday(),
							stuinfo.getSid(),
							stuinfo.getSnation(),
							stu.getClno()
						});

	}
	
	/**
	 * 获取班级详细信息
	 * @param account
	 * @return
	 */
	public Class getClass(String clname) {
		
		ClassDao dao = new ClassDaoImpl();
		
		Class class1 = (Class)dao.getObject(Class.class,"SELECT * FROM class WHERE clname=?", new Object[]{clname});		
		
		return class1;
	}

	public void deleteStudent(String sno) {
		//删除成绩
		dao.delete("DELETE FROM score WHERE sno =? ", new Object[]{sno});
		//删除学生
		dao.delete("DELETE FROM student WHERE sno =? ", new Object[]{sno});
		
	}

	public void editStudent(StudentInfo stuinfo,String sno) {
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
		
		//更新学生信息
		dao.update(sql, params);

		
	}
	
}
