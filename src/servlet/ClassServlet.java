package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import service.ClassService;
import utils.StringTool;

public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ClassService service = new ClassService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("toClassListView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("/course/Cou-Infor.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if("ClassList".equalsIgnoreCase(method)){ //获取所有班级
			classList(request, response);
		} else if("ClassDetailList".equalsIgnoreCase(method)){ //获取所有班级详细信息
			classDetailList(request, response);
		} else if("AddClass".equalsIgnoreCase(method)){ //添加班级
			addClass(request, response);
		} else if("DeleteClass".equalsIgnoreCase(method)){ //删除班级
			deleteClazz(request, response);
		}
		
	}
	
	private void deleteClazz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Clno = request.getParameter("Clno");
		try {
			service.deleteClazz(Clno);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}

	private void addClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String clname = request.getParameter("clname");
		String Clno = request.getParameter("Clno");
		service.addClass(clname, Clno);
		response.getWriter().write("success");
	}

	private void classDetailList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取参数
		String Clno = request.getParameter("Clno");
	
		String result = service.getClazzDetailList(Clno);
		//返回数据
        response.getWriter().write(result);
	}

	private void classList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取参数
		String Clno = request.getParameter("Clno");
		
		if(StringTool.isEmpty(Clno)){
			return;
		}
		String result = service.getClazzList(Clno);
		//返回数据
        response.getWriter().write(result);
	}

}
