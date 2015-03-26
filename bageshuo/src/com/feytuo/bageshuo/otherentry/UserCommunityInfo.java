package com.feytuo.bageshuo.otherentry;

/**
 * 用户社区基本信息类， co_id 社区的id co_name 社区的名字 co_inv_num 对应社区的帖子数
 * @author Tms
 */
public class UserCommunityInfo {
	private int co_id;
	private String co_name;
	private int co_inv_num;

	public int getCo_id() {
		return co_id;
	}

	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}

	public String getCo_name() {
		return co_name;
	}

	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}

	public int getCo_inv_num() {
		return co_inv_num;
	}

	public void setCo_inv_num(int co_inv_num) {
		this.co_inv_num = co_inv_num;
	}
}
