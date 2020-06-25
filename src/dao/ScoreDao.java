package dao;

import java.util.List;
import dao.BaseDao;
import model.ScoreInfo;


/**
 * 操作成绩的数据层接口
 *
 */

public interface ScoreDao extends BaseDao {

	/**
	 * 获取学生成绩表
	 */
	List<ScoreInfo> getScoreInfoList(String sql, List<Object> param);

}
