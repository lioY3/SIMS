package service;

import java.util.List;

import dao.SystemDao;
import dao.impl.SystemDaoImpl;
import model.User;
import net.sf.json.JSONArray;

public class SystemService {

	SystemDao dao = new SystemDaoImpl();

	/**
	 * 获取系统所有账号
	 * 
	 * @return
	 */
	public String getAccountList() {
		// 获取数据
		List<String> list = dao.getColumn("SELECT account FROM user", null);
		// json化
		String result = JSONArray.fromObject(list).toString();

		return result;
	}

	/**
	 * 登录验证
	 * 
	 * @param user
	 * @return
	 */
	public User getAdmin(User user) {
		User searchUser = (User) dao.getObject(User.class,
				"SELECT * FROM user WHERE Account=? AND Password=? AND Type=?",
				new Object[] { user.getAccount(), user.getPassword(), user.getType() });
		return searchUser;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 */
	public void editPassword(User user) {

		dao.update("UPDATE user SET password=? WHERE account=?",
				new Object[] { user.getPassword(), user.getAccount() });

	}

}
