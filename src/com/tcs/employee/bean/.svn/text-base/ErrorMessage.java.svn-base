package com.tcs.employee.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String message;
	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ErrorMessage(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	@Override
	public String toString() {
		return "ErrorMessage :" + message;
	}

}
