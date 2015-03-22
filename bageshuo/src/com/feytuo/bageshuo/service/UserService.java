package com.feytuo.bageshuo.service;

import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.domian.User;

/**
 * 用户操作service
 * @author Tms
 *
 */
public class UserService {

	/**
	 * 用户注册
	 * @param user 产生的用户
	 */
	UserDao userDao = new UserDao();
	
	public boolean userRegister(User user) {
		boolean flag = false;
		//flag = userDao.insertUser(user);
		return flag;
	}

}
