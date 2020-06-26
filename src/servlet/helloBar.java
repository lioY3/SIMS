package servlet;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import dao.barDAO;
import model.barBean;

@WebServlet("/hhh.do")
public class helloBar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//创建了一个bardao的对象，barDAO主要是对数据库的连接和对数据库的操作
        barDAO bardao=new barDAO();
        //调用bardao的select_all()方法把从数据库中读取所有的数据返回的是一个ArrayList，ArrayList里面放的是一个barBean
        ArrayList<barBean> array = bardao.select_all();
        //设置返回时的编码格式
        response.setContentType("text/html; charset=utf-8");
        //调用JSONArray.fromObject方法把array中的对象转化为JSON格式的数组
        JSONArray json=JSONArray.fromObject(array);
        System.out.println(array.toString());
        //返回给前段页面
        PrintWriter out = response.getWriter();  
        out.println(json);  
        out.flush();  
        out.close();   
		
	}
}
