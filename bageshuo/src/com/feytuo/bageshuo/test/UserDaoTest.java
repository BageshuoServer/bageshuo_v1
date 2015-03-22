package com.feytuo.bageshuo.test;

import org.junit.Test;

import com.feytuo.bageshuo.dao.UserDao;


public class UserDaoTest {
	
	@Test
    public void testname() throws Exception {
		UserDao userdao = new UserDao();
		int uid= userdao.queryUserByUNameAndUPwd("wszzzzzwsw", "ewew");
		System.out.println(uid);
	}
	
}
