package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.Teacher;
import net.sf.json.JSONObject;
import service.TeacherService;
import utils.GetRequestJsonUtils;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//创建服务层对象
	private TeacherService service = new TeacherService();
	
	public TeacherServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if("toTeacherInfoView".equalsIgnoreCase(method)){ //转发到教师信息查询页面
			request.getRequestDispatcher("view/teacher/Tea-Infor.jsp").forward(request, response);
		} else if("toTeacherNoteListView".equalsIgnoreCase(method)){ //转发到教师信息修改页面
			request.getRequestDispatcher("view/teacher/Tea-modify.jsp").forward(request, response);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		//请求分发
		if("TeacherList".equalsIgnoreCase(method)){ //获取所有教师数据
			teacherList(request, response);
		} else if("AddTeacher".equalsIgnoreCase(method)){ //添加教师
			addTeacher(request, response);
		} else if("DeleteTeacher".equalsIgnoreCase(method)){ //删除教师
			deleteTeacher(request, response);
		}
		
	}
	

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String tno = request.getParameter("tno");

		JSONObject result = new JSONObject();
		try {
			service.deleteTeacher(tno);
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


	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);

		Teacher teacher = new Teacher();
		
		teacher.setTno(json.getString("tno"));
		teacher.setTname(json.getString("tname"));
		teacher.setTsex(json.getString("tsex"));

		JSONObject result = new JSONObject();

		try {
			service.addTeacher(teacher);
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


	private void teacherList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		// 条件查询参数
		String tname = request.getParameter("key[tname]");
		String tno = request.getParameter("key[tno]");
		String cname = request.getParameter("key[cname]");
		
		//System.out.println(tname+" "+tno+" "+cname);


		Teacher teacher = new Teacher();

		String result = service.getTeacherList(teacher, tname, tno, cname, new Page(page, limit));
		response.getWriter().write(result);

	}
	
}
