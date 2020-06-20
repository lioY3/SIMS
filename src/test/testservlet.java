package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testservlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("tosudentview".equalsIgnoreCase(method)){ //转发到学生列表页
			request.getRequestDispatcher("WebContent/WEB-INF/student/Stu-Infor.jsp").forward(request, response);
		}
	}
}
