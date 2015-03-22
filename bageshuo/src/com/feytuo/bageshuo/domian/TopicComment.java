package com.feytuo.bageshuo.domian;

/**
 * ª∞Ã‚∆¿¬€
 * @author Tms
 *
 */
public class TopicComment {
	private Integer to_com_id;
	private String to_com_location;
	private java.util.Date to_com_time;
	private String to_com_word;
	private String to_com_voice;
	private Integer u_id;
	private Integer to_id;

	private User user;
	private Topic topic;

	public Integer getTo_com_id() {
		return to_com_id;
	}

	public void setTo_com_id(Integer to_com_id) {
		this.to_com_id = to_com_id;
	}

	public String getTo_com_location() {
		return to_com_location;
	}

	public void setTo_com_location(String to_com_location) {
		this.to_com_location = to_com_location;
	}

	public java.util.Date getTo_com_time() {
		return to_com_time;
	}

	public void setTo_com_time(java.util.Date to_com_time) {
		this.to_com_time = to_com_time;
	}

	public String getTo_com_word() {
		return to_com_word;
	}

	public void setTo_com_word(String to_com_word) {
		this.to_com_word = to_com_word;
	}

	public String getTo_com_voice() {
		return to_com_voice;
	}

	public void setTo_com_voice(String to_com_voice) {
		this.to_com_voice = to_com_voice;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getTo_id() {
		return to_id;
	}

	public void setTo_id(Integer to_id) {
		this.to_id = to_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
