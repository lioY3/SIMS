package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import model.ScoreInfo;
import utils.DBUtil;

public class ScoreDaoImpl extends BaseDaoImpl implements ScoreDao {

	public List<ScoreInfo> getScoreInfoList(String sql, List<Object> param) {
		// 数据集合
		List<ScoreInfo> list = new LinkedList<>();
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
				ScoreInfo scoreInfo = new ScoreInfo();
				// 遍历每个字段
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					String field = meta.getColumnName(i);

					// System.out.println(field+" "+rs.getObject(field));

					BeanUtils.setProperty(scoreInfo, field, rs.getObject(field));
				}

				// 添加到集合
				list.add(scoreInfo);
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
