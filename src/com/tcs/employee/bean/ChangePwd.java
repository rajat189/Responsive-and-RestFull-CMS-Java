package com.tcs.employee.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChangePwd {
	private String id;
	private String oldPwd;
	private String newPwd;
	public ChangePwd() {
	}
	public ChangePwd(String id, String oldPwd, String newPwd) {
		super();
		this.id = id;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
