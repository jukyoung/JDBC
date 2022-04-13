package com.kh.cafe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

// Singleton
public class CafeDAO {
	
	private BasicDataSource bds = new BasicDataSource();
	private static CafeDAO instance = null;
	
	private CafeDAO() { // cafeDAO 생성자 private 설정

		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}
	
	public static CafeDAO getInstance() { //CafeDAO 인스턴스를 내부적으로 new 하고 반환하는 메서드
		if(instance == null) { 
			// CafeDAO 인스턴스가 만들어진 적이 없다면 new를 하겠다
			// == DBCP -> Connection pool을 하나 만들겠다
			instance = new CafeDAO();
		}
		// 만일 CafeDAO 인스턴스가 이미 존재한다면 == 이미 만들어진 Connection pool이 존재
		// 그런 경우라면 이미 있는 커넥션 풀을 통해서 접속을 제공
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}
	
	public int insert(CafeDTO dto) throws Exception {
		String sql = "insert into cafe values(seq_cafe.nextval, ?, ?, sysdate)";
		
		try(Connection con = this.getConnection();			
			PreparedStatement pstmt = con.prepareStatement(sql);){
		
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getPrice());		
			int rs = pstmt.executeUpdate();
			return rs;
		} 
		
	}
	
	public int update(CafeDTO dto) throws Exception{ 
		String sql = "update cafe set product_name = ?, price = ? where product_id =?";
		
		try(Connection con = this.getConnection();
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
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	// 1개 데이터에 대한 select
	public CafeDTO select(int id) throws Exception{
		
		String sql = "select * from cafe where product_id = ?";

		try(Connection con = this.getConnection();
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
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
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
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
