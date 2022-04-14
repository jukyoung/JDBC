package com.kh.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

public class MemberDAO {
	private BasicDataSource bds = new BasicDataSource();
	private static MemberDAO instance = null;

	private MemberDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}

	public int insert(MemberDTO dto) throws Exception {

		String sql = "insert into member_tbl values(?, ?, ?)";

		try (Connection con = this.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getNickname());
			
			
			int rs = pstmt.executeUpdate();
			return rs;

		}
	}

	// 아이디 중복검사
	public boolean checkId(String id) throws Exception {
		
		String sql = "select * from member_tbl where id = ?";
		try(Connection con = this.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, id);
			ResultSet rs= pstmt.executeQuery();
			
			if(rs.next()) {// 중복된 id
				return false; // 사용불가 아이디 false 리턴
			}
			return true; // 사용할 수 있는 아이디일 경우 true 라면
		}
	}
	// 로그인 가능여부
	
	public boolean checkLogin(String id, String pw)throws Exception {
		
		String sql = "select * from member_tbl where id = ? and pw = ?";
		try(Connection con = this.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs= pstmt.executeQuery();
			
			if(rs.next()) {// id pw 정상입력 -> 로그인 가능
				return true; // 로그인 성공
			}
			return false; // 로그인 실패
		}
	}
	// 닉네임 가져오는 메서드
	public String getNickname(String id) throws Exception{
		
		String sql = "select nickname from member_tbl where id = ?";
		
		try(Connection con = this.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, id);
			ResultSet rs= pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
			return null;
		}
	}
	// 삭제는 고유값을 기준으로 삭제
	public int delete(String id) throws Exception{
		
		String sql = "delete from member_tbl where id = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, id);
			int rs = pstmt.executeUpdate();
			return rs;
		}
		
	}

	}


