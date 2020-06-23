package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import dao.impl.BaseDaoImpl;
import com.mysql.jdbc.ResultSetMetaData;

import dao.TeacherDao;
import model.Class;
import model.Course;
import model.Teacher;
import utils.MysqlTool;

/**
 * 教师数据层
 *
 */

public class TeacherDaoImpl extends BaseDaoImpl implements TeacherDao {

	public List<Teacher> getTeacherList(String sql, Object[] param, Class clno) {
		//数据集合
		List<Teacher> list = new LinkedList<>();
		try {
			//获取数据库连接
			Connection conn = MysqlTool.getConnection();
			//预编译
			PreparedStatement ps = conn.prepareStatement(sql);
			//设置参数
			if(param != null && param.length > 0){
				for(int i = 0;i < param.length;i++){
					ps.setObject(i+1, param[i]);
				}
			}
			//执行sql语句
			ResultSet rs = ps.executeQuery();
			//获取元数据
			java.sql.ResultSetMetaData meta = rs.getMetaData();
			//遍历结果集
			while(rs.next()){
				//创建对象
				Teacher teacher = new Teacher();
				//遍历每个字段
				for(int i=1;i <= meta.getColumnCount();i++){
					String field = meta.getColumnName(i);
					BeanUtils.setProperty(teacher, field, rs.getObject(field));
				}
				
				List<Object> itemParam = new LinkedList<>();
				StringBuffer itemSql = new StringBuffer("SELECT * FROM Teacher WHERE Tno=? ");
				itemParam.add(teacher.getTno());
				
				if(clno != null){
					itemSql.append(" AND clazzid=?");
					itemParam.add(clno.getClno());
				}
				
				List<Object> objList = getList(CourseItem.class, itemSql.toString(), itemParam);
				List<CourseItem> itemList = new LinkedList<>();
				for(Object obj : objList){
					CourseItem item = (CourseItem) obj;
					//查询班级
					String clno = (String) getObject(Class.class, "SELECT * FROM class WHERE Clno=?", new Object[]{item.getClno()});
					Course course = (Course) getObject(Course.class, "SELECT * FROM course WHERE id=?", new Object[]{item.getCourseid()});
					
					item.setClazz(clno);
					item.setCourse(course);
					
					itemList.add(item);
				}
				//添加
				teacher.setCourseList(itemList);
				//添加到集合
				list.add(teacher);
			}
			//关闭连接
			MysqlTool.closeConnection();
			MysqlTool.close(ps);
			MysqlTool.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Teacher> getTeacherList(String sql, Object[] param, java.lang.Class clno) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
