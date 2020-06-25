package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.Student;
import model.StudentInfo;
import net.sf.json.JSONObject;
import service.StudentService;
import utils.GetRequestJsonUtils;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentService service = new StudentService();

	public StudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("toStudentInfoView".equals(method)) {
			request.getRequestDispatcher("view/student/Stu-Infor.jsp").forward(request, response);
		} else if ("toStudentModifyView".equals(method)) {
			request.getRequestDispatcher("view/student/Stu-modify.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		// 请求分发
		if ("StudentList".equals(method)) { // 获取所有学生数据
			studentList(request, response);
		} else if ("AddStudent".equalsIgnoreCase(method)) { // 添加学生
			addStudent(request, response);
		} else if ("DeleteStudent".equalsIgnoreCase(method)) { // 删除学生
			deleteStudent(request, response);
		} else if ("EditStudent".equalsIgnoreCase(method)) { // 修改学生信息
			editStudent(request, response);
		}

	}

	private void studentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		//条件查询参数
		String sno = request.getParameter("key[Sno]");
		String sname = request.getParameter("key[Sname]");
		String clname = request.getParameter("key[Clname]");
		
		System.out.println("key[Sno]:"+sno);
		
		Student student = new Student();

		// 获取数据
		String result = service.getStudentList(student, sno, sname, clname, new Page(page, limit));

		// 返回数据
		response.getWriter().write(result);

	}

	
	private void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
		
		String sno = json.getString("request_Sno");

		StudentInfo stuinfo = new StudentInfo();

		stuinfo.setClname(json.getString("clname"));
		stuinfo.setDname(json.getString("dname"));
		stuinfo.setSbirthday(json.getString("sbirthday"));
		stuinfo.setSid(json.getString("sid"));
		stuinfo.setSname(json.getString("sname"));
		stuinfo.setSnation(json.getString("snation"));
		stuinfo.setSno(json.getString("sno"));
		stuinfo.setSsex(json.getString("ssex"));
		
		JSONObject result = new JSONObject();
		try {
			service.editStudent(stuinfo,sno);
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

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sno = request.getParameter("sno");
		
		JSONObject result = new JSONObject();
		try {
			service.deleteStudent(sno);
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

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
		
		StudentInfo stuinfo = new StudentInfo();
		
		stuinfo.setClname(json.getString("clname"));
		stuinfo.setDname(json.getString("dname"));
		stuinfo.setSbirthday(json.getString("sbirthday"));
		stuinfo.setSid(json.getString("sid"));
		stuinfo.setSname(json.getString("sname"));
		stuinfo.setSnation(json.getString("snation"));
		stuinfo.setSno(json.getString("sno"));
		stuinfo.setSsex(json.getString("ssex"));
			
		JSONObject result = new JSONObject();
		
		try {
			service.addStudent(stuinfo);
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
