package com.feytuo.bageshuo.domian;

/**
 * ∞À∏ÁÀµŒ Ã‚
 * @author Administrator
 *
 */
public class Problem {
	private Integer pr_id;
	private String pr_location;
	private java.util.Date pr_time;
	private String pr_word;
	private String pr_voice;
	private String pr_native;
	private Integer pr_share_num;
	private Integer pr_praise_num;
	private Integer u_id;

	private User user;

	public Integer getPr_id() {
		return pr_id;
	}

	public void setPr_id(Integer pr_id) {
		this.pr_id = pr_id;
	}

	public String getPr_location() {
		return pr_location;
	}

	public void setPr_location(String pr_location) {
		this.pr_location = pr_location;
	}

	public java.util.Date getPr_time() {
		return pr_time;
	}

	public void setPr_time(java.util.Date pr_time) {
		this.pr_time = pr_time;
	}

	public String getPr_word() {
		return pr_word;
	}

	public void setPr_word(String pr_word) {
		this.pr_word = pr_word;
	}

	public String getPr_voice() {
		return pr_voice;
	}

	public void setPr_voice(String pr_voice) {
		this.pr_voice = pr_voice;
	}

	public String getPr_native() {
		return pr_native;
	}

	public void setPr_native(String pr_native) {
		this.pr_native = pr_native;
	}

	public Integer getPr_share_num() {
		return pr_share_num;
	}

	public void setPr_share_num(Integer pr_share_num) {
		this.pr_share_num = pr_share_num;
	}

	public Integer getPr_praise_num() {
		return pr_praise_num;
	}

	public void setPr_praise_num(Integer pr_praise_num) {
		this.pr_praise_num = pr_praise_num;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
