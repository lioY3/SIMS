package service;

import dao.SystemDao;
import dao.impl.SystemDaoImpl;
import model.User;

public class SystemService {

	SystemDao dao = new SystemDaoImpl();

	/**
	 * 登录验证
	 * 
	 * @param user
	 * @return
	 */
	public User getAdmin(User user) {
		User searchUser = (User) dao.getObject(User.class, "SELECT * FROM user WHERE Account=? AND Password=?",
				new Object[] { user.getAccount(), user.getPassword() });
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
