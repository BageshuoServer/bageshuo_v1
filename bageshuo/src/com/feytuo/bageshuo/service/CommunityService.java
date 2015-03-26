package com.feytuo.bageshuo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feytuo.bageshuo.dao.CommunityDao;
import com.feytuo.bageshuo.dao.CommunityUserDao;
import com.feytuo.bageshuo.dao.InvitationDao;
import com.feytuo.bageshuo.dao.UserDao;
import com.feytuo.bageshuo.domian.Community;
import com.feytuo.bageshuo.domian.CommunityUser;
import com.feytuo.bageshuo.domian.Invitation;
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
    
//    public static void main(String[] args) throws Exception {
//    	CommunityService service = new CommunityService();
//    	Map<String,Object> perCommunityInfoMap = service.perCommunityInfo(23, 3, "333");
//    	int interest_num = (Integer) perCommunityInfoMap.get("co_interest_num");
//    	int inv_num = (Integer) perCommunityInfoMap.get("co_inv_num");
//    	Community community = (Community) perCommunityInfoMap.get("community");
//    	System.out.println(interest_num);
//    	System.out.println("-------------------");
//    	System.out.println(inv_num);
//    	System.out.println("-------------------");
//    	System.out.println(community.getCo_name());
//    	
//	}
}
