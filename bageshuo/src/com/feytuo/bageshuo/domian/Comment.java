package com.feytuo.bageshuo.domian;

public class Comment {
	private Integer com_id;
	private String com_location;
	private java.util.Date com_date;
	private String com_word;
	private String com_voice;
	private Integer u_id;
	private Integer inv_id;

	private User user;
	private Invitation invitation;

	public Integer getCom_id() {
		return com_id;
	}

	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}

	public String getCom_location() {
		return com_location;
	}

	public void setCom_location(String com_location) {
		this.com_location = com_location;
	}

	public java.util.Date getCom_date() {
		return com_date;
	}

	public void setCom_date(java.util.Date com_date) {
		this.com_date = com_date;
	}

	public String getCom_word() {
		return com_word;
	}

	public void setCom_word(String com_word) {
		this.com_word = com_word;
	}

	public String getCom_voice() {
		return com_voice;
	}

	public void setCom_voice(String com_voice) {
		this.com_voice = com_voice;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getInv_id() {
		return inv_id;
	}

	public void setInv_id(Integer inv_id) {
		this.inv_id = inv_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
}
