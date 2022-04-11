package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			
			// product_id 가 1번인 메뉴 정보만 가져오기
			/*String sql = "select * from cafe where product_id = 1";
			// executeQuerty() 를 통해 반환받는 결과 -> ResultSet(질의결과)
			ResultSet rs = stmt.executeQuery(sql); // import 해주기			
			System.out.println("rs : " + rs); // ResultSet의 주소값 반환
			// ResultSet이 반환될 때 커서라는 개념이 존재
			// 이 커서는 가장 첫 번째 행의 바로 위쪽을 가리키고 있음
			// 첫 번째 행을 가리키고 싶으면 커서를 내려줘야 함 -> rs.next()
			// rs.next() 이 메서드가 반환하는 값은 만약 커서를 아래 행을 내렸을 때 데이터가 있으면 true
			// 아래 행으로 내렸을 때 데이터가 없으면 false
			/*System.out.println("rs.next() : " + rs.next());
			//System.out.println("rs.next() : " + rs.next()); 
			
			// 커서가 데이터를 가리키고 있는 상태에서 값을 얻어내는 방법
			// 1. 오라클의 인덱스를 사용하는 방법(1번부터 시작 : product_id(1) product_name(2) price(3) register_date(4))
			// 해당 컬럼이 어떤 형 데이터 인지 알아야함 -> 자바에서 해당하는 데이터 타입
			// number -> int int형 getter 메서드
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getInt(3));
			System.out.println(rs.getDate(4));
			
			// 만약 커서를 내렸을때 행이 존재한다면 값을 출력하겠다
			
			 if(rs.next()) {
				// 출력코드
				System.out.println(rs.getInt(1) 
						+ " : " + rs.getString(2) 
						+ " : " + rs.getInt(3)
				        + " : " + rs.getDate(4));
			}
			
			// 2. 컬럼명을 사용하는 방법
			if(rs.next()) {
				System.out.println(rs.getInt("product_id")
						 + " : " + rs.getString("product_name")
						 + " : " + rs.getInt("price")
						 + " : " + rs.getDate("register_date"));
			} */
			
			String sql = "select * from cafe";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println( rs.getInt(1)
						+ " : " + rs.getString(2)
						+ " : " + rs.getInt(3)
						+ " : " + rs.getDate(4));
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
