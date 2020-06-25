package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Page;
import model.TeacherInfo;
import model.User;
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
		} else if("EditTeacher".equalsIgnoreCase(method)){ //修改教师信息
			editTeacher(request, response);
		} 
		
	}
	


	/**
	 * 转到个人信息页，加载个人信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */

	@SuppressWarnings("unused")
	private void getTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		String number = user.getAccount();
		String result = service.getTeacherResult(number);
		response.getWriter().write(result);
	}

	private void editTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);

		String tno = json.getString("request_Tno");

		TeacherInfo teainfo = new TeacherInfo();

		teainfo.setTname(json.getString("tname"));
		teainfo.setTsex(json.getString("tsex"));
		teainfo.setCname(json.getString("cname"));

		JSONObject result = new JSONObject();
		try {
			service.editTeacher(teainfo, tno);
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

		TeacherInfo teainfo = new TeacherInfo();
		
		teainfo.setTno(json.getString("tno"));
		teainfo.setTname(json.getString("tname"));
		teainfo.setTsex(json.getString("tsex"));
		teainfo.setCname(json.getString("cname"));

		JSONObject result = new JSONObject();

		try {
			service.addTeacher(teainfo);
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
		String tname = request.getParameter("key[Tname]");
		String tno = request.getParameter("key[Tno]");
		String cname = request.getParameter("key[Cname]");


		TeacherInfo teainfo = new TeacherInfo();

		String result = service.getTeacherList(teainfo, tname, tno, cname, new Page(page, limit));
		response.getWriter().write(result);

	}
	
}
