package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ScoreService;
import model.Page;
import model.Score;
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

	private void editScore(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteScore(HttpServletRequest request, HttpServletResponse response) {
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");

		System.out.println(sno);
		System.out.println(cno);
		
	}

	private void addScore(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
