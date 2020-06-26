package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.SystemService;
import utils.VCodeGenerator;

/**
 * Servlet implementation class SystemServlet
 */
@WebServlet("/SystemServlet")
public class SystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 创建service对象
	private SystemService service = new SystemService();

	public SystemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("LoginOut".equals(method)) { // 退出系统
			loginOut(request, response);
		} else if ("GetVCode".equals(method)) { // 获取验证码
			getVCode(request, response);
		} else if ("Login".equals(method)) { // 登陆
			login(request, response);
		} else if ("toAdminView".equals(method)) { // 到管理员界面
			request.getRequestDispatcher("view/console/administrator-layout.jsp").forward(request, response);
		} else if ("toTeacherView".equals(method)) { // 到教师界面
			request.getRequestDispatcher("view/console/teacher-layout.jsp").forward(request, response);
		} else if ("toStudentView".equals(method)) { // 到学生界面
			request.getRequestDispatcher("view/console/student-layout.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("EditPassword".equals(method)) { // 修改密码
			editPasswod(request, response);
		}
		doGet(request, response);
	}

	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 退出系统时清除系统登录的用户
		request.getSession().removeAttribute("user");
		String contextPath = request.getContextPath();
		// 转发到登录界面
		response.sendRedirect(contextPath);
	}

	private void getVCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建验证码生成器对象
		VCodeGenerator vcGenerator = new VCodeGenerator();
		// 生成验证码
		String vcode = vcGenerator.generatorVCode();
		// 将验证码保存在session域中,以便判断验证码是否正确
		request.getSession().setAttribute("vcode", vcode);
		// 生成验证码图片
		BufferedImage vImg = vcGenerator.generatorVCodeImage(vcode);
		// 输出图像
		ImageIO.write(vImg, "gif", response.getOutputStream());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取用户输入的账户
		String account = request.getParameter("account");
		// 获取用户输入的密码
		String password = request.getParameter("password");
		// 获取用户输入的验证码
		String vcode = request.getParameter("vcode");
		// 获取登录类型

		// 返回信息z
		String msg = null;

		// 获取session中的验证码
		String sVcode = (String) request.getSession().getAttribute("vcode");

		// 判断验证码是否正确
		if (!sVcode.equalsIgnoreCase(vcode)) {
			msg = "vcodeError";
		} else { // 判断用户名和密码是否正确
			// 将账户和密码封装
			User user = new User();
			user.setAccount(account);
			user.setPassword(password);

			// 创建系统数据层对象,查询用户是否存在
			User loginUser = service.getAdmin(user);
			if (loginUser == null) {// 用户名或密码错误
				msg = "loginError";
			} else { // 正确
				if (loginUser.getType() == 1) {
					msg = "admin";
				} else if (loginUser.getType() == 2) {
					msg = "teacher";
				} else if (loginUser.getType() == 3) {
					msg = "student";
				}
				// 将该用户名保存到session中
				request.getSession().setAttribute("user", loginUser);
			}
		}
		// 返回登录信息
		response.getWriter().write(msg);
	}

	private void editPasswod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		service.editPassword(user);
		response.getWriter().write("success");
	}
	
}
