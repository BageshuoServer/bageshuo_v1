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
	
	public int userRegister(User user) throws Exception {
		boolean isExist = userDao.queryUserByUName(user);
		if(isExist){
			return -1;
		}else {
			int u_id = 0;
			u_id = userDao.saveUser(user);
			return u_id;
		}
	}
	
	/**
	 * 用户普通登录
	 * @param u_name 用户名
	 * @param u_pwd  用户密码
	 * @return 是否存在  uid>0表示数据库中有用户，如果isUpdate==false,则表明没有更新成功，则让用户从新登录，使uid=0
	 * @throws Exception 
	 */
	public int normalLogin(String u_name,String u_pwd,String u_type,String device_id,String u_push_id) throws Exception {
		int u_id = 0;
		u_id = userDao.queryUserByUNameAndUPwd(u_name, u_pwd);
		if(u_id>0){
			boolean isUpdate = userDao.updateUserTypeDeviceIdPushId(u_type,device_id,u_push_id,u_id);
			if(isUpdate) {
				return u_id;
			}else{
				u_id = 0;
			}
		}
		return u_id;
	}
	
	/**
	 *根据用户名修改密码和设备的ID
	 * @param u_name
	 * @param u_pwd
	 * @param device_id
	 * @return 是否修改成功
	 * @throws Exception
	 */
	public boolean updatePwd(String u_name,String u_pwd,String device_id) throws Exception {
		boolean isUpdatePwdSuccess = false;
		boolean isExist = userDao.queryUserIsExistByUname(u_name);
		if(isExist){
			isUpdatePwdSuccess = userDao.updatePwdAndDeviceID(u_pwd,device_id,u_name);
		}
		return isUpdatePwdSuccess;
	}
}
