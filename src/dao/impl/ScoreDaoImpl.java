package dao.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;

import dao.ScoreDao;
import model.Score;
import model.Student;
import utils.MysqlTool;

public class ScoreDaoImpl extends BaseDaoImpl implements ScoreDao {

	public List<Map<String, Object>> getScoreList(Score score) {
		//sql语句
		List<Object> stuParam = new LinkedList<>();
		StringBuffer stuSb = new StringBuffer("SELECT Sno, Sname FROM student WHERE Clno=? ");
		stuParam.add(score.getCno());
		if(score.getCno() != null){
			stuParam.add(score.getCno());
		}
		String stuSql = stuSb.toString();
		
		//获取数据库连接
		Connection conn = (Connection) MysqlTool.getConnection();
		
		//获取该年级下的所有学生
		List<Object> stuList = getList(Student.class, stuSql, stuParam);
		
		//数据集合
		List<Map<String, Object>> list = new LinkedList<>();
		
		//sql语句
		String sql = "SELECT e.id,e.courseid,e.score FROM student s, escore e "
				+ "WHERE s.id=e.studentid AND e.examid=? AND e.studentid=?";
		
		for(int i = 0;i < stuList.size();i++){
			Map<String, Object> map = new LinkedHashMap<>();
			
			Student student = (Student) stuList.get(i);
			
			map.put("Sname", student.getSname());
			map.put("Sno", student.getSno());
			
			List<Object> scoreList = getList(conn, Score.class, sql, new Object[]{score.getCno(), student.getSno()});
			int total = 0;
			for(Object obj : scoreList){
				Score score1 = (Score) obj;
				total += score1.getGrade();
				
				//将成绩表id放入:便于获取单科成绩用于登记
				map.put("course"+score1.getCno(), score1.getGrade());
				map.put("escoreid"+score1.getCno(), score1.getGrade());
			}

			list.add(map);
		}
		return list;
	}
	

}
