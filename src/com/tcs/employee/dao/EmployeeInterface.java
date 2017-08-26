package com.tcs.employee.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.employee.bean.ChangePwd;
import com.tcs.employee.bean.Employee;
import com.tcs.employee.bean.Login;
import com.tcs.employee.bean.Transaction;
import com.tcs.employee.bean.Wallet;
import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;

public interface EmployeeInterface {
	// By Amit
	public Employee login(Login login) throws ClassNotFoundException,
			SQLException;

	// By Amit
	public int update(Employee employee) throws ClassNotFoundException,
			SQLException;

	// By Amit
	public Employee getEmployee(String id) throws ClassNotFoundException,
			SQLException;

	// By Shalabh
	public ArrayList<Wallet> showWallet(String id)
			throws ClassNotFoundException, SQLException;

	// By Ram
	public ArrayList<Vendor> displayVendorList() throws ClassNotFoundException,
			SQLException;

	// By Amit
	public boolean changePwd(ChangePwd changePwd)
			throws ClassNotFoundException, SQLException;

	public int registration(Employee employee) throws ClassNotFoundException,
			SQLException;

	// By Jeet
	public ArrayList<Vendor> getAllVendors() throws ClassNotFoundException,
			SQLException;

	// By ShiShir
	public double getWalletBalance(String e_id, String v_id)
			throws ClassNotFoundException, SQLException;

	// By Prashanti
	public boolean addWallet(String e_id, String v_id)
			throws ClassNotFoundException, SQLException;

	// By Souvik
	public ArrayList<Item> getItems(String v_id) throws ClassNotFoundException,
			SQLException;

	// By DhivyaG
	public ArrayList<Transaction> ShowTransaction(String id)
			throws ClassNotFoundException, SQLException;

	// By Sishir
	public boolean rechargeWallet(String e_id, String v_id, double amount)
			throws ClassNotFoundException, SQLException;
}
