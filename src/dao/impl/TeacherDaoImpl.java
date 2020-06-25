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
import utils.DBUtil;

/**
 * 教师数据层
 *
 */

public class TeacherDaoImpl extends BaseDaoImpl implements TeacherDao {

	public List<Teacher> getTeacherList(String sql, List<Object> param) {
		//数据集合
		List<Teacher> list = new LinkedList<>();
		try {
			//获取数据库连接
			Connection conn = DBUtil.getConnection();
			//预编译
			PreparedStatement ps = conn.prepareStatement(sql);
			//设置参数
			if(param != null && param.size() > 0){
				for(int i = 0;i < param.size();i++){
					ps.setObject(i+1, param.get(i));
				}
			}
			//执行sql语句
			ResultSet rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			//遍历结果集
			while(rs.next()){
				//创建对象
				Teacher teacher = new Teacher();
				//遍历每个字段
				for(int i=1;i <= meta.getColumnCount();i++){
					String field = meta.getColumnName(i);
					
					System.out.println(field+" "+rs.getObject(field));

					BeanUtils.setProperty(teacher, field, rs.getObject(field));
				}
				
				List<Object> itemParam = new LinkedList<>();
				StringBuffer itemSql = new StringBuffer("SELECT * FROM clazz_course_teacher WHERE teacherid=? ");
				itemParam.add(teacher.getTno());
				
				
				List<Object> objList = getList(Course.class, itemSql.toString(), itemParam);
				List<Course> itemList = new LinkedList<>();
				for(Object obj : objList){
					Course item = (Course) obj;
					//查询班级
					Class sclass = (Class) getObject(Class.class, "SELECT * FROM class WHERE Clno=?", new Object[]{item.getCls()});
					
					item.setCls(sclass);
					
					itemList.add(item);
				}
				//添加
				teacher.setCourseList(itemList);
				//添加到集合
				list.add(teacher);
			}
			//关闭连接
			DBUtil.closeConnection();
			DBUtil.close(ps);
			DBUtil.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
