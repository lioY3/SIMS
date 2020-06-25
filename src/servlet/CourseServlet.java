package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CourseService;
import utils.GetRequestJsonUtils;
import model.Course;
import model.Page;
import net.sf.json.JSONObject;


@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private CourseService service = new CourseService();
	
	public CourseServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("toCourseInfoView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("view/course/Cour-info.jsp").forward(request, response);
		} else if("toCourseModifyView".equalsIgnoreCase(method)){ //转发到课程修改页
			request.getRequestDispatcher("view/course/Cour-modify.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if("CourseList".equalsIgnoreCase(method)){ //获取所有课程
			courseList(request, response);
		} else if("AddCourse".equalsIgnoreCase(method)){ //添加课程
			addCourse(request, response);
		} else if("DeleteCourse".equalsIgnoreCase(method)){ //删除课程
			deleteCourse(request, response);
		}else if("EditCourse".equalsIgnoreCase(method)){ //修改课程
			editCourse(request, response);
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
		course.setCredit(json.getInt("credit"));
		course.setTerm(json.getString("term"));
		course.setHours(json.getString("hours"));
		course.setTno(json.getString("tno"));

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
		String cno = request.getParameter("key[Cno]");
		String cname = request.getParameter("key[Cname]");
		Course course = new Course();

		// 获取数据
		String result = service.courseList(course, cno,cname,new Page(page, limit));

		// 返回数据
		response.getWriter().write(result);

	}
	private void editCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
		String request_cno = json.getString("request_cno");
		
		System.out.println(request_cno);
		
		Course course = new Course();
		
		//course.setCno(json.getString("cno"));
		course.setCname(json.getString("cname"));
		course.setCredit(json.getInt("credit"));
		course.setTerm(json.getString("term"));
		course.setHours(json.getString("hours"));
		course.setTno(json.getString("tno"));

		JSONObject result = new JSONObject();
		
		try {
			service.editCourse(course,request_cno);
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


}
