package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBUtil;

/**
 * Servlet implementation class Selectcourse
 */
@WebServlet("/Selectcourse")//课程信息查询
public class Selectcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Selectcourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
	   // response.setContentType("text/javascript");
	    HttpSession session=request.getSession();
		
		String Cno = request.getParameter("Cno");
	
			ResultSet rs;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (Exception e) {
				out.println("缺少数据库驱动程序 ");
			}
			try {
				Connection con = DBUtil.getConnection();
				Statement stmt=con.createStatement();
				 String sql="select * from Course where Cno="+Cno+"";
				rs=stmt.executeQuery(sql);
					out.print(
			"<table border='1' cellpadding='3' cellspacing='0' style='width:700px; align:center;margin:auto'>");
	out.print("<tr>");
	out.print("<th width=200>" + "课程号号");
	out.print("<th width=200>" + "课程名");
	out.print("<th width=200>" + "学分");
	out.print("<th width=250>" + "学期");
	out.print("<th width=150>" + "学时");
	out.print("<th width=250>" + "教师编号");
	out.print("</TR>");

	while (rs.next()) {
		out.print("<tr>");
		out.print("<td >" + rs.getString(1) + "</td>");
		out.print("<td >" + rs.getString(2) + "</td>");
		out.print("<td >" + rs.getString(3) + "</td>");
		out.print("<td >" + rs.getString(4) + "</td>");
		out.print("<td >" + rs.getString(5) + "</td>");
		out.print("<td >" + rs.getString(6) + "</td>");
		out.print("</tr>");
	}
	out.print("</table>");								
					
								
							
			}catch (SQLException e) {
				out.print(e);
			}
		
		}	
	}


