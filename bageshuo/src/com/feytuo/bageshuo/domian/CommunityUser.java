package com.feytuo.bageshuo.domian;

/**
 * 社区和用户中间表
 * @author Tms
 *
 */
public class CommunityUser {
	private Integer co_u_id;
	private Integer co_id;
	private Integer u_id;
	
	private Community community;
	private User user;

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCo_u_id() {
		return co_u_id;
	}

	public void setCo_u_id(Integer co_u_id) {
		this.co_u_id = co_u_id;
	}

	public Integer getCo_id() {
		return co_id;
	}

	public void setCo_id(Integer co_id) {
		this.co_id = co_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
}
