package com.kh.date01;

import java.sql.Date;

public class MemberDTO {
	// id, pw, birth_date-> oracle date
	private String id;
	private String pw;
	private String birth_date;
	
	public MemberDTO() {};
	public MemberDTO(String id, String pw, String birth_date) {
		super();
		this.id = id;
		this.pw = pw;
		this.birth_date = birth_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	
	

}
