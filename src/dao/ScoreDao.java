package dao;

import java.util.List;
import java.util.Map;
import dao.BaseDao;
import model.Score;


/**
 * 操作成绩的数据层接口
 *
 */

public interface ScoreDao extends BaseDao {

	/**
	 * 获取学生成绩表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getScoreList(Score score);

}
