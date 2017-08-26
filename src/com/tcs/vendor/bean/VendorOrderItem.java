package com.tcs.vendor.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VendorOrderItem {

	int itemId;
	String itemName;
	int quantity;
	float price=0;
	
	public VendorOrderItem() {
		super();
	}
	public VendorOrderItem(int itemId, String itemName, int quantity,
			float price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
