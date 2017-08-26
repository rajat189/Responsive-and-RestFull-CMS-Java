package com.tcs.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tcs.admin.bean.Admin;
import com.tcs.admin.bean.ItemVerifyData;

import com.tcs.data.DatabaseUtils;
import com.tcs.employee.bean.Employee;
import com.tcs.vendor.bean.Vendor;

public class AdminDao {

	Connection connection;
	PreparedStatement preparedStatement;

	public Admin login(String id, String pwd) throws ClassNotFoundException,
	SQLException {
		Admin admin = null;
		String query = "SELECT * FROM CMS_ADMIN WHERE A_ID = ? AND A_PWD = ? AND A_ACTIVE=1";
		try {
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pwd);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				admin = new Admin(result.getString(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getLong(5));
			}
			return admin;
		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	public String addVendor(Vendor vendor) throws ClassNotFoundException,
	SQLException {

		String vendor_id = countVendors();
		String query = "INSERT INTO CMS_VENDOR (v_id ,v_name ,v_email ,v_phone,v_active ,v_type) "
				+ "VALUES(?,?,?,?,?,?)";

		int i = 0;
		try {
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, vendor_id);
			preparedStatement.setString(2, vendor.getV_name());
			preparedStatement.setString(3, vendor.getV_email());
			preparedStatement.setLong(4, vendor.getV_phone());
			preparedStatement.setInt(5, 1);
			preparedStatement.setString(6, vendor.getV_type());

			i = preparedStatement.executeUpdate();
			if (i > 0)
				return vendor_id;
		}

		finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
		return null;
	}

	public String countVendors() throws SQLException, ClassNotFoundException {
		/* Saurabh */
		String query = "SELECT COUNT(V_ID) FROM CMS_VENDOR ";
		Statement st = null;
		int j = 0;
		try {
			connection = DatabaseUtils.getConnection();
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				j = rs.getInt(1);
			}
			return ("V" + (10000 + j + 1));
		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* starting----------------Saurabh-------------- */

	public ArrayList<Vendor> getVendors(int lowerlimit,int upperlimit) throws ClassNotFoundException,
	SQLException {
		/* Saurabh */
		ArrayList<Vendor> vendorList = new ArrayList<>();
		String query = "SELECT * FROM CMS_VENDOR WHERE V_ACTIVE=1 ORDER BY V_ID";
		int count=0,count1=0;

		try {
					connection = DatabaseUtils.getConnection();
					preparedStatement = connection.prepareStatement(query);
					ResultSet resultset = preparedStatement.executeQuery();
					while (resultset.next() && count<=upperlimit-lowerlimit) 
					{
						if(count1<lowerlimit-1)
						{
							count1++;
							continue;
						}
						vendorList.add(new Vendor(resultset.getString(1), resultset
						.getString(2), resultset.getString(4), resultset.getLong(5), resultset.getString(7)));
						count++;
					}
			
				return vendorList;
			}
		
			finally 
			{
				DatabaseUtils.closeConnection(connection);
				DatabaseUtils.closeStatement(preparedStatement);
			}
	}

	/* ending------------------Saurabh------------------ */
	
	/* starting----------RabiJha--------------- */
	public ArrayList<Admin> getAdmin(int lowerlimit,int upperlimit) throws ClassNotFoundException,
	SQLException {
		ResultSet resultset = null;//
		String query = "SELECT * FROM CMS_ADMIN WHERE A_ACTIVE=1 ORDER BY A_ID";
		ArrayList<Admin> adminList = new ArrayList<>();
		Admin admin = null;
		int count=0,count1=0;

		try 
		{
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);

			resultset = preparedStatement.executeQuery();

			while (resultset.next() && count<=upperlimit-lowerlimit) 
			{
				
				if(count1<lowerlimit-1)
				{
					count1++;
					continue;
				}
				
				adminList.add(new Admin(resultset.getString(1),resultset.getString(2),
						resultset.getString(4), resultset.getLong(5)));
				count++;
				
			}
			return adminList;
		} 
		
		finally 
		{
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending----------RabiJha--------------- */

	/*
	 * public int updateVendor(Vendor vendor) throws ClassNotFoundException,
	 * SQLException { String query=
	 * "UPDATE CMS_VENDOR SET v_name=?,v_pwd=?,v_email=?,v_phone=? WHERE V_ID=?"
	 * ;
	 * 
	 * try { connection=DatabaseUtils.getConnection();
	 * preparedStatement=connection.prepareStatement(query);
	 * preparedStatement.setString(1,vendor.getV_name());
	 * preparedStatement.setString(2,vendor.getV_pwd());
	 * preparedStatement.setString(3,vendor.getV_email());
	 * preparedStatement.setLong(4,vendor.getV_phone());
	 * preparedStatement.setString(5,vendor.getV_id()); int
	 * i=preparedStatement.executeUpdate(); return i; } finally {
	 * DatabaseUtils.closeConnection(connection);
	 * DatabaseUtils.closeStatement(preparedStatement); } }
	 */


	/* starting------------Sidhhartha--------------- */

	public ArrayList<String> deleteVendor(ArrayList<String> vendor_id) throws SQLException,

	ClassNotFoundException {
		
		  String query = "UPDATE CMS_VENDOR SET V_ACTIVE=0 WHERE V_ID=?";
		  String query1 = "SELECT T_STATUS FROM CMS_TRANSCATION WHERE T_VID = ?";
		  ArrayList<String> deleteVendors = new ArrayList<String>();
		  ArrayList<String> cannotDeleteVendors = new ArrayList<String>();
		  String status = null;
		  try{
		  connection = DatabaseUtils.getConnection();
		  PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
		  for(String vid : vendor_id){
			 boolean flag = true;
			 preparedStatement1.setString(1, vid);
		     ResultSet rs = preparedStatement1.executeQuery();
		     if(rs.next()){
		    	 status = rs.getString(1);
		    	 //System.out.println(status);
		    	 if(status.equalsIgnoreCase("pending")){
		    		 cannotDeleteVendors.add(vid);
		    		 flag = false;
		    	 }
		    	 while(rs.next()){
		    		 status = rs.getString(1);
			    	// System.out.println(status);
			    	 if(status.equalsIgnoreCase("pending")){
			    		 cannotDeleteVendors.add(vid);
			    		 flag = false;
			    		 break;
			    	 }
		    	 }
		    	 
		    	 if(flag)
		    		 deleteVendors.add(vid);
		     }
		     else{
		    	 deleteVendors.add(vid);
		     }
		  }
		  preparedStatement = connection.prepareStatement(query);
		  for(String v_Id : deleteVendors){
			  preparedStatement.setString(1, v_Id);
			  preparedStatement.executeUpdate();
		  }
		  return cannotDeleteVendors;
		  }
		  finally {
				DatabaseUtils.closeConnection(connection);
				DatabaseUtils.closeStatement(preparedStatement);
			}
	}
	/* ending------------Sidhhartha--------------- */

	/* starting----------Samrat------------------ */

	public String addAdmin(Admin admin) throws ClassNotFoundException,
	SQLException {
		String query = "INSERT INTO CMS_ADMIN (A_ID, A_NAME, A_EMAIL, A_PHONE) VALUES (?,?,?,?)";
		String admin_id = null;
		try {
			admin_id = "A" + admin.getA_id();
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, admin_id);
			preparedStatement.setString(2, admin.getA_name());
			preparedStatement.setString(3, admin.getA_email());
			preparedStatement.setLong(4, admin.getA_phone());
			int i = preparedStatement.executeUpdate();
			if (i == 1)
				return admin_id;

		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}

		return admin_id;
	}
	/* ending------------Samrat--------------- */

	/* starting------------venumallik--------------- */

	public ArrayList<ItemVerifyData> itemDisplayForVerification() throws SQLException,
	ClassNotFoundException { 
		ResultSet resultset = null;
		ItemVerifyData item = new ItemVerifyData();
		ArrayList<ItemVerifyData> listItems=new ArrayList<ItemVerifyData>();
		String query = "select i_id,v_id,i_name,i_price,i_status,i_update from CMS_ITEM where i_status=0 or i_update=1";
		try

		{
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);

			resultset = preparedStatement.executeQuery();
			preparedStatement = null;

			while (resultset.next()) {
				item.setI_id(resultset.getInt(1));
				item.setV_id(resultset.getString(2));
				item.setI_name(resultset.getString(3));
				item.setI_price(resultset.getFloat(4));
				item.setI_update(resultset.getInt(6));
				item.setI_status(resultset.getInt(5));
				addToItemTemp(item);
				listItems.add(item);
			}
		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}

		return listItems;
	}

	/* ending------------venumallik--------------- */

	/* starting------------venumallik--------------- */

	private void addToItemTemp(ItemVerifyData item)
			throws ClassNotFoundException, SQLException {
		// VenuMallik
		String query = "insert into CMS_ITEM_TEMP values(?,?,?,?,?,?,?)";
		try {
			connection = DatabaseUtils.getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, item.getI_id());
			preparedStatement.setString(2, item.getV_id());
			preparedStatement.setString(3, item.getI_name());
			preparedStatement.setFloat(4, item.getI_price());
			preparedStatement.setInt(5, item.getI_update());
			if (item.getI_status() == 0)
				preparedStatement.setString(6, "New Item");
			else
				preparedStatement.setString(6, "For Update Permission");
			preparedStatement.setString(7, "	");

			preparedStatement.executeUpdate();

		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}

	}

	/* ending------------venumallik--------------- */

	/* starting------------venumallik--------------- */

	public boolean itemTempUpdateAfterVerification(ItemVerifyData itemVD)
			throws SQLException, ClassNotFoundException { // VenuMallik
		String query = null;
		connection = DatabaseUtils.getConnection();
		// query="UPDATE CMS_ITEM_TEMP SET i_reject_status=?,i_reject_cmt=?,i_update_cmt=? where i_id=?";
		if (itemVD.getI_status() == 1 && itemVD.getI_reject_status()== 0)
			query = "UPDATE CMS_ITEM SET i_update=2,i_status=1 where i_id="
					+ itemVD.getI_id() + "";
		if(itemVD.getI_status() == 0 && itemVD.getI_reject_status()== 1){
			query = "UPDATE CMS_ITEM_TEMP SET i_reject_status=1,i_reject_cmt=?,i_update_cmt=? where i_id=?";

		}
		try {

			preparedStatement = connection.prepareStatement(query);
			if (itemVD.getI_status() == 0 && itemVD.getI_reject_status()== 1) {
				preparedStatement.setString(1, itemVD.getI_reject_cmt());
				preparedStatement.setString(2, itemVD.getI_update_cmt());
				preparedStatement.setInt(3, itemVD.getI_id());
				itemVD.setI_update(3);
				updateItemRequestByVendor(itemVD);
			}
			int i = preparedStatement.executeUpdate(query);
			if (i > 0)
				return true;
			return false;

		} finally {
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending------------venumallik--------------- */

	

	/* starting----------venumallik--------------- */

	public boolean updateItemRequestByVendor(ItemVerifyData itemVD) throws ClassNotFoundException,
	SQLException 
	{
		
		String query = "update cms_item set i_update="+itemVD.getI_update()+" where i_id=? and v_id=?";
		try 
		{
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,itemVD.getI_id());
			preparedStatement.setString(2,itemVD.getV_id());
			int b= preparedStatement.executeUpdate();
			if (b<=0)
				return false;
			return true;
		} 
		finally 
		{
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending----------venumalik--------------- */

	/* starting----------Saurabh--------------- */

	public Vendor getVendorById(String vendorId) throws SQLException, ClassNotFoundException
	{
		Vendor vendor=null;
		String query="SELECT * FROM CMS_VENDOR WHERE V_ID=? AND V_ACTIVE=1";
		try
		{
			connection=DatabaseUtils.getConnection();
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1,vendorId);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next())
			{
				vendor=new Vendor(resultset.getString(1),resultset.getString(2),resultset.getString(4),resultset.getLong(5),resultset.getString(7));
			}
			return vendor;
		}
		finally
		{
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending----------Saurabh--------------- */

	/* starting----------RabiJha--------------- */

	public Admin getAdminById(String adminId) throws ClassNotFoundException, SQLException
	{
		String query = "SELECT * FROM CMS_ADMIN WHERE a_id = ? AND A_ACTIVE=1";
		Admin admin = null;
		ResultSet resultset = null;

		try 
		{
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, adminId);

			resultset = preparedStatement.executeQuery();

			if(resultset.next())
			{
				admin = new Admin(resultset.getString(1),resultset.getString(2), resultset.getString(4), resultset.getLong(5));
			}
			return admin;

		}
		finally
		{
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending----------RabiJha--------------- */

	/* starting----------Sidhhartha--------------- */


	public Employee getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException
	{
		String query = "SELECT * FROM CMS_EMPLOYEE WHERE e_id = ? AND E_ACTIVE=1";
		Employee employee = null;
		ResultSet resultset = null;

		try 
		{
			connection = DatabaseUtils.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, employeeId);

			resultset = preparedStatement.executeQuery();

			if(resultset.next())
			{
				employee = new Employee(resultset.getString(1),resultset.getString(2),resultset.getString(4),resultset.getLong(5));
			}
			return employee;

		}
		finally
		{
			DatabaseUtils.closeConnection(connection);
			DatabaseUtils.closeStatement(preparedStatement);
		}
	}

	/* ending----------Sidhhartha--------------- */
}
