package com.tcs.admin.bean;

public class ItemVerifyData {
	private int i_id;
	private int i_status;
	private int i_update;
	private int serve_count;
	private String v_name;
	private String v_id;
	private float i_price;
	
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	private String i_name;
	private String i_reject_cmt;
	private int i_reject_status;
	private String i_update_cmt;
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	
	public float getI_price() {
		return i_price;
	}
	public void setI_price(float i_price) {
		this.i_price = i_price;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getI_reject_cmt() {
		return i_reject_cmt;
	}
	public void setI_reject_cmt(String i_reject_cmt) {
		this.i_reject_cmt = i_reject_cmt;
	}
	public int getI_reject_status() {
		return i_reject_status;
	}
	public void setI_reject_status(int i_reject_status) {
		this.i_reject_status = i_reject_status;
	}
	public String getI_update_cmt() {
		return i_update_cmt;
	}
	public void setI_update_cmt(String i_update_cmt) {
		this.i_update_cmt = i_update_cmt;
	}
	public ItemVerifyData(int i_id, String v_id, float i_price, String i_name,
			String i_reject_cmt, int i_reject_status, String i_update_cmt) {
		super();
		this.i_id = i_id;
		this.v_id = v_id;
		this.i_price = i_price;
		this.i_name = i_name;
		this.i_reject_cmt = i_reject_cmt;
		this.i_reject_status = i_reject_status;
		this.i_update_cmt = i_update_cmt;
	}
	public ItemVerifyData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getV_id() {
		return v_id;
	}
	public int getI_status() {
		return i_status;
	}
	public void setI_status(int i_status) {
		this.i_status = i_status;
	}
	public int getI_update() {
		return i_update;
	}
	public void setI_update(int i_update) {
		this.i_update = i_update;
	}
	public int getServe_count() {
		return serve_count;
	}
	public void setServe_count(int serve_count) {
		this.serve_count = serve_count;
	}
	public String getV_name() {
		return v_name;
	}
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}


}
