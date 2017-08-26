package com.tcs.vendor.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;

public interface VendorInterface {
	public Vendor loginVendor(String v_id, String v_pwd) throws SQLException,
			ClassNotFoundException;

	public boolean insertItemInMenu(Item item) throws SQLException,
			ClassNotFoundException;

	public boolean updateItemInMenu(Item item) throws SQLException,
			ClassNotFoundException;

	public Item searchItemInMenu(String i_name,String vid) throws SQLException,
			ClassNotFoundException;

	public ArrayList<Item> displayMenu(String v_id) throws SQLException,
			ClassNotFoundException;

	public boolean deleteItemInMenu(int itemID) throws SQLException,
			ClassNotFoundException;
}
