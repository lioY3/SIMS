package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import service.StudentService;
import utils.StringTool;



/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentService service = new StudentService();
	
    public StudentServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if ("toStudentInfoView".equals(method)) {
			request.getRequestDispatcher("/student/Stu-Infor.jsp").forward(request, response);
		} else if ("toStudentModifyView".equals(method)) {
			request.getRequestDispatcher("/student/Stu-modify.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		//请求分发
		if("StudentList".equals(method)){ //获取所有学生数据
			studentList(request, response);
		}
		
		doGet(request, response);
	}


	private void studentList(HttpServletRequest request, HttpServletResponse response) {
		//年级ID
		String gradeid = request.getParameter("gradeid");
		//班级ID
		String clazzid = request.getParameter("clazzid");
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//封装参数
		Student student = new Student();
		
		if(!StringTool.isEmpty(gradeid)){
			student.setGradeid(Integer.parseInt(gradeid));
		}
		if(!StringTool.isEmpty(clazzid)){
			student.setClazzid(Integer.parseInt(clazzid));
		}
		
		//获取数据
		String result = service.getStudentList(student, new Page(page, rows));
		//返回数据
        response.getWriter().write(result);
		
		
	}

}
