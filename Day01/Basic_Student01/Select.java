package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) {
		
		// 1. 1번 학생의 데이터만 가져와서 출력해보기
		// 2. 전체 학생의 데이터만 가져와보기
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			String sql = "select * from tbl_student where no = 1";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println(rs.getInt(1)
						+ " : " + rs.getString(2)
						+ " : " + rs.getString(3)
						+ " : " + rs.getDate(4));
			}
			
			String sql2 = "select * from tbl_student";
			ResultSet rs2 = stmt.executeQuery(sql2);
			
			while(rs2.next()) {
				System.out.println(rs2.getInt(1)
						+ " : " + rs2.getString(2)
						+ " : " + rs2.getString(3)
						+ " : " + rs2.getDate(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
