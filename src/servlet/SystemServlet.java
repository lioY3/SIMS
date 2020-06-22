package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SystemServlet
 */
@WebServlet("/SystemServlet")
public class SystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SystemServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("LoginOut".equals(method)) { // 退出系统
			loginOut(request, response);
		} else if ("toAdminView".equals(method)) { // 到管理员界面
			request.getRequestDispatcher("/console/administrator-layout.jsp").forward(request, response);
		} else if ("toTeacherView".equals(method)) { // 到教师界面
			request.getRequestDispatcher("/console/teacher-layout.jsp").forward(request, response);
		} 
	}


	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 退出系统时清除系统登录的用户
		request.getSession().removeAttribute("user");
		String contextPath = request.getContextPath();
		// 转发到登录界面
		response.sendRedirect(contextPath + "/index.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
