package servlet;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import model.Page;
import model.Teacher;
import model.User;
import net.sf.json.JSONObject;
import service.TeacherService;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//创建服务层对象
	private TeacherService service = new TeacherService();
	
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
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Teacher teacher = new Teacher();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				if("course[]".equals(pName)){//设置所选课程
					BeanUtils.setProperty(teacher, "course", request.getParameterValues("course[]"));
				} else{
					BeanUtils.setProperty(teacher, pName, value);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			service.editTeacher(teacher);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取要删除的教师编号
		String[] ids = request.getParameterValues("ids[]");
		String[] numbers = request.getParameterValues("numbers[]");
		try {
			service.deleteTeacher(ids, numbers);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取参数名
		Enumeration<String> pNames = request.getParameterNames();
		Teacher teacher = new Teacher();
		while(pNames.hasMoreElements()){
			String pName = pNames.nextElement();
			String value = request.getParameter(pName);
			try {
				if("course[]".equals(pName)){//设置所选课程
					BeanUtils.setProperty(teacher, "course", request.getParameterValues("course[]"));
				} else{
					BeanUtils.setProperty(teacher, pName, value);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			service.addTeacher(teacher);
			response.getWriter().write("success");
		} catch (Exception e) {
			response.getWriter().write("fail");
			e.printStackTrace();
		}
	}

	private void teacherList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//获取数据
		String result = service.getTeacherList(new Page(page, rows));
		//返回数据
        response.getWriter().write(result);
	}
	
}
