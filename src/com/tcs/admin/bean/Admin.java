package com.tcs.admin.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Admin {

	private String a_id;
	private String a_name;
	private String a_pwd;
	private String a_email;
	private long a_phone;
	private int a_active;
	
	public Admin(){}
	
	
	/*				Adding Admin	&  Displaying all Admin 	*/
	
	public Admin(String a_id, String a_name, String a_email, long a_phone) {
		this.a_id = a_id;
		this.a_name = a_name;
		this.a_email = a_email;
		this.a_phone = a_phone;
	}



	public Admin(String a_id, String a_name, String a_pwd, String a_email,
			long a_phone) {
		this.a_id = a_id;
		this.a_name = a_name;
		this.a_pwd = a_pwd;
		this.a_email = a_email;
		this.a_phone = a_phone;
	}
	public Admin(String a_id, String a_name, String a_pwd, String a_email,
			long a_phone, int a_active) {
		this.a_id = a_id;
		this.a_name = a_name;
		this.a_pwd = a_pwd;
		this.a_email = a_email;
		this.a_phone = a_phone;
		this.a_active = a_active;
	}
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_pwd() {
		return a_pwd;
	}
	public void setA_pwd(String a_pwd) {
		this.a_pwd = a_pwd;
	}
	public String getA_email() {
		return a_email;
	}
	public void setA_email(String a_email) {
		this.a_email = a_email;
	}
	public long getA_phone() {
		return a_phone;
	}
	public void setA_phone(long a_phone) {
		this.a_phone = a_phone;
	}
	public int getA_active() {
		return a_active;
	}
	public void setA_active(int a_active) {
		this.a_active = a_active;
	}
	
	
}
