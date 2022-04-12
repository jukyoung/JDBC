package com.kh.cafe;

import java.text.SimpleDateFormat;

public class CafeDTO {
	
	private int product_id;
	private String product_name;
	private int price;
	private String register_date;
	
	public CafeDTO(int product_id, String product_name, int price, String register_date) {
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

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	@Override
	public String toString() {
		return  product_id + " : " + product_name + " : " + price
				+ " : " + register_date + " : " + getTimestamp();
	}
	int sec = 60;
	int min = 60;
	int hour = 24;
	int day = 30;
	int month = 12;
	
	// register_date 멤버필드를 이용해서 현재시간과 등록시간의 차이를 계산해 String값으로 반환해주는 메서드
	public String getTimestamp() {
		// System.currentTimeMillis() -> 현재 시간이 long형으로 반환됨
		// register_date를 먼저 자바의 Date 타입으로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss"); // register_date 가 생긴형태로 집어넣기
		try {
		
		// sdf의 형식처럼 생긴 register_date String 값을 자바의 Date형으로 변환하는 작업
		java.util.Date date = sdf.parse(this.register_date);
		// getTime : 자바의 date 타입 데이터를 long 형으로 변환해서 반환해주는 메서드
		long origin_date = date.getTime(); // 등록일을 long형으로 변환
		long cur_date = System.currentTimeMillis(); // 현재 시간
		
		// 두 시간간의 갭 -> 초 단위로 계산
		long gap_time = (cur_date - origin_date) / 1000; // 밀리세컨드니까 초단위로 만드려면 1000으로 나누기
		
		// 1분 미만이라고 한다면 '~초 전'
		if(gap_time < sec) {
			return gap_time + "초 전";
		}else if((gap_time/= sec) < min) { // 1시간 미만이라고 한다면 '~분 전'
			// 1분 -> 60초
			// 1시간 -> 60분
			// 나눔과 동시에 그 값을 gap_time에 대입
			return gap_time + "분 전";
		}else if((gap_time/= min) < hour) { // 1일 미만이라면 '~시간 전'
			// gap_time은 60 이상의 값을 가지고 있을 거다.
			// 60분 기준으로 분 단위 / 1시간으로 나눴을때 24보다 작아야함
			// 1일 -> 24시간
			return gap_time + "시간 전";
		}else if((gap_time/= hour) < day) { // 30일 미만이라면 '~일 전'
			return gap_time + "일 전";
		}else if((gap_time/= day) < month) { // 12개월 미만이라면 '~개월 전'
			return gap_time + "개월 전";
		}
	
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

}
