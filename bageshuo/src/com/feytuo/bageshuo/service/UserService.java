package com.feytuo.bageshuo.service;

import java.sql.SQLException;
import java.util.Random;

import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.domian.User;

/**
 * 用户操作service
 * 
 * @author Tms
 * 
 */
public class UserService {

	private UserDao userDao = new UserDao();
	/**
	 * 用户注册
	 * 
	 * @param user
	 *            产生的用户
	 */
	public int userRegister(String u_name, String u_pwd, String u_type,
			String device_id, String u_push_id) throws Exception {
		boolean isExist = userDao.queryUserByUName(u_name);
		if (isExist) {
			return -1;
		} else {
			int u_id = 0;
			u_id = userDao.saveUser(getUser(u_name, u_pwd, u_type, device_id, u_push_id));
			if(buildBage(u_id)){//八哥号创建并更新成功
				return u_id;
			}else{//创建失败
				userDao.deleteUserByUid(u_id);
				return -1;
			}
		}
	}

	/**
	 * 用户普通登录
	 * 
	 * @param u_name
	 *            用户名
	 * @param u_pwd
	 *            用户密码
	 * @return 是否存在 uid>0表示数据库中有用户，如果isUpdate==false,则表明没有更新成功，则让用户从新登录，使uid=0
	 * @throws Exception
	 */
	public int normalLogin(String u_name, String u_pwd, String u_type,
			String device_id, String u_push_id) throws Exception {
		int u_id = 0;
		u_id = userDao.queryUserByUNameAndUPwd(u_name, u_pwd);
		if (u_id > 0) {
			boolean isUpdate = userDao.updateUserTypeDeviceIdPushId(u_type,
					device_id, u_push_id, u_id);
			if (isUpdate) {
				return u_id;
			} else {
				u_id = 0;
			}
		}
		return u_id;
	}

	/**
	 * 根据用户名修改密码和设备的ID
	 * 
	 * @param u_name
	 * @param u_pwd
	 * @param device_id
	 * @return 是否修改成功
	 * @throws Exception
	 */
	public boolean updatePwd(String u_name, String u_pwd, String device_id)
			throws Exception {
		boolean isUpdatePwdSuccess = false;
		boolean isExist = userDao.queryUserIsExistByUname(u_name);
		if (isExist) {
			isUpdatePwdSuccess = userDao.updatePwdAndDeviceID(u_pwd, device_id,
					u_name);
		}
		return isUpdatePwdSuccess;
	}

	/**
	 * 设置个人信息
	 * 
	 * @param device_id
	 * @param u_head
	 * @param u_nick
	 * @param u_sex
	 * @param u_home
	 * @param u_sign
	 * @param u_id
	 * @return
	 * @throws Exception
	 */
	public boolean setUserInfo(String u_head,String device_id, String u_nick,
			String u_sex, String u_home, String u_sign, int u_id)
			throws Exception {
		boolean flag = false;
        //将数据保存到数据库服务器
		flag =  userDao.updateUserInfo(device_id, u_head, u_nick,
    		u_sex, u_home, u_sign, u_id);
		return flag;
	}
	
	/**
	 * 第三方登录
	 * @param u_name 用户名
	 * @param u_pwd 用户密码
	 * @param u_type 登录方式
	 * @param device_id 设备id
	 * @param u_push_id clientid
	 * @return 登录成功返回uid，不成功返回0
	 */
	public int threeLogin(String u_name, String u_pwd, String u_type,
			String device_id, String u_push_id) throws Exception{
		int uId = 0;
		//判断用户是否存在
		boolean isExist = userDao.queryUserByUName(u_name);
		if(isExist){//存在，更新数据
			boolean isUpdate = userDao.updateTypeDeviceIDPushID(u_type, device_id, u_push_id, u_name);
			if(isUpdate){
				uId = userDao.queryUidByUname(u_name);
			}
		}else{//不存在，新用户，保存数据
			uId = userDao.saveUser(getUser(u_name, u_pwd, u_type, device_id, u_push_id));
			if(!buildBage(uId)){//新用户使用第三方登录，需要生成八哥号
				uId = 0;//生成不成功，则登录失败
			}
		}
		return uId;
	}
	
	/**
	 * 根据用户信息构造User类
	 * @param u_name
	 * @param u_pwd
	 * @param u_type
	 * @param device_id
	 * @param u_push_id
	 * @return
	 */
	private User getUser(String u_name, String u_pwd, String u_type,
			String device_id, String u_push_id){
		User user = new User();
		user.setU_name(u_name);
		user.setU_pwd(u_pwd);
		user.setU_type(u_type);
		user.setDevice_id(device_id);
		user.setU_push_id(u_push_id);
		user.setU_nick("小八哥");
		user.setU_bage("000000");
		user.setU_sex("女");
		user.setU_home("长沙");
		return user;
	}
	
	/**
	 * 根据用户id确定八哥号u_bage
	 * 并更新用户数据库八哥号
	 * @param u_id 用户id
	 * @throws SQLException 
	 */
	private boolean buildBage(int u_id) throws SQLException {
		// TODO Auto-generated method stub
		boolean isUpdate = false;
		int[] firstList = new int[]{1,3,5,7,9};
		int[] secondList = new int[]{0,1,2,3,4,5,6,7,8,9};
		int[] thirdList = new int[]{2,4,6,8,0};
		Random random = new Random();
		int first = firstList[random.nextInt(firstList.length)];
		int second = secondList[random.nextInt(secondList.length)];
		int third = thirdList[random.nextInt(thirdList.length)];
		System.out.println("八哥号："+first+"-"+second+"-"+third+"-"+u_id);
		StringBuilder sb = new StringBuilder();
		sb.append(first).append(second).append(third).append(u_id);
		isUpdate = userDao.updateBageByUid(sb.toString(),u_id);
		return isUpdate;
	}
}
