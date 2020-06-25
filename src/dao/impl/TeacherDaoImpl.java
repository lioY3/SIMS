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
import model.StudentInfo;
import model.Teacher;
import model.TeacherInfo;
import utils.DBUtil;

/**
 * 教师数据层
 *
 */

public class TeacherDaoImpl extends BaseDaoImpl implements TeacherDao {

	public List<TeacherInfo> getTeacherList(String sql, List<Object> param) {
		//数据集合
		List<TeacherInfo> list = new LinkedList<>();
		try {
			//获取数据库连接
			Connection con = DBUtil.getConnection();
			//预编译
			PreparedStatement ps = con.prepareStatement(sql);
			//设置参数
			if(param != null && param.size() > 0){
				for(int i = 0;i < param.size();i++){
					ps.setObject(i+1, param.get(i));
				}
			}
			//执行SQL语句
			ResultSet rs = ps.executeQuery();
			
			
			//获取元数据
			java.sql.ResultSetMetaData meta = rs.getMetaData();
			//遍历结果集
			while(rs.next()){
				//创建对象
				TeacherInfo teainfo = new TeacherInfo();
				//遍历每个字段
				for(int i=1;i <= meta.getColumnCount();i++){
					String field = meta.getColumnName(i);
					
					//System.out.println(field+" "+rs.getObject(field));
					
					BeanUtils.setProperty(teainfo, field, rs.getObject(field));
				}
				
				//添加到集合
				list.add(teainfo);
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
