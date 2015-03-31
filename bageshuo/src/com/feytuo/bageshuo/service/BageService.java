package com.feytuo.bageshuo.service;

import com.feytuo.bageshuo.dao.AnswerDao;
import com.feytuo.bageshuo.dao.ProblemDao;
import com.feytuo.bageshuo.dao.UserDao;

public class BageService {
	/**
	 * 提问service
	 * @param pr_location
	 * @param device_id
	 * @param pr_time
	 * @param pr_word
	 * @param pr_voice
	 * @param pr_native
	 * @param pr_share_num
	 * @param pr_praise_num
	 * @param u_id
	 * @return
	 * @throws Exception
	 */
    public boolean publishProblem(String pr_location,String device_id,
			java.util.Date pr_time, String pr_word, String pr_voice,
			String pr_native,int pr_share_num,int pr_praise_num,int u_id) throws Exception {
    	UserDao userDao = new UserDao();
    	boolean isSuccess = false;
    	boolean isExit = userDao.queryUserByUidAndDeviceId(u_id, device_id);
    	if(isExit){
    		ProblemDao problemDao = new ProblemDao();
    		try {
    			isSuccess = problemDao.insertProblem(pr_location, pr_time, pr_word, pr_voice, pr_native, pr_share_num, pr_praise_num, u_id);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
    	}
    	return isSuccess;
    }
    
    
  
    /**
     * 回答service
     * @param an_location
     * @param device_id
     * @param an_time
     * @param an_word
     * @param an_voice
     * @param an_praise_num
     * @param u_id
     * @param pr_id
     * @return
     * @throws Exception
     */
    public boolean publishanswer(String an_location,String device_id,
			java.util.Date an_time, String an_word, String an_voice,
			int an_praise_num,int u_id,int pr_id) throws Exception {
    	UserDao userDao = new UserDao();
    	boolean isSuccess = false;
    	boolean isExit = userDao.queryUserByUidAndDeviceId(u_id, device_id);
    	if(isExit){
    		AnswerDao answerDao = new AnswerDao();
    		try {
    			isSuccess = answerDao.insertAnswer(an_location, an_time, an_word, an_voice, an_praise_num, u_id, pr_id);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
    	}
    	return isSuccess;
    }
}
