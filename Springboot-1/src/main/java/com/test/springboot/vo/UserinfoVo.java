package com.test.springboot.vo;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_userInfo")
public class UserinfoVo {

	private String id;
	private String organid;
	private String usercode;
	private String username;
	private String userpass;
	private String useremail;
	private String userphone;
	private String userstate;
	private String userhead;
	private String usersecurity;
	private String bindip;
	private String userbirthday;
	private String useraddress;
	private String usersex;
	private String description;
	private String createuser;
	private String createtime;
	private String updateuser;
	private String updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganid() {
		return organid;
	}

	public void setOrganid(String organid) {
		this.organid = organid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}

	public String getUsersecurity() {
		return usersecurity;
	}

	public void setUsersecurity(String usersecurity) {
		this.usersecurity = usersecurity;
	}

	public String getBindip() {
		return bindip;
	}

	public void setBindip(String bindip) {
		this.bindip = bindip;
	}

	public String getUserbirthday() {
		return userbirthday;
	}

	public void setUserbirthday(String userbirthday) {
		this.userbirthday = userbirthday;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "UserinfoVo [id=" + id + ", organid=" + organid + ", usercode=" + usercode + ", username=" + username
				+ ", userpass=" + userpass + ", useremail=" + useremail + ", userphone=" + userphone + ", userstate="
				+ userstate + ", userhead=" + userhead + ", usersecurity=" + usersecurity + ", bindip=" + bindip
				+ ", userbirthday=" + userbirthday + ", useraddress=" + useraddress + ", usersex=" + usersex
				+ ", description=" + description + ", createuser=" + createuser + ", createtime=" + createtime
				+ ", updateuser=" + updateuser + ", updatetime=" + updatetime + "]";
	}

}
