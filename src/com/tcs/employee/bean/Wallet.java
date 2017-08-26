package com.tcs.employee.bean;



// By Shalabh...
public class Wallet {
	
	private String empId;
	private String vendorId;
	private double money;
	public Wallet() {
		super();
	}
	public Wallet(String empId, String vendorId, double money) {
		super();
		this.empId = empId;
		this.vendorId = vendorId;
		this.money = money;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	

}
