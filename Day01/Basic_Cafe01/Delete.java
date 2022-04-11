package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Delete {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		// Connection / Statement 객체 생성
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			// product_id 가 2인 데이터를 삭제
			String sql = "delete from cafe where product_id = 2";
			
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) {
				System.out.println("데이터 삭제 성공");
			}else {
				System.out.println("데이터 삭제 실패");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
