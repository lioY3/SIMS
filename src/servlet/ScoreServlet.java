package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ScoreService;
import utils.GetRequestJsonUtils;
import model.Page;
import model.Score;
import model.StudentInfo;
import net.sf.json.JSONObject;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//创建服务层对象
	private ScoreService service = new ScoreService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		//请求分发
		if("toScoreInfoView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("view/score/score-info.jsp").forward(request, response);
		} else if ("toScoreModifyView".equals(method)) {
			request.getRequestDispatcher("view/score/score-modify.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		//请求分发
		if("ScoreList".equalsIgnoreCase(method)){ //获取所有成绩数据
			scoreList(request, response);
		} else if ("AddScore".equalsIgnoreCase(method)) { // 录入成绩
			addScore(request, response);
		} else if ("DeleteScore".equalsIgnoreCase(method)) { // 删除成绩
			deleteScore(request, response);
		} else if ("EditScore".equalsIgnoreCase(method)) { // 修改成绩
			editScore(request, response);
		}
	}

	private void editScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);

		String sno = json.getString("request_Sno");
		String cno = json.getString("request_Cno");

		Score score = new Score();

		score.setGrade(json.getDouble("grade"));


		JSONObject result = new JSONObject();
		try {
			service.editScore(score, sno, cno);
			result.put("code", "0");
			result.put("msg", "增加成功！");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
		} catch (Exception e) {
			result.put("code", "1");
			result.put("msg", "增加失败");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
			e.printStackTrace();
		}
		
	}

	private void deleteScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");

		System.out.println(sno);
		System.out.println(cno);
				
		JSONObject result = new JSONObject();
		try {
			service.deleteScore(sno,cno);
			result.put("msg", "删除成功！");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
		} catch (Exception e) {
			result.put("msg", "删除失败！");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
			e.printStackTrace();
		}
		
	}

	private void addScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);

		Score score = new Score();

		score.setSno(json.getString("sno"));
		score.setCno(json.getString("cno"));
		score.setGrade(json.getDouble("grade"));

		JSONObject result = new JSONObject();

		try {
			service.addScore(score);
			result.put("code", "0");
			result.put("msg", "增加成功！");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
		} catch (Exception e) {
			result.put("code", "1");
			result.put("msg", "增加失败");
			String status = JSONObject.fromObject(result).toString();
			response.getWriter().write(status);
			e.printStackTrace();
		}
		
	}

	private void scoreList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		// 条件查询参数
		String sno = request.getParameter("key[Sno]");
		String cname = request.getParameter("key[Cname]");
		String tno = request.getParameter("tno");
		System.out.println("servlet:"+tno);

		//System.out.println("key[Sno]:" + sno);

		Score score = new Score();

		String result = service.getScoreList(score, sno, cname, tno, new Page(page, limit));
		response.getWriter().write(result);
		
	}
	
}
