package com.tcs.employee.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.tcs.employee.bean.ChangePwd;
import com.tcs.employee.bean.Employee;
import com.tcs.employee.bean.ErrorMessage;
import com.tcs.employee.bean.Login;
import com.tcs.employee.bean.Transaction;
import com.tcs.employee.bean.Wallet;
import com.tcs.employee.dao.EmployeeDao;
import com.tcs.employee.dao.EmployeeInterface;
import com.tcs.vendor.bean.Item;
import com.tcs.vendor.bean.Vendor;

@Path("/EmployeeService")
public class EmployeeService {

	EmployeeInterface dao = new EmployeeDao();

	/*--Starting--------------Amit Desai-----------*/

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginEmployee(Login login) {

		Employee employee = null;
		Response response = null;
		try {

			employee = dao.login(login);
			System.out.println(employee);
			if (employee != null) {
				if (employee.getE_active() == 1) {
					response = Response.ok().entity(employee).build();
				} else {
					response = Response.status(202)
							.entity(new ErrorMessage("Employee is inactive"))
							.build();
				}
			} else {
				response = Response
						.status(201)
						.entity(new ErrorMessage(
								"The username or password is incorrect"))
						.build();
			}
		} catch (Exception e) {
			response = Response.status(Status.CONFLICT)
					.entity(new ErrorMessage("Some error occour at server"))
					.build();
		}
		return response;

	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee emp) {
		Response response = null;
		ErrorMessage errorMessage = new ErrorMessage();
		int code = 0;
		try {
			int result = dao.update(emp);
			switch (result) {
			case 0:
				return Response.ok().entity(dao.getEmployee(emp.getE_id()))
						.build();
			case 1:
				code = 201;
				errorMessage.setMessage("Employee email already exsist");
				break;
			case 2:
				code = 201;
				errorMessage.setMessage("Employee phone number already exsist");
				break;
			case 3:
				code = 201;
				errorMessage.setMessage("Please enter the correct password");
				break;
			
			default:
				code = Response.Status.CONFLICT.getStatusCode();
				errorMessage.setMessage("DataBase Error");
				break;
			}
			response = Response.status(code).entity(errorMessage).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.CONFLICT).build();
		}
		return response;
	}

	@POST
	@Path("/changePwd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePwd(ChangePwd changePwd) {
		Response response = null;
		String result = "";
		int code=200;
		try {
			if (dao.changePwd(changePwd)){
				result = "Sucessfully changed";
			}
			else{
				result = "Error during changing password";
				code=201;
			}
			response = Response.status(code).entity(new ErrorMessage(result)).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.CONFLICT)
					.entity(new ErrorMessage("Some error occour at the server")).build();
		}
		return response;
	}

	/*--Ending--------------Amit Desai-----------*/

	/*--Starting--------------Rahul Golani-----------*/

	@POST
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerEmployee(Employee employee) {

		Response response = null;

		try {
			int result = dao.registration(employee);
			ErrorMessage errorMessage = new ErrorMessage();
			int code = 200;
			switch (result) {
			case 0:
				errorMessage.setMessage("Employee is registered succesfully");
				break;
			case 1:
				code = 201;
				errorMessage.setMessage("Employee Id already exsist");
				break;
			case 2:
				code = 201;
				errorMessage.setMessage("Employee email already exsist");
				break;
			case 3:
				code = 201;
				errorMessage.setMessage("Employee phone number already exsist");
				break;
			default:
				errorMessage.setMessage("DataBase Error");
				break;
			}
			response = Response.status(code).entity(errorMessage).build();

		} catch (ClassNotFoundException | SQLException e) {
			response = Response.status(Status.CONFLICT)
					.entity(new ErrorMessage("Some error occour at server"))
					.build();
		}
		return response;
	}

	@GET
	@Path("/get_items/{vendor_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerEmployee(@PathParam("vendor_id") String vendorId) {

		Response response = null;

		try {
			ArrayList<Item> itemList = dao.getItems(vendorId);
			System.out.println(itemList);
			if (itemList != null) {
				if (itemList.size() > 0) {
					response = Response.status(Status.OK).entity(itemList)
							.build();
				} else {
					response = Response.status(201)
							.entity(new ErrorMessage("No items found")).build();
				}
			} else {
				response = Response.status(Status.CONFLICT)
						.entity(new ErrorMessage("Some error occurred"))
						.build();
			}
		} catch (ClassNotFoundException | SQLException e) {
			response = Response.status(Status.CONFLICT)
					.entity(new ErrorMessage("Some error occurred")).build();
		}
		return response;
	}

	/*--Ending--------------Rahul Golani-----------*/

	/*--Starting--------------Ram Kalugotla-----------*/

	@GET
	@Path("/displayVendor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayVendor() {
		Response response = null;
		// System.out.println("I m called");
		try {
			ArrayList<Vendor> vendorList = dao.getAllVendors();

			if (vendorList.size() > 0) {
				response = Response.status(Status.OK).entity(vendorList)
						.build();
			} else {
				response = Response.status(201)
						.entity(new ErrorMessage("No Vendor Found")).build();
			}

		} catch (ClassNotFoundException | SQLException e) {
			response = Response.status(Status.CONFLICT)
					.entity("Some error occured").build();
		}
		return response;
	}

	/*--Ending--------------Ram Kalugotla-----------*/

	/*--Starting--------------Shalabh-----------*/

	@POST
	@Path("/showWallet")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response showWallet(String id) {
		Response response = null;

		try {
			ArrayList<Wallet> walletList = dao.showWallet(id);

			if (walletList != null) {
				response = Response.status(Status.OK).entity(walletList)
						.build();
			} else {
				response = Response.status(Status.BAD_REQUEST)
						.entity("Unable to display wallet List").build();
			}

		} catch (ClassNotFoundException | SQLException e) {
			response = Response.status(Status.CONFLICT)
					.entity("Some error occured").build();
		}
		return response;
	}

	/*--Ending--------------Shalabh-----------*/

	/*--Starting--------------DhivyaG-----------*/

	@POST
	@Path("/transaction")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ShowTransaction(String id) {

		Response response = null;
		ArrayList<Transaction> trans = null;
		try {

			trans = dao.ShowTransaction(id);
			if (trans != null) {
				response = Response.status(Status.OK)
						.entity("Transaction is done succesfully").build();
			} else {
				response = Response.status(Status.BAD_REQUEST)
						.entity("Unable to do Transaction").build();
			}
		} catch (ClassNotFoundException | SQLException e) {
			response = Response.status(Status.CONFLICT)
					.entity("Some error occour").build();
		}
		return response;
	}

	/*--Ending--------------DhivyaG-----------*/

	/*--starting--------------souuvik-----------*/

	@POST
	@Path("/recharge")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rechargeWallet(String e_id, String v_id, double amount) {
		Response response = null;
		String result = "";
		try {
			if (dao.rechargeWallet(e_id, v_id, amount))
				result = "Recharge Sucessfull";
			else
				result = "Error during Recharge";
			response = Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.CONFLICT).build();
		}
		return response;
	}

	/*--Ending--------------souvik-----------*/

}
