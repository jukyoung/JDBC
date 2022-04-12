package com.kh.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "kh";
	private String pw = "kh";
	
	public int insert(StudentDTO dto) throws Exception{
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
			
			String sql = "insert into tbl_student values(seq_std.nextval, '" + dto.getName() + "', '"+ dto.getPhone() +"', to_date('" + dto.getBirth_date() +"', 'yyyy/mm/dd'))";
			int rs = stmt.executeUpdate(sql);
			return rs;
		}
	}
	
	public int update(StudentDTO dto) throws Exception{ // no 기준으로
		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
			
			String sql = "update tbl_student set name = '"+ dto.getName() + "', phone = '"+ dto.getPhone() + "', birth_date = + to_date('" + dto.getBirth_date() +"', 'yyyy/mm/dd') where no = " + dto.getNo();
			int rs = stmt.executeUpdate(sql);
			return rs;
			
		}
	}
	
	public int delete(int no) throws Exception{ // no 기준으로
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
				Statement stmt = con.createStatement();) {
			
			String sql = "delete from tbl_student where no = " + no;
			int rs = stmt.executeUpdate(sql);
			return rs;
			
		}
	}
	
	public StudentDTO select(int number) throws Exception{
		
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
				Statement stmt = con.createStatement();){
			
			String sql = "select * from tbl_student where no = " + number;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				int no = rs.getInt(1); 
				String name = rs.getString(2); 
				String phone = rs.getString(3); 
				Date birth_date = rs.getDate(4);
				
				StudentDTO dto = new StudentDTO(no, name, phone, birth_date);
				return dto;
			}
			return null;
		}
	}
	
	public ArrayList<StudentDTO> selectAll() throws Exception{
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
				Statement stmt = con.createStatement();){
			
			String sql = "select * from tbl_student";
			ResultSet rs = stmt.executeQuery(sql);
			
			ArrayList<StudentDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date birth_date = rs.getDate(4);
				
				list.add(new StudentDTO(no, name, phone, birth_date));
			}
			return list;
		}
		
	}
}
