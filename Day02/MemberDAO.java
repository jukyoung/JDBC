package com.kh.date01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MemberDAO {
	
	public int insert(MemberDTO dto) throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		String sql = "insert into tbl_member values(?, ?, to_date(?))";
		try(Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getBirth_date());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
		
	}

	public ArrayList<MemberDTO> selectAll() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		String sql = "select * from tbl_member";
		try(Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<>();
			while(rs.next()) {
				
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String birth_date = toJavaString(rs.getDate("birth_date"));
				// 오라클의 date 타입을 String으로 만들어줘야함 -> 메서드 만들기
				
				list.add(new MemberDTO(id, pw, birth_date));
			}
			return list;
		}
	}
	
	public String toJavaString(Date date) { // oracle의 date 타입을 받아야함
		// oracle date타입의 데이터를 java의 String을 변환 -> SimpleDateFormat
		// 생성자의 인자값을 String으로 변환할때 어떤 형식으로 변환할 것인기 format
		// format의 대소문자 구분하기
		// oracle 월(mm/MM) 분(mi)
		// java 월(MM) 분(mm)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date_str = sdf.format(date);
		return date_str;
	}
	
}
