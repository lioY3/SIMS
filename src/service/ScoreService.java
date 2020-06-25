package service;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dao.ScoreDao;
import dao.impl.ScoreDaoImpl;
import model.Page;
import model.Score;
import model.ScoreInfo;
import net.sf.json.JSONObject;

/**
 * 成绩类服务层
 *
 */

public class ScoreService {

	private ScoreDao dao;
	
	public ScoreService(){
		dao = new ScoreDaoImpl();
	}
	
	/**
	 * 获取记录数
	 * @param tno 
	 */
	private long getCount(String sno, String cname, Score score, String tno){
		//sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM scoreinfo join course on course.cno = scoreinfo.cno ");
		//参数
		List<Object> param = new LinkedList<>();
		
		
		if ((tno != null) && (tno != "")) {
			param.add(tno);
			sb.append("AND tno=? ");

			if ((sno != null) && (sno != "")) {// 条件查询
				param.add(sno);
				sb.append("AND sno=? ");
			} else if ((cname != null) && (cname != "")) {
				param.add(cname);
				sb.append("AND cname=? ");
			}
		}
		
		String sql = sb.toString().replaceFirst("AND", "WHERE");
		
		System.out.println(sql);
		
		long count = dao.count(sql, param);

		// System.out.println("条件查询：" + sql);
		// System.out.println(count);

		return count;
	}

	public String getScoreList(Score score, String sno, String cname, String tno, Page page) {
		// sql语句
		StringBuffer sb = new StringBuffer("SELECT * FROM scoreinfo join course on course.cno = scoreinfo.cno ");

		// 参数
		List<Object> param = new LinkedList<>();

		//System.out.println(sno + "+" + sname + "+" + clname);
		

		if (score != null) {
			if ((tno != null) && (tno != "")) {
				param.add(tno);
				sb.append("AND tno=? ");

				if ((sno != null) && (sno != "")) {// 条件查询
					param.add(sno);
					sb.append("AND sno=? ");
				} else if ((cname != null) && (cname != "")) {
					param.add(cname);
					sb.append("AND cname=? ");
				}
			}
		}
		
		// 分页
		if (page != null) {
			param.add(page.getStart());
			param.add(page.getSize());
			sb.append("limit ?,?");
		}
		
		String sql = sb.toString().replaceFirst("AND", "WHERE");
		
		System.out.println("service:"+tno);
		System.out.println(sql);
		
		List<ScoreInfo> list = dao.getScoreInfoList(sql, param);
		// 获取总记录数
		long total = getCount(sno, cname, score, tno);
		
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

	public void deleteScore(String sno, String cno) {
		// 删除成绩
		dao.delete("DELETE FROM score WHERE sno = ? and cno = ?", new Object[] { sno,cno });
	}

	public void addScore(Score score) {
		
		// 添加成绩
		dao.insert("INSERT INTO score(sno, cno, grade value(?,?,?)",
				new Object[] { score.getSno(), score.getCno(), score.getGrade()});		
		
	}

	public void editScore(Score score, String sno, String cno) {
		
		List<Object> params = new LinkedList<>();
		params.add(score.getGrade());
		params.add(score.getSno());
		params.add(score.getCno());

		String sql = "update score "
				+ "set grade = ? "
				+ "where sno = ? and cno = ?";

		// 更新学生信息
		dao.update(sql, params);
		
	}
		
}
