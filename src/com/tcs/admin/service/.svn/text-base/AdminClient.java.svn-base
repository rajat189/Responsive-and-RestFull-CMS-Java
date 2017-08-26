package com.tcs.admin.service;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.tcs.admin.bean.Admin;
import com.tcs.admin.bean.ItemVerifyData;
import com.tcs.employee.bean.Employee;
import com.tcs.vendor.bean.Vendor;

public class AdminClient {
	
	static Client client;
	static WebResource webresource;
	static ClientResponse response;
	
	public static void main(String[] args) {
		get_Admin();
		//get_Vendor();

		//delete_Vendor("V10003");

		//add_Admin(new Admin("123456", "Atul Kataria", "atul@tcs.com",9647897037L));

		//delete_Vendor("V10003");
		/*Vendor vendor=new Vendor("Mousumi","mousumi@gmail.com",1234056789,"veg");
		add_vendor(vendor);*/
		//delete_Vendor("V10003");

		//Vendor vendor=new Vendor("Atul","atul@gmail.com",9876543210L,"Veg");
		//add_vendor(vendor);
		//delete_Vendor("V10018");
		//get_Vendor_By_Id("V10007");
		//get_Admin_By_Id("A1120799");
		//get_Employee_By_Id("1096");
		
	}
	
	public static void add_vendor(Vendor vendor)
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/AddVendor";	
		client = Client.create();
		webresource = client.resource(url);
		response = webresource.entity(vendor)
				.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		
		if (response.getStatus()!=200) 
		{
			System.err.println("DataBase Error");
		} 
		else
		{
			  String id = response.getEntity(String.class);
			  System.out.println(id);
		}
	}
	
	public static void get_Vendor()
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/GetVendors";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) 
		{
			System.err.println("DataBase Error");
		} 
		
		else if (response.getStatus() == Response.Status.OK.getStatusCode()) 
		{
			ArrayList<Vendor> vendorList = response.getEntity(new GenericType<ArrayList<Vendor>>() {});
			for (Vendor v : vendorList) 
			{
				System.out.println(v.getV_name());
			}
		} 
		else 
		{
			System.out.println("Unacceptable Error");
		}
	}
	public static void get_Admin()
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/GetAdmin";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (response.getStatus()!=200) {
			System.err.println("DataBase Error");
		} else{
			ArrayList<Admin> adminList = response
					.getEntity(new GenericType<ArrayList<Admin>>() {
					});

			for (Admin admin : adminList) {
				System.out.println(admin.getA_name());
			}
		} 
	}
	
	public static void delete_Vendor(String id)
	{  /*sid*/
		String url="http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/DeleteVendor/"+id;
		client=Client.create();
		webresource=client.resource(url);
		response=webresource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		
		if (response.getStatus() != 200) 
		{
			System.err.println("Unable to connect to the server");
		}
		else 
		{
			System.out.println("Vendor is deleted");
		}
	}
	
	public static void get_Items()
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/getItems";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (response.getStatus()!=200) {
			System.err.println("DataBase Error");
		} else{
			ArrayList<ItemVerifyData> itemList = response
					.getEntity(new GenericType<ArrayList<ItemVerifyData>>() {
					});

			for (ItemVerifyData item : itemList) {
				System.out.println(item.getI_name());
			}
		} 
	}
	
	public static void add_Admin(Admin admin)
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/AddAdmin";
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse resp = webResource.entity(admin).accept(MediaType.APPLICATION_JSON)
				.entity(admin, MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(resp.getStatus() != 200 )
		{
			System.err.println("Database Error");
		}
		else
		{
			System.out.println(resp.getEntity(String.class));
		}
	}
	
	public static void get_Vendor_By_Id(String v_id)
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/GetVendorById";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource.entity(v_id).
				accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if(response.getStatus() != 200 ){
			System.err.println("Database Error");
		}
		else{
			System.out.println(response.getEntity(Vendor.class));
		}
	}
	
	
	
	public static void get_Admin_By_Id(String a_id)
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/GetAdminById";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource.entity(a_id).
				accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200 ){
			System.err.println("Database Error");

		}
		else{
			System.out.println(response.getEntity(Admin.class).getA_name());
		}
	}
	
	public static void get_Employee_By_Id(String e_id)
	{
		String url = "http://localhost:8080/CMS_RestfullWebServiceProject/rest/AdminService/GetEmployeeById";
		client = Client.create();
		webresource = client.resource(url);
		response = webresource.entity(e_id).
				accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200 ){
			System.err.println("Database Error");
		}
		else{
			System.out.println(response.getEntity(Employee.class).getE_name());
		}
	}
	
}
