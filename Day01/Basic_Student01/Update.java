package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			
			String sql = "update tbl_student set name = '김영수', phone = '010-1234-1234', birth_date = to_date('1985/12/20', 'yyyy/mm/dd') where no = 1";
			
			int rs = stmt.executeUpdate(sql);
			if(rs > 0) {
				System.out.println("데이터 수정 완료");
			}else {
				System.out.println("데이터 수정 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
