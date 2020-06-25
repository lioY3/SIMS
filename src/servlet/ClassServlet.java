package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import net.sf.json.JSONObject;
import service.ClassService;
import utils.StringTool;

public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ClassService service = new ClassService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("toClassListView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("view/course/Cou-Infor.jsp").forward(request, response);
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
			deleteClass(request, response);
		}
		
	}
	
	private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String clno = request.getParameter("clno");
		JSONObject result = new JSONObject();
		try {
			service.deleteClass(clno);
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

	private void addClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String clname = request.getParameter("clname");
		String Clno = request.getParameter("Clno");
		service.addClass(clname, Clno);
		response.getWriter().write("success");
	}

	private void classDetailList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取参数
		String clno = request.getParameter("clno");
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String result = service.getClazzDetailList(clno, new Page(page, rows));
		//返回数据
        response.getWriter().write(result);
	}

	private void classList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取参数
		String Clno = request.getParameter("Clno");
		
		if(StringTool.isEmpty(Clno)){
			return;
		}
		String result = service.getClassList(Clno);
		//返回数据
        response.getWriter().write(result);
	}

}
