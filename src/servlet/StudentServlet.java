package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import model.Student;
import service.StudentService;


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
			request.getRequestDispatcher("student/Stu-Infor.jsp").forward(request, response);
		} else if ("toStudentModifyView".equals(method)) {
			request.getRequestDispatcher("student/Stu-modify.jsp").forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		//请求分发
		if("StudentList".equals(method)){ //获取所有学生数据
			studentList(request, response);
		}
		
	}


	private void studentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取分页参数
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//封装参数
		Student student = new Student();
		
		//获取数据
		String result = service.getStudentList(student, new Page(page, limit));

		//返回数据
        response.getWriter().write(result);
		
	}

}
