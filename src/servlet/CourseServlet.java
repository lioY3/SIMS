package servlet;

import java.io.IOException;



import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CourseService;
import model.Course;
import model.User;



public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private CourseService service = new CourseService();
	public CourseServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("toCourseListView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("/course/Cou-Infor.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if("CourseList".equalsIgnoreCase(method)){ //获取所有课程
			courseList(request, response);
		} else if("AddCourse".equalsIgnoreCase(method)){ //添加课程
			addCourse(request, response);
		} else if("deleteCourse".equalsIgnoreCase(method)){ //删除课程
			deleteCourse(request, response);
		}
		
	}
	
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int Cno = Integer.parseInt(request.getParameter("Cno"));
		try {
			service.deleteCourse(Cno);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		Course course = new Course();
		course.setCname(name);
		service.addCourse(course);
		response.getWriter().write("success");
	}
	
	private void courseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Cno = request.getParameter("Cno");
		
		String result = service.getCourseList(Cno);
		//返回数据
        response.getWriter().write(result);
	}

}
