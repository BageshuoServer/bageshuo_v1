package com.feytuo.bageshuo.domian;

/**
 * Ьћзг
 * @author Tms
 *
 */
public class Invitation {
    private Integer inv_id;
    private String inv_location;
    private java.util.Date inv_time;
    private String inv_word;
    private String inv_voice;
    private Integer inv_share_num;
    private Integer inv_praise_num;
    private Integer inv_top;
    private Integer inv_outstanding;
    private Integer u_id;
    private Integer co_id;
    
    private User user;
    private Community community;
	public Integer getInv_id() {
		return inv_id;
	}
	public void setInv_id(Integer inv_id) {
		this.inv_id = inv_id;
	}
	public String getInv_location() {
		return inv_location;
	}
	public void setInv_location(String inv_location) {
		this.inv_location = inv_location;
	}
	public java.util.Date getInv_time() {
		return inv_time;
	}
	public void setInv_time(java.util.Date inv_time) {
		this.inv_time = inv_time;
	}
	public String getInv_word() {
		return inv_word;
	}
	public void setInv_word(String inv_word) {
		this.inv_word = inv_word;
	}
	public String getInv_voice() {
		return inv_voice;
	}
	public void setInv_voice(String inv_voice) {
		this.inv_voice = inv_voice;
	}
	public Integer getInv_share_num() {
		return inv_share_num;
	}
	public void setInv_share_num(Integer inv_share_num) {
		this.inv_share_num = inv_share_num;
	}
	public Integer getInv_praise_num() {
		return inv_praise_num;
	}
	public void setInv_praise_num(Integer inv_praise_num) {
		this.inv_praise_num = inv_praise_num;
	}
	public Integer getInv_top() {
		return inv_top;
	}
	public void setInv_top(Integer inv_top) {
		this.inv_top = inv_top;
	}
	public Integer getInv_outstanding() {
		return inv_outstanding;
	}
	public void setInv_outstanding(Integer inv_outstanding) {
		this.inv_outstanding = inv_outstanding;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public Integer getCo_id() {
		return co_id;
	}
	public void setCo_id(Integer co_id) {
		this.co_id = co_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
    
    
}
