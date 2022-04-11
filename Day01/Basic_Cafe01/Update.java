package com.kh.cafe;

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
			// 1번 메뉴의 이름을 '바닐라라떼', 가격을 4500 으로 변경
			String sql = "update cafe set product_name = '바닐라라떼', price = 4500 where product_id = 1";
			
			int rs = stmt.executeUpdate(sql);
			if(rs > 0) {
				System.out.println("데이터 수정 성공");
			}else {
				System.out.println("데이터 수정 실패");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
