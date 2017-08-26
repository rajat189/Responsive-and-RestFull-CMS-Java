package com.tcs.tcs;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.owlike.genson.Genson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.tcs.employee.bean.Employee;
import com.tcs.employee.bean.ErrorMessage;
import com.tcs.employee.bean.Login;
import com.tcs.vendor.bean.Vendor;

public class ClientEx {
	
	public static void main(String[] args) {
		Client restClient = Client.create();
		WebResource webResource = restClient
				.resource("http://localhost:8080/CMS_RestfullWebServiceProject/rest/EmployeeService/login");
		ClientResponse resp = webResource
				.accept(MediaType.APPLICATION_JSON)
				.entity(new Login(103 + "", "pwd"),
						MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if (resp.getStatus() != 200) {
			System.out.println(resp.getEntity(ErrorMessage.class));
		} else {
			System.out.println("Login Sucessfull");
		}
		
		
		
		Client restClient2 = Client.create();
		WebResource webResource2 = restClient2
				.resource("http://localhost:8080/CMS_RestfullWebServiceProject/rest/EmployeeService/displayVendor");
		ClientResponse resp2 = webResource2
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		if (resp2.getStatus() != 200) {
			System.out.println(resp2.getStatus());
		} else {
			System.out.println(resp2.getEntity(new GenericType<ArrayList<Vendor>>(){}));
		}
	}
}
