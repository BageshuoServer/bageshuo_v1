package com.feytuo.bageshuo.domian;

/**
 * °Ë¸çËµ´ð°¸
 * @author Tms
 *
 */
public class Answer {
	private Integer an_id;
	private String an_location;
	private java.util.Date an_time;
	private String an_word;
	private String an_voice;
	private Integer an_praise_num;
	private Integer u_id;
	private Integer pr_id;

	private User user;
	private Problem problem;

	public Integer getAn_id() {
		return an_id;
	}

	public void setAn_id(Integer an_id) {
		this.an_id = an_id;
	}

	public String getAn_location() {
		return an_location;
	}

	public void setAn_location(String an_location) {
		this.an_location = an_location;
	}

	public java.util.Date getAn_time() {
		return an_time;
	}

	public void setAn_time(java.util.Date an_time) {
		this.an_time = an_time;
	}

	public String getAn_word() {
		return an_word;
	}

	public void setAn_word(String an_word) {
		this.an_word = an_word;
	}

	public String getAn_voice() {
		return an_voice;
	}

	public void setAn_voice(String an_voice) {
		this.an_voice = an_voice;
	}

	public Integer getAn_praise_num() {
		return an_praise_num;
	}

	public void setAn_praise_num(Integer an_praise_num) {
		this.an_praise_num = an_praise_num;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getPr_id() {
		return pr_id;
	}

	public void setPr_id(Integer pr_id) {
		this.pr_id = pr_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
}
