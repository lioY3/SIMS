package servlet;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import service.ScoreService;
import model.Score;

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
		} else if("ColumnList".equalsIgnoreCase(method)){ //获取列
			columnList(request, response);
		} else if("SetScore".equalsIgnoreCase(method)){ //登记成绩
			setScore(request, response);
		} 
		
		
		
	}
	
	private void setScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] score = request.getParameterValues("score[]");
		service.setScore(score);
		//返回数据
        response.getWriter().write("success");
	}
	
	private void columnList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Enumeration<String> pNames = request.getParameterNames();
		Score score = new Score();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(score, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		//获取数据
		String result = service.columnList(score);
		//返回数据
        response.getWriter().write(result);
	}
	
	private void scoreList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Enumeration<String> pNames = request.getParameterNames();
		Score score = new Score();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				BeanUtils.setProperty(score, pName, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		//获取数据
		String result = service.getScoreList(score);
		//返回数据
        response.getWriter().write(result);
	}
	
}
