package com.kh.cafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CafeDAO {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "kh";
	private String pw = "kh";
	
	public int insert(CafeDTO dto) throws Exception { // 예외전가
		
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
			
		// dto에 있는 값을 가져오기 때문에 getter
			String sql = "insert into cafe values(seq_cafe.nextval, '" + dto.getProduct_name() + "'," + dto.getPrice() + ", sysdate)";
			int rs = stmt.executeUpdate(sql);
			return rs;
		} // try 문안에서 예외 발생하는거 책임을 전가 -> 메서드를 호춣한 자리에서 처리해라 throws Exception
		
	}
	
	public int update(CafeDTO dto) throws Exception{ //수정할 id -> name, price 수정
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
			
			String sql = "update cafe set product_name = '" + dto.getProduct_name()
			        + "', price = "	+ dto.getPrice() 
					+ "where product_id = " + dto.getProduct_id();
			int rs = stmt.executeUpdate(sql);
			return rs;
		}
	}
	
	public int delete(int id) throws Exception{ // 고유값이 되는 product_id 를 기준으로 삭제
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
			
			String sql = "delete from cafe where product_id = " + id;
			int rs = stmt.executeUpdate(sql);
			return rs;		
		}
	}
	// 1개 데이터에 대한 select
	public CafeDTO select(int id) throws Exception{

		try(Connection con = DriverManager.getConnection(url, username, pw);
			Statement stmt = con.createStatement();){
	
			String sql = "select * from cafe where product_id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				Date register_date = rs.getDate(4);
				
				CafeDTO dto = new CafeDTO(product_id, product_name, price, register_date);
				return dto;
			}
				return null;
		}
	}
	
	// 전체 데이터를 불러오는 selectAll
	public ArrayList<CafeDTO> selectAll() throws Exception {
	
		try(Connection con = DriverManager.getConnection(url, username, pw);
				Statement stmt = con.createStatement();){
			String sql = "select * from cafe";
			ResultSet rs = stmt.executeQuery(sql);
			
			ArrayList<CafeDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				Date register_date = rs.getDate(4);
				
				list.add(new CafeDTO(product_id, product_name, price, register_date));
			}
			return list;
		}
    }
}
