package com.kh.cafe;

import java.sql.Date;

public class CafeDTO {
	
	private int product_id;
	private String product_name;
	private int price;
	private Date register_date;
	
	public CafeDTO(int product_id, String product_name, int price, Date register_date) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.register_date = register_date;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	@Override
	public String toString() {
		return  product_id + " : " + product_name + " : " + price
				+ " : " + register_date ;
	}
	
	

}
