package com.tcs.vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.tcs.data.DatabaseUtils;
import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;

public class VendorDao implements VendorInterface {

//	static Connection con = null;
//	static PreparedStatement ps = null, ps1 = null;
//	static ResultSet rs = null;
	
	//Login of Vendor
	@Override
	public Vendor loginVendor(String v_id, String v_pwd) throws SQLException,
			ClassNotFoundException {
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		try {
			String query = "Select * from cms_vendor where v_id=? and v_pwd=? ";
			ps = connection.prepareStatement(query);
			ps.setString(1, v_id);
			ps.setString(2, v_pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// match found
				Vendor vendor = new Vendor(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getLong(5),
						rs.getInt(6), rs.getString(7));
				return vendor;
			} else {
				// match not found
				return null;
			}
		} finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
		}

	}

	// Adding Item to Menu
	@Override
	public boolean insertItemInMenu(Item item) throws SQLException,
			ClassNotFoundException {

		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		int i = 0;
		try {
			ps = connection.prepareStatement("insert into cms_item values(i_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, item.getV_id());
			ps.setString(2, item.getI_name());
			ps.setDouble(3, item.getI_price());
			ps.setString(4, item.getI_type());
			ps.setInt(5, item.getI_mo());
			ps.setInt(6, item.getI_tu());
			ps.setInt(7, item.getI_we());
			ps.setInt(8, item.getI_th());
			ps.setInt(9, item.getI_fr());
			ps.setInt(10, item.getI_sa());
			ps.setInt(11, item.getI_su());
			ps.setInt(12, item.getI_status());
			ps.setInt(13, item.getI_update());
			ps.setString(14, item.getComment());
			ps.setInt(15, item.getServe_count());
			i = ps.executeUpdate();
			if (i == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
			
		}
		return false;
	}

	// Updating Item Details
	@Override
	public boolean updateItemInMenu(Item it) throws SQLException,
			ClassNotFoundException {
		int id = it.getI_id();
		boolean status=false;
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			connection = DatabaseUtils.getConnection();
			ps = connection.prepareStatement("update cms_item set i_mo=?,i_tu=?,i_we=?,i_th=?,i_fr=?,i_sa=?,i_su=?,serve_count=? where i_id=?");
			ps.setInt(1, it.getI_mo());
			ps.setInt(2, it.getI_tu());
			ps.setInt(3, it.getI_we());
			ps.setInt(4, it.getI_th());
			ps.setInt(5, it.getI_fr());
			ps.setInt(6, it.getI_sa());
			ps.setInt(7, it.getI_su());
			ps.setInt(8, it.getServe_count());
			ps.setInt(9, id);
			if(ps.executeUpdate()<1)
			{
				System.out.println("Error in updating item method");
				return false;
			}


			DatabaseUtils.closeStatement(ps);
			
			ps = connection.prepareStatement("select i_name,i_price from cms_item where i_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			DatabaseUtils.closeStatement(ps);
			
			if (rs.next()) {
				String s1 = rs.getString(1);
				double price = rs.getDouble(2);
				
				if (!(s1.equalsIgnoreCase(it.getI_name()))
						|| (price != it.getI_price())) {
					ps = connection
							.prepareStatement("insert into cms_item_temp values(?,?,?,?,0,null,null)");
					ps.setInt(1, id);
					ps.setString(2, it.getV_id());
					ps.setString(3, it.getI_name());
					ps.setDouble(4, it.getI_price());
					if(ps.executeUpdate()>0)
						status=true;
					else
						status=false;
					
					DatabaseUtils.closeStatement(ps);
					
				} else {
					ps = connection
							.prepareStatement("update cms_item set i_update=1 where i_id=?");
					ps.setInt(1, id);
					
					if(ps.executeUpdate()>0)
						status=true;
					else
						status=false;
				}
			}
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
			
		}
		return status;
	}

	// Search Item using ID BY Poonam(1083015)
	@Override
	public Item searchItemInMenu(String i_name,String vid) throws SQLException,
			ClassNotFoundException {
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			connection = DatabaseUtils.getConnection();

			String query = "Select * from cms_item where i_name like %?% AND v_id=?";
			ps = connection.prepareStatement(query);
			ps.setString(1, i_name);
			ps.setString(1, vid);

			rs = ps.executeQuery();
			rs.next();
			Item item = new Item(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getDouble(4), rs.getString(5),
					rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
					rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13),
					rs.getInt(14), rs.getString(15), rs.getInt(16));
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
		}

	}


	// Display of Menu
	@Override
	public ArrayList<Item> displayMenu(String v_id) throws SQLException,
			ClassNotFoundException {
		System.out.println("dao menu called.");
		ArrayList<Item> list = new ArrayList<Item>();
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			String query = "select * from cms_item where v_id=?";
			ps = connection.prepareStatement(query);
			ps.setString(1, v_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						rs.getInt(10), rs.getInt(11), rs.getInt(12),
						rs.getInt(13), rs.getInt(14), rs.getString(15),
						rs.getInt(16));
				list.add(item);

			}
		} finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
		}
		return list;

	}

	// Deleting Item from Menu
	@Override
	public boolean deleteItemInMenu(int itemID) throws SQLException,
			ClassNotFoundException {
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		try {
			String query = "UPDATE cms_item SET i_status = 0 WHERE i_id = ? ";
			ps = connection.prepareStatement(query);
			ps.setInt(1, itemID);
			int checkStatus = ps.executeUpdate();
			if (checkStatus > 0)
				return true;
			return false;
		} finally {
			DatabaseUtils.closeStatement(ps);
			DatabaseUtils.closeConnection(connection);
		}

	}
	
	public boolean updateVendorProfile(Vendor vendor) throws SQLException,
	ClassNotFoundException{
		
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		try {
			connection = DatabaseUtils.getConnection();
			String query = "Update cms_vendor SET v_name=?, v_email=?, v_phone=?, v_type=?" +
			"WHERE v_id=?";
			
			ps = connection.prepareStatement(query);
			ps.setString(1, vendor.getV_name());
			ps.setString(2, vendor.getV_email());
			ps.setLong(3, vendor.getV_phone());
			ps.setString(4, vendor.getV_type());
			ps.setString(5, vendor.getV_id());
			
			int result = ps.executeUpdate();
			
			if(result==1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	public boolean setSecurityQuestions (String v_id, String answer1,String answer2, String answer3, String answer4) throws SQLException,
	ClassNotFoundException{
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		try {
			connection = DatabaseUtils.getConnection();
			String query = "INSERT into cmms_securityAnswer values (?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, v_id);
			ps.setString(2, answer1);
			ps.setString(3, answer2);
			ps.setString(4, answer3);
			ps.setString(5, answer4);
			
			int result = ps.executeUpdate();
			
			if(result == 1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
		
	}
	
public boolean updateSecurityQuestion(String v_id,String answer1,String answer2,String answer3,String answer4) throws SQLException,
ClassNotFoundException{
		Connection connection = DatabaseUtils.getConnection();
		PreparedStatement ps =null;
		try {
			connection = DatabaseUtils.getConnection();
			String query = "Update cms_securityAnswer set answer1 = ?,answer2 = ?,answer3 = ?,answer4 = ? where u_id = ?";
			ps = connection.prepareStatement(query);
			
			ps.setString(1,answer1);
			ps.setString(2, answer2);
			ps.setString(3,answer3);
			ps.setString(4,answer4);
			ps.setString(5,v_id);
			
			int result = ps.executeUpdate();
			
			if(result == 1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

public HashMap<Integer,Integer> statsByIdInterval(String v_id,java.sql.Date d2,java.sql.Date d4) throws SQLException,ClassNotFoundException
{
	
	HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
	Connection connection = DatabaseUtils.getConnection();
	PreparedStatement ps =null;
	ResultSet rs=null;
	try
	{
	   connection=DatabaseUtils.getConnection();
	   ps=connection.prepareStatement("select i_id,count(*) from cms_transaction_item where t_id in (select t_id from cms_transcation where trunc(t_date)>=TO_DATE(?,'YYYY-MM-DD') and trunc(t_date)<=TO_DATE(?,'YYYY-MM-DD') and t_vid=?) group by i_id");
	   ps.setDate(1,d2);
	   ps.setDate(2,d4);
	   ps.setString(3,v_id);
	   rs=ps.executeQuery();
	   while(rs.next())
	   {
		   Integer i1=new Integer(rs.getInt(1));
		   Integer i2=new Integer(rs.getInt(2));
		   hm.put(i1, i2);
		   
	   }
	   return hm;
	}
	finally
	{
		
	}
	
	
}
public void changePassword()
{
	Vendor vendor=new Vendor();
	
	String initial_password=vendor.getV_pwd();
	
	
	
	
	
	
}




}
