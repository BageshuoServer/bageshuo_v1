package com.feytuo.bageshuo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feytuo.bageshuo.dao.CommentDao;
import com.feytuo.bageshuo.dao.CommunityDao;
import com.feytuo.bageshuo.dao.CommunityUserDao;
import com.feytuo.bageshuo.dao.InvitationDao;
import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.domian.Comment;
import com.feytuo.bageshuo.domian.Community;
import com.feytuo.bageshuo.domian.CommunityUser;
import com.feytuo.bageshuo.domian.Invitation;
import com.feytuo.bageshuo.domian.User;
import com.feytuo.bageshuo.otherentry.UserCommunityInfo;

/**
 * 社区服务类
 * @author Tms
 *
 */
public class CommunityService {
	/**
	 * 接口：获取用户的社区的信息
	 * @param u_id
	 * @param device_id
	 * @return userCommunityInfoList 用户对应的社区的id以及社区名字和对应社区的帖子的数量
	 */
	
	UserDao userdao = new UserDao();
	CommunityUserDao communityUserDao = new CommunityUserDao();
	InvitationDao invitationDao = new InvitationDao();
	CommunityDao communityDao = new CommunityDao();
	CommentDao commentDao = new CommentDao();
	
    public List<UserCommunityInfo> getUserCommunityInfo(int u_id,String device_id) throws Exception{
    	List<CommunityUser> communityUserList = new ArrayList<CommunityUser>(); 
    	List<UserCommunityInfo> userCommunityInfoList = new ArrayList<UserCommunityInfo>();
    	//1先根据u_id和device_id判断用户是否在用户表里面
    	try {
			boolean isExits = userdao.queryUserByUidAndDeviceId(u_id, device_id);
			if(isExits) {
				//2根据用户u_id获得co_id列表
				communityUserList = communityUserDao.queryCoIdListByUid(u_id);
				if(!communityUserList.isEmpty()) {
					//遍历CommunityUser，分别取出对应的co_id然后再到invitation表中进行查询
					for(CommunityUser communityUser: communityUserList) {
						List<Invitation> invitationList = new ArrayList<Invitation>();
						Community community = new Community();
						//根据co_id得到对应的社区
						community = communityDao.queryCommunityByCoId(communityUser.getCo_id());
						//3根据co_id获得社区中的帖子数
						invitationList = invitationDao.queryInvatationListByCoId(communityUser.getCo_id());
						//封装每一个信息
						UserCommunityInfo userCommunityInfo = new UserCommunityInfo();
						userCommunityInfo.setCo_id(communityUser.getCo_id());
						userCommunityInfo.setCo_name(community.getCo_name());
						userCommunityInfo.setCo_inv_num(invitationList.size());
						userCommunityInfoList.add(userCommunityInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	return userCommunityInfoList;
    } 
    
    /**
     * 获取用户社区详情
     * @param u_id 用户id
     * @param co_id 社区id
     * @param device_id 设备id
     * @return
     * @throws Exception
     */
    public Map<String,Object> perCommunityInfo(int u_id,int co_id,String device_id) throws Exception {
    	Map<String,Object> perCommunityInfoMap = new HashMap<String,Object>();
    	try {
			boolean isExits = userdao.queryUserByUidAndDeviceId(u_id, device_id);
			if(isExits) {
				//初始社区列表
				List<CommunityUser> communityList = new ArrayList<CommunityUser>();
				//初始化帖子列表
				List<Invitation> invitationList = new ArrayList<Invitation>();
				//社区
				Community community = new Community();
				//根据社区id(co_id)获得中间表中当前co_id的数量，可以得到用户的数量.size()
				communityList = communityUserDao.queryUidListByCoId(co_id);
				//根据帖子表中co_id的数量的到当前社区的帖子数
				invitationList = invitationDao.queryInvatationListByCoId(co_id);
				//根据co_id得到社区的基本信息
				community = communityDao.queryCommunityByCoId(co_id);
				//将得到的信息放到map中
				perCommunityInfoMap.put("co_interest_num", communityList.size());
				perCommunityInfoMap.put("co_inv_num",invitationList.size());
				perCommunityInfoMap.put("community", community);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	return perCommunityInfoMap;
    }
    
    /**
     * 根据用户的u_id，device_id和co_id获取置顶帖的信息
     * @param u_id
     * @param device_id
     * @param co_id
     * @return
     * @throws Exception 
     */
    public List<HashMap<String, Object>> getTopInvationInfoList(int u_id,String device_id,int co_id) throws Exception { 
    	 List<HashMap<String, Object>> topInvationInfoList  = new ArrayList<HashMap<String,Object>>();
    	 List<Invitation> topInvationList = new ArrayList<Invitation>();
    	 try {
			boolean isExits = userdao.queryUserByUidAndDeviceId(u_id, device_id);
			if(isExits) {
				//根据co_id获得置顶帖子数（已经按时间的先后顺序排好了顺序）
				topInvationList = invitationDao.queryTopInvationListByCoIdOrderByInvTime(co_id);
				if(!topInvationList.isEmpty()) {
					//初始化评论数目
					int topInvitationNum = 0;
					//初始化发帖人
					User user= new User();
					for(Invitation invitation :topInvationList) {
						topInvitationNum = commentDao.queryCommentNumListByInvId(invitation.getInv_id());
						user =userdao.queryUserByUid(invitation.getU_id());
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("invitation",invitation );
						map.put("topInvitationNum", topInvitationNum);
						map.put("user", user);
						topInvationInfoList.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	 return topInvationInfoList;
    }
    
    /**
     * 用户是否关注成功
     * @param u_id 用户ID
     * @param co_id  社区ID
     * @param device_id 设备ID
     * @return
     * @throws Exception 
     */
    public boolean interestCo(int u_id,int co_id,String device_id) throws Exception {
    	boolean interestSuccess = false;
    	try {
			boolean isExsit  = userdao.queryUserByUidAndDeviceId(u_id, device_id);
			if(isExsit) {
				//判断用户是否已经关注这个社区
				boolean isAleadlyInterest = communityUserDao.queryUserIsInerest(u_id, co_id);
				if(isAleadlyInterest){
					//如果关注则赋值关注成功
					interestSuccess = true;
				}else {
					//用户还没关注，则向中间表中插入数据
					interestSuccess = communityUserDao.insertIntoComminityUser(u_id, co_id);
					return interestSuccess;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	return interestSuccess;
    }
}
