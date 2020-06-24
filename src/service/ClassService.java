package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ClassDao;
import dao.impl.ClassDaoImpl;
import model.Page;
import model.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import utils.DBUtil;
import utils.StringTool;

/**
 * 年级服务层
 *
 */

public class ClassService {
	
	ClassDao dao = new ClassDaoImpl();
	
	/**
	 * 获取指定的班级
	 * @param gid 年级ID
	 * @return JSON格式的班级
	 */
	public String getClazzList(String gradeid){
		int id = Integer.parseInt(gradeid);
		//获取数据
		List<Object> list = dao.getList(Class.class, "SELECT * FROM class WHERE clno=?", new Object[]{id});
		//json化
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"clno", "studentList"});
        String result = JSONArray.fromObject(list, config).toString();
        
        return result;
	}
	
	/**
	 * 获取班级详细信息
	 * @param gradeid
	 * @param page
	 * @return
	 */
	public String getClazzDetailList(String clno, Page page) {
		//获取数据
		@SuppressWarnings("rawtypes")
		List<Class> list = dao.getClazzDetailList(clno,page);
		//获取总记录数
		long total = 0;
		if(!StringTool.isEmpty(clno)){
			//int gid = Integer.parseInt(clno);
			total = dao.count("SELECT COUNT(*) FROM class WHERE clno=?", new Object[]{clno});
		} else {
			total = dao.count("SELECT COUNT(*) FROM class", new Object[]{});
		}
		//定义Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();  
		//total键 存放总记录数，必须的
        jsonMap.put("total", total);
        //rows键 存放每页记录 list 
        jsonMap.put("rows", list); 
        //格式化Map,以json格式返回数据
        String result = JSONObject.fromObject(jsonMap).toString();
        
        return result;
	}

	/**
	 * 添加班级
	 * @param name
	 * @param gradeid
	 */
	public void addClass(String clname, String clno) {
		dao.insert("INSERT INTO class(clname, clno) value(?,?)", new Object[]{clname, clno});
	}
	
	/**
	 * 删除班级
	 * @param clazzid
	 * @throws Exception 
	 */
	public void deleteClazz(String clno) throws Exception {
		//获取连接
		Connection conn = DBUtil.getConnection();
		try {
			//开启事务
			DBUtil.startTransaction();
			//删除成绩表
			dao.deleteTransaction(conn, "DELETE FROM score WHERE clno=?", new Object[]{clno});
			//删除考试记录
//			dao.deleteTransaction(conn, "DELETE FROM exam WHERE clazzid=?", new Object[]{clazzid});
			//删除用户
			List<Object> list = dao.getList(Student.class, "SELECT number FROM student WHERE clno=?",  new Object[]{clno});
			if(list.size() > 0){
				Object[] param = new Object[list.size()];
				for(int i = 0;i < list.size();i++){
					Student stu = (Student) list.get(i);
					param[i] = stu.getSno();
				}
				String sql = "DELETE FROM user WHERE sno IN ("+StringTool.getMark(list.size())+")";
				dao.deleteTransaction(conn, sql, param);
				//删除学生
				dao.deleteTransaction(conn, "DELETE FROM student WHERE clno=?", new Object[]{clno});
			}
			//删除班级的课程和老师的关联
			dao.deleteTransaction(conn, "DELETE FROM class_course_teacher WHERE clno=?", new Object[]{clno});
			//最后删除班级
			dao.deleteTransaction(conn, "DELETE FROM class WHERE clno=?",  new Object[]{clno});
			
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
