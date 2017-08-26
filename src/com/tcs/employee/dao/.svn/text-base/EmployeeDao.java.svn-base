package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.data.DatabaseUtils;
import com.tcs.employee.bean.ChangePwd;
import com.tcs.employee.bean.Employee;
import com.tcs.employee.bean.Login;
import com.tcs.employee.bean.Transaction;
import com.tcs.employee.bean.Wallet;
import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;

public class EmployeeDao implements EmployeeInterface {

	private static final String EMPLOYEE_TABLE = "cms_employee";
	private static final String VENDOR_TABLE = "cms_vendor";
	private static final String TRANSACTION_TABLE = "cms_transcation";

	/*--Starting--------------Amit Desai-----------*/

	public Employee login(Login login) throws ClassNotFoundException,
			SQLException {

		Connection connection = DatabaseUtils.getConnection();
		String sql = "select * from " + EMPLOYEE_TABLE
				+ " where e_id=? and e_pwd=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, login.getUsername());
		ps.setString(2, login.getPassword());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			//System.out.println(rs.getInt(6));
			return new Employee(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getLong(5),
					rs.getInt(6));
		}
		return null;
	}

	public int update(Employee employee) throws ClassNotFoundException,
			SQLException {
		Connection connection = DatabaseUtils.getConnection();
		Employee emp = getEmployee(employee.getE_id());
		if (!emp.getE_email().equals(employee.getE_email())) {
			if (isEmailExsist(employee.getE_email(), connection)) {
				return 1;
			}
		}
		if (emp.getE_phone() != employee.getE_phone()) {
			if (isPhoneExsist(employee.getE_phone(), connection)) {
				return 2;
			}
		}
		if (!emp.getE_pwd().equals(employee.getE_pwd())) {
			return 3;
		}
		String sql = "update " + EMPLOYEE_TABLE
				+ " set e_name=?,e_email=?,e_phone=? where e_id=? and e_pwd=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, employee.getE_name());
		ps.setString(2, employee.getE_email());
		ps.setLong(3, employee.getE_phone());
		ps.setString(4, employee.getE_id());
		ps.setString(5, employee.getE_pwd());
		ps.executeUpdate();
		return 0;
	}

	@Override
	public boolean changePwd(ChangePwd changePwd)
			throws ClassNotFoundException, SQLException {
		Connection connection = DatabaseUtils.getConnection();
		String sql = "update " + EMPLOYEE_TABLE
				+ " set e_pwd=? where e_id=? and e_pwd=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, changePwd.getNewPwd());
		ps.setString(2, changePwd.getId());
		ps.setString(3, changePwd.getOldPwd());
		int result = ps.executeUpdate();
		if (result > 0)
			return true;
		return false;
	}

	public Employee getEmployee(String id) throws ClassNotFoundException,
			SQLException {
		Connection connection = DatabaseUtils.getConnection();
		String sql = "select * from " + EMPLOYEE_TABLE + " where e_id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
			return new Employee(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getLong(5),
					rs.getInt(6));
		return null;
	}

	/*--Ending--------------Amit Desai-----------*/

	/*--Starting--------------DhivyaG-----------*/

	public ArrayList<Transaction> ShowTransaction(String id)
			throws ClassNotFoundException, SQLException {
		ArrayList<Transaction> ListTransaction = new ArrayList<Transaction>();

		Connection connection = DatabaseUtils.getConnection();
		String query = "select * from " + TRANSACTION_TABLE + " where t_id=?";
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Transaction transaction = new Transaction(rs.getInt(1),
					rs.getString(2), rs.getString(3), rs.getDate(4),
					rs.getDouble(5), rs.getString(6));
			ListTransaction.add(transaction);
		}
		return ListTransaction;
	}

	/*--Ending--------------DhivyaG-----------*/

	/*--Starting--------------Rahul Golani-----------*/
	// Updated to non static function by -- Ruchir

	public int registration(Employee employee) throws ClassNotFoundException,
			SQLException {
		Connection connection = DatabaseUtils.getConnection();
		if (isIdExist(employee.getE_id(), connection)) {
			return 1;
		} else if (isEmailExsist(employee.getE_email(), connection)) {
			return 2;
		} else if (isPhoneExsist(employee.getE_phone(), connection)) {
			return 3;
		}
		String sql = "insert into " + EMPLOYEE_TABLE + " values (?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, employee.getE_id());
		ps.setString(2, employee.getE_name());
		ps.setString(3, employee.getE_pwd());
		ps.setString(4, employee.getE_email());
		ps.setLong(5, employee.getE_phone());
		ps.setInt(6, 1);
		int count = ps.executeUpdate();
		if (count > 0) {
			return 0;
		} else {
			return -1;
		}

	}

	private boolean isPhoneExsist(Long e_phone, Connection connection)
			throws SQLException {
		String sql = "select * from " + EMPLOYEE_TABLE + " where e_phone=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, e_phone);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	private boolean isEmailExsist(String e_email, Connection connection)
			throws SQLException {
		String sql = "select * from " + EMPLOYEE_TABLE + " where e_email=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, e_email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	private boolean isIdExist(String id, Connection connection)
			throws SQLException {
		String sql = "select * from " + EMPLOYEE_TABLE + " where e_id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public ArrayList<Item> getAllItems(String vendorId)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Item> itemList = new ArrayList<>();
		Connection connection = DatabaseUtils.getConnection();
		String sql = "Select * from cms_item where v_id=? AND i_status=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, vendorId);
		ps.setInt(2, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Item item = new Item(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getDouble(4), rs.getString(5),
					rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
					rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13),
					rs.getInt(14), rs.getString(15), rs.getInt(16));
			itemList.add(item);
		}

		DatabaseUtils.closeStatement(ps);
		DatabaseUtils.closeConnection(connection);

		return itemList;

	}

	/*--Ending--------------Rahul Golani-----------*/

	/*--Starting--------------Ram Kalugotla-----------*/

	public ArrayList<Vendor> displayVendorList() throws ClassNotFoundException,
			SQLException {
		ArrayList<Vendor> vendorDetails = new ArrayList<Vendor>();
		ResultSet rs = null;
		Vendor vendor = new Vendor();
		Connection connection = DatabaseUtils.getConnection();
		String sql = "select v_id,v_name from" + VENDOR_TABLE
				+ "where v_active=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 1);
		rs = ps.executeQuery();
		while (rs.next()) {
			vendor.setV_id(rs.getString(1));
			vendor.setV_name(rs.getString(2));
			vendorDetails.add(vendor);
		}

		return vendorDetails;
	}

	/*--Ending--------------Ram Kalugotla-----------*/

	/*--Starting--------------Jeet-----------*/
	@Override
	public ArrayList<Vendor> getAllVendors() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
		Connection connection = DatabaseUtils.getConnection();
		String sql = "Select * from cms_vendor where v_active=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Vendor vendor = new Vendor(rs.getString(1), rs.getString(2), null,
					rs.getString(4), rs.getLong(5), rs.getInt(6),
					rs.getString(7));
			vendorList.add(vendor);
		}

		DatabaseUtils.closeStatement(ps);
		DatabaseUtils.closeConnection(connection);

		return vendorList;

	}

	/*--Ending--------------Jeet-----------*/

	/*--Starting--------------Shishir-----------*/
	@Override
	public double getWalletBalance(String e_id, String v_id)
			throws ClassNotFoundException, SQLException

	{
		// TODO Auto-generated method stub
		double money = 0.0;
		ResultSet result = null;
		Connection connection = DatabaseUtils.getConnection();
		String Sql = "SELECT * FROM CMS_WALLET WHERE E_ID=? AND V_ID=?";
		PreparedStatement pStat = connection.prepareStatement(Sql);
		pStat.setString(1, e_id);
		pStat.setString(2, v_id);
		try {
			result = pStat.executeQuery();
			while (result.next()) {
				money = result.getDouble(1);
			}
			return money;

		} finally {
			DatabaseUtils.closeConnection(connection);
		}

	}

	/*--Ending--------------Shishir-----------*/

	/*--Starting--------------Prashanti-----------*/
	@Override
	public boolean addWallet(String e_id, String v_id)
			throws ClassNotFoundException, SQLException {

		Connection connection = DatabaseUtils.getConnection();
		String sql = "insert into cms_wallet values where e_id=? and v_id=?";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, e_id);
		ps.setString(2, v_id);

		try {
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		} finally {
			DatabaseUtils.closeConnection(connection);
		}
	}

	/*--Ending--------------Prashanti-----------*/

	/*--Starting--------------Souvik-----------*/

	public ArrayList<Item> getItems(String v_id) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		Connection connection = DatabaseUtils.getConnection();
		ArrayList<Item> itemList = new ArrayList<Item>();
		try {
			String sql = "select * from cms_item where v_id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, v_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * public Item(int i_id, String v_id, String i_name, double
				 * i_price, String i_type, int i_mo, int i_tu, int i_we, int
				 * i_th, int i_fr, int i_sa, int i_su, int i_status, int
				 * i_update, String comment, int serve_count)
				 */
				itemList.add(new Item(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getDouble(4), rs.getString(5), rs
						.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						rs.getInt(10), rs.getInt(11), rs.getInt(12), rs
								.getInt(13), rs.getInt(14), rs.getString(15),
						40));

			}
			return itemList;
		} finally {
			DatabaseUtils.closeConnection(connection);
		}

	}

	// By shalabh
	@Override
	public ArrayList<Wallet> showWallet(String id)
			throws ClassNotFoundException, SQLException {
		ArrayList<Wallet> walletList = new ArrayList<Wallet>();
		Wallet wallet = null;
		Connection connection = DatabaseUtils.getConnection();
		String sql = "select * from cms_wallet where e_id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			wallet = new Wallet(rs.getString(1), rs.getString(2),
					rs.getDouble(3));
			walletList.add(wallet);

		}

		return walletList;
	}

	/*--Starting--------------Shishir-----------*/
	@Override
	public boolean rechargeWallet(String e_id, String v_id, double amount)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = DatabaseUtils.getConnection();
		try {
			String sql = "updare cms_wallet " + "set money = money +" + amount
					+ "where e_id=? and v_id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, e_id);
			ps.setString(2, v_id);
			int result = ps.executeUpdate();
			if (result > 0)
				return true;
			return false;
		} finally {
			DatabaseUtils.closeConnection(connection);
		}

	}
	/* Ending--------------Shishir----------- */
}
