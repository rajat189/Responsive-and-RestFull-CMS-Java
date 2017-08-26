/**
 * 
 */
package com.tcs.employee.bean;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 1213330
 * 
 */

@XmlRootElement
public class Employee {

	private String e_id;
	private String e_name;
	private String e_pwd;
	private String e_email;
	private long e_phone;
	private int e_active;
	private HashMap<Integer, Integer> order;

	// 'tcs@1234' not null
	
	@Override
	public String toString() {
		return "Employee [e_id=" + e_id + ", e_name=" + e_name + ", e_pwd="
				+ e_pwd + ", e_email=" + e_email + ", e_phone=" + e_phone
				+ ", e_active=" + e_active + ", order=" + order + "]";
	}

	public Employee() {

	}

	/**
	 * @param e_name
	 * @param e_pwd
	 * @param e_email
	 * @param e_phone
	 * @param e_active
	 * @param orders
	 * @param items
	 */
	public Employee(String e_name, String e_pwd, String e_email, long e_phone,int e_active) {
		this.e_name = e_name;
		this.e_pwd = e_pwd;
		this.e_email = e_email;
		this.e_phone = e_phone;
		this.e_active = e_active;
		this.order = new HashMap<Integer, Integer>();
	}
	
	public Employee(String e_id,String e_name, String e_email,int e_active, long e_phone) {
		this.e_name = e_name;
		this.e_pwd = e_pwd;
		this.e_email = e_email;
		this.e_phone = e_phone;
		this.e_active = e_active;
		
	}

	/**
	 * @param e_id
	 * @param e_name
	 * @param e_pwd
	 * @param e_email
	 * @param e_phone
	 * @param e_active
	 * @param orders
	 */
	public Employee(String e_id, String e_name, String e_pwd, String e_email,
			long e_phone, int e_active) {
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_pwd = e_pwd;
		this.e_email = e_email;
		this.e_phone = e_phone;
		this.e_active = e_active;
		this.order = new HashMap<Integer, Integer>();
	}

	

	public Employee(String e_id, String e_name, String e_email, long e_phone) {
		super();
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_email = e_email;
		this.e_phone = e_phone;
	}

	/**
	 * @return the e_id
	 */
	public String getE_id() {
		return e_id;
	}

	/**
	 * @param e_id
	 *            the e_id to set
	 */
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}

	/**
	 * @return the e_name
	 */
	public String getE_name() {
		return e_name;
	}

	/**
	 * @param e_name
	 *            the e_name to set
	 */
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	/**
	 * @return the e_pwd
	 */
	public String getE_pwd() {
		return e_pwd;
	}

	/**
	 * @param e_pwd
	 *            the e_pwd to set
	 */
	public void setE_pwd(String e_pwd) {
		this.e_pwd = e_pwd;
	}

	/**
	 * @return the e_email
	 */
	public String getE_email() {
		return e_email;
	}

	/**
	 * @param e_email
	 *            the e_email to set
	 */
	public void setE_email(String e_email) {
		this.e_email = e_email;
	}

	/**
	 * @return the e_phone
	 */
	public long getE_phone() {
		return e_phone;
	}

	/**
	 * @param e_phone
	 *            the e_phone to set
	 */
	public void setE_phone(long e_phone) {
		this.e_phone = e_phone;
	}

	/**
	 * @return the e_active
	 */
	public int getE_active() {
		return e_active;
	}

	/**
	 * @param e_active
	 *            the e_active to set
	 */
	public void setE_active(int e_active) {
		this.e_active = e_active;
	}

	/**
	 * @return the orders
	 */
	public HashMap<Integer, Integer> getOrder() {
		return this.order;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrder(Integer itemid, Integer quantity) {

		if (this.order != null)
			this.order.put(itemid, quantity);
		else {
			this.order = new HashMap<Integer, Integer>();
			this.order.put(itemid, quantity);
		}
	}
}
