package com.feytuo.bageshuo.domian;

/**
 * �û�
 * 
 * @author Tms
 * 
 */
public class User {
	private Integer u_id;
	private String u_name;
	private String u_pwd;
	private String u_head;
	private String u_nick;
	private String u_bage;
	private String u_sex;
	private String u_home;
	private String u_sign;
	private String u_type;
	private String device_id;
	private String u_push_id;

	public String getU_push_id() {
		return u_push_id;
	}

	public void setU_push_id(String u_push_id) {
		this.u_push_id = u_push_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_head() {
		return u_head;
	}

	public void setU_head(String u_head) {
		this.u_head = u_head;
	}

	public String getU_nick() {
		return u_nick;
	}

	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}

	public String getU_bage() {
		return u_bage;
	}

	public void setU_bage(String u_bage) {
		this.u_bage = u_bage;
	}

	public String getU_sex() {
		return u_sex;
	}

	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}

	public String getU_home() {
		return u_home;
	}

	public void setU_home(String u_home) {
		this.u_home = u_home;
	}

	public String getU_sign() {
		return u_sign;
	}

	public void setU_sign(String u_sign) {
		this.u_sign = u_sign;
	}

	public String getU_type() {
		return u_type;
	}

	public void setU_type(String u_type) {
		this.u_type = u_type;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

}
