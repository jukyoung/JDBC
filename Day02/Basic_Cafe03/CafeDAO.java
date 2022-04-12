package com.kh.cafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CafeDAO {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "kh";
	private String pw = "kh";
	
	public int insert(CafeDTO dto) throws Exception {
		String sql = "insert into cafe values(seq_cafe.nextval, ?, ?, sysdate)";
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			//PreparedStatement 객체는 쿼리문을 인자값으로 하여 생성
			PreparedStatement pstmt = con.prepareStatement(sql);){
			// ?에 해당하는 인자값 세팅
			//pstmt.set자료형(물음표의 인덱스(물음표 순서), 그 물음표에 세팅해줄 값); -> 쿼리문을 기준으로 하기 때문에 인덱스 1부터 시작
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getPrice());
			// 쿼리문을 실행 -> 이미 완성된 쿼리문을 PreparedStatement 객체가 가지고 있기 때문에 그 쿼리문을 실행만 시켜주면 됨.
			int rs = pstmt.executeUpdate();
			return rs;
		} 
		
	}
	
	public int update(CafeDTO dto) throws Exception{ 
		String sql = "update cafe set product_name = ?, price = ? where product_id =?";
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setInt(3, dto.getProduct_id());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public int delete(int id) throws Exception{ 
		String sql = "delete from cafe where product_id = ?";
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	// 1개 데이터에 대한 select
	public CafeDTO select(int id) throws Exception{
		
		String sql = "select * from cafe where product_id = ?";

		try(Connection con = DriverManager.getConnection(url, username, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				String register_date = parseDate(rs.getDate(4));
				
				CafeDTO dto = new CafeDTO(product_id, product_name, price, register_date);
				return dto;
			}
				return null;
		}
	}
	
	// 전체 데이터를 불러오는 selectAll
	public ArrayList<CafeDTO> selectAll() throws Exception {
		String sql = "select * from cafe";
		try(Connection con = DriverManager.getConnection(url, username, pw);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			// 인자값의 세팅이 필요없는 경우에는 바로 쿼리문 실행하면 됨
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<CafeDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				String register_date = parseDate(rs.getDate(4));
				
				list.add(new CafeDTO(product_id, product_name, price, register_date));
			}
			return list;
		}
    }
	public String parseDate(Date date) {
		// 년/월/일 24시:분:초
		// 자바에서 hh -> default 12시간 기준으로 표기됨
		// HH -> 24시간 표기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
