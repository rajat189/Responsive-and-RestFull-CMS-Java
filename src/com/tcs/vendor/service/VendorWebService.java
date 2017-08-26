package com.tcs.vendor.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tcs.employee.bean.Employee;
import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;
import com.tcs.vendor.dao.VendorDao;

@Path("/VendorWebService")
public class VendorWebService {
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginVendor(Vendor vendor) {

		VendorDao ob = new VendorDao();
		Response response = null;
		try {
			System.out.println("web service invoked");
			vendor = ob.loginVendor(vendor.getV_id(), vendor.getV_pwd());
			if (vendor == null)
			{
				System.out.println("vendor found in web service");
				response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(null)
						.build();
			}	
			else{
				System.out.println("vendor not found in web service");
				response = Response.ok().entity(vendor).build();
			}
				
		} catch (Exception e) {
			System.out.println("exception at login in web service");
			response = Response.status(Response.Status.CONFLICT).entity("vendor not found").build();
		}
		return response;

	}

	@POST
	@Path("/addItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertItemInMenu(Item item) {
		System.out.println("in the web service");
		boolean status = false;
		Response response = null;
		VendorDao ob = new VendorDao();
		try {
			status = ob.insertItemInMenu(item);

			if (status == false) {
				System.out.println("addint item failed in service");
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else
				response = Response.ok().entity(status).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
		return response;
	}

	@POST
	@Path("/updateItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItemInMenu(Item item) {
		boolean status = false;
		Response response = null;
		VendorDao ob = new VendorDao();
		try {
			status = ob.updateItemInMenu(item);

			if (status == false) {
				System.out.println("update item failed in service");
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else
				response = Response.ok().entity(status).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

	@POST
	@Path("/searchItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchItemInMenu(String i_name,String vid) {
		Item item = null;
		Response response = null;
		VendorDao ob = new VendorDao();
		try {
			item = ob.searchItemInMenu(i_name,vid);

			if (item == null) {
				System.out.println("search item failed in service");
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else
				response = Response.ok().entity(item).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

	@POST
	@Path("/menu")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMenu(String vendor_id) {
		ArrayList<Item> itemList = null;
		Response response = null;
		VendorDao ob = new VendorDao();
		System.out.println("web server (menu) called..");
		try {
			itemList = ob.displayMenu(vendor_id);

			if (itemList == null) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else
				response = Response.ok().entity(itemList).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.CONFLICT).build();
		}
		return response;

	}

	@POST
	@Path("/deleteItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteItem(int itemID) {
		boolean status = false;
		Response response = null;
		VendorDao ob = new VendorDao();
		try {
			status = ob.deleteItemInMenu(itemID);

			if (status == false) {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			} else
				response = Response.ok().entity(status).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
	
	

}
