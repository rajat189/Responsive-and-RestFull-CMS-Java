package com.tcs.vendor.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class VendorOrderDetails {

	String orderId;
	String empId;
	String empName;
	float price;
	
	
	public VendorOrderDetails() {
		super();
		
	}
	public VendorOrderDetails(String orderId, String empId, String empName,
			float price, ArrayList<VendorOrderItem> itemList) {
		super();
		this.orderId = orderId;
		this.empId = empId;
		this.empName = empName;
		this.price = price;
		this.itemList = itemList;
	}
	ArrayList<VendorOrderItem> itemList=new ArrayList<VendorOrderItem>();
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public ArrayList<VendorOrderItem> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<VendorOrderItem> itemList) {
		this.itemList = itemList;
	}
	public float calPrice(){
		float total=0;
		for(VendorOrderItem item:itemList){
			total=total+item.getPrice();
		}
		return total;
	}
	
	
}
