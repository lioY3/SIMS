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
import utils.GetRequestJsonUtils;
import model.Course;
import model.Page;
import model.Student;
import model.StudentInfo;
import model.User;
import net.sf.json.JSONObject;



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
			request.getRequestDispatcher("view/course/Cou-Infor.jsp").forward(request, response);
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

		String cno = request.getParameter("cno");
		
		JSONObject result = new JSONObject();
		try {
			service.deleteCourse(cno);
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

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
		
		Course course = new Course();
		
		course.setCno(json.getString("cno"));
		course.setCname(json.getString("cname"));
//		course.setCredit(json.getString("sbirthday"));
//		stuinfo.setSid(json.getString("sid"));
//		stuinfo.setSname(json.getString("sname"));
//		stuinfo.setSnation(json.getString("snation"));
//		stuinfo.setSno(json.getString("sno"));
//		stuinfo.setSsex(json.getString("ssex"));
			
		JSONObject result = new JSONObject();
		
		try {
			service.addCourse(course);
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

	
	private void courseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		Course course = new Course();

		// 获取数据
		String result = service.getCourseList(course, new Page(page, limit));

		// 返回数据
		response.getWriter().write(result);

	}


}
