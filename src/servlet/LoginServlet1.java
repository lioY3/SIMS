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
 * Servlet implementation class LoginServlet1
 */
@WebServlet("/LoginServlet1")
public class LoginServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemService service = new SystemService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet1() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("GetVCode".equals(method)) {
			getVCode(request, response);
		}
	}

	private void getVCode(HttpServletRequest request, HttpServletResponse response) {
		// 创建验证码生成器对象
		VCodeGenerator vcGenerator = new VCodeGenerator();
		// 生成验证码
		String vcode = vcGenerator.generatorVCode();
		// 将验证码保存在session域中,以便判断验证码是否正确
		request.getSession().setAttribute("vcode", vcode);
		// 生成验证码图片
		BufferedImage vImg = vcGenerator.generatorVCodeImage(vcode);
		// 输出图像
		try {
			ImageIO.write(vImg, "gif", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的方法
		String method = request.getParameter("method");

		if ("Login".equals(method)) { // 验证登录
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户输入的账户
		String account = request.getParameter("account");
		// 获取用户输入的密码
		String password = request.getParameter("password");
		// 获取用户输入的验证码
		String vcode = request.getParameter("vcode");
		// 获取登录类型
		int type = Integer.parseInt(request.getParameter("type"));

		// 返回信息
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
			user.setLevel(Integer.parseInt(request.getParameter("type")));

			// 创建系统数据层对象,查询用户是否存在
			User loginUser = service.getAdmin(user);
			if (loginUser == null) {// 如果用户名或密码错误
				msg = "loginError";
			} else { // 正确
				if (User.ADMIN == type) {
					msg = "admin";
				} else if (User.TEACHER == type) {
					msg = "teacher";
				}
				// 将该用户名保存到session中
				request.getSession().setAttribute("user", loginUser);
			}
		}
		// 返回登录信息
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
