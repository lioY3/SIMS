package service;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import dao.ScoreDao;
import dao.impl.ScoreDaoImpl;
import model.Course;
import model.Score;
import net.sf.json.JSONArray;
import utils.ExcelTool;

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
	 * 获取成绩列表
	 * @param exam 参数
	 * @return
	 */
	public String getScoreList(Score score) {
		
		List<Map<String, Object>> list = dao.getScoreList(score);
        //格式化Map,以json格式返回数据
        String result = JSONArray.fromObject(list).toString();
        //返回
		return result;
	}
	
	/**
	 * 获取记录数
	 * @param score
	 * @return
	 */
	private long getCount(Score score){
		//sql语句
		StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM score ");
		//参数
		List<Object> param = new LinkedList<>();
		String sql = sb.toString().replaceFirst("AND", "WHERE");
		
		long count = dao.count(sql, param).intValue();
		
		return count;
	}
	

	/**
	 * 获取数据栏的列名
	 * @param exam
	 * @return
	 */
	public String columnList(Score score) {
		List<Object> list = getColumn(score);
		
		return JSONArray.fromObject(list).toString();
	}
	
	private List<Object> getColumn(Score score){
		List<Object> list = null;
			//获取某科
			list =  dao.getList(Course.class, 
					"SELECT * FROM course WHERE cno=?", new Object[]{score.getCno()});
			
		
		return list;
	}
	

	/**
	 * 导出成绩列表
	 * @param response
	 * @param exam
	 */
	@SuppressWarnings("unchecked")
	public void exportScore(HttpServletResponse response, Score score) {
		//获取需要导出的数据
		List<Map<String, Object>> list = dao.getScoreList(score);
		//获取考试信息
		Score sc = (Score) dao.getObject(Score.class, "SELECT Cname, time FROM score WHERE cno=?", new Object[]{score.getCno()});
		//设置文件名
		String fileName = sc.getCname()+".xls";
		//定义输出类型
		response.setContentType("application/msexcel;charset=utf-8");
		//设定输出文件头
		try {
			response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		//获取导出的课程
		List<Object> courseList = getColumn(score);
		
		//表头长度
		int len = 2 + courseList.size();
		
		//设置excel的列名
		String[] headers = new String[len];
		headers[0] = "姓名";
		headers[1] = "学号";
		
		int index = 2;
		for(Object obj : courseList){
			Course course = (Course) obj;
			headers[index++] = course.getCname();
		}
		
		
		@SuppressWarnings("rawtypes")
		ExcelTool et = new ExcelTool<>();
		//导出
		try {
			et.exportMapExcel(headers, list, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置成绩
	 * @param score id_score 形式
	 */
	public void setScore(String[] score) {
		Object[][] param = new Object[score.length][2];
		
		for(int i = 0;i < score.length;i++){
			String[] id_score = score[i].split("_");
			int id = Integer.parseInt(id_score[0]);
			param[i][1] = id;
			if(id_score.length == 1){
				param[i][0] = 0;
			} else {
				int sco = Integer.parseInt(id_score[1]);
				param[i][0] = sco;
			}
		}
		
		dao.updateBatch("UPDATE escore SET score=? WHERE id=?", param);
		
	}
	
}
