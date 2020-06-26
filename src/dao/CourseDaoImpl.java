package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import model.Course;
import utils.DBUtil;

public class CourseDaoImpl extends BaseDaoImpl implements CourseDao {

	@Override
	public List<Course> getallCourseList(String sql, List<Object> param) {
		List<Course> list = new LinkedList<>();
		try {
			// 获取数据库连接
			Connection con = DBUtil.getConnection();
			// 预编译
			PreparedStatement ps = con.prepareStatement(sql);
			// 设置参数
			if (param != null && param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					ps.setObject(i + 1, param.get(i));
				}
			}
			// 执行SQL语句
			ResultSet rs = ps.executeQuery();

			// 获取元数据
			ResultSetMetaData meta = rs.getMetaData();
			// 遍历结果集
			while (rs.next()) {
				// 创建对象
				Course course = new Course();
				// 遍历每个字段
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					String field = meta.getColumnName(i);

					// System.out.println(field+" "+rs.getObject(field));

					BeanUtils.setProperty(course, field, rs.getObject(field));
				}

				// 添加到集合
				list.add(course);
			}

			// 关闭连接
			DBUtil.closeConnection();
			DBUtil.close(ps);
			DBUtil.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
