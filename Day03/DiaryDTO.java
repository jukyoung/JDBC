package com.kh.member;

public class DiaryDTO {
	private int no;
	private String date;
	private String title;
	private String content;
	
	public DiaryDTO() {};
	public DiaryDTO(int no, String date, String title, String content) {
		super();
		this.no = no;
		this.date = date;
		this.title = title;
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return  no + "\t" + date + "\t   " + title + "\t" + content;
	}
	
	

}
