package com.kh.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class DiaryDAO {
	
	private BasicDataSource bds = new BasicDataSource();
	private static DiaryDAO instance = null;
	
	private DiaryDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}
	
	public static DiaryDAO getInstance() {
		if(instance == null) {
			instance = new DiaryDAO();
		}
		return instance;
	}
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	public int diaryInsert(DiaryDTO dto) throws Exception {
		String sql ="insert into diary values(seq_diary.nextval, to_date(?), ?, ?)";
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getDate());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public DiaryDTO diarySelect(int number) throws Exception{
		String sql = "select * from diary where no =?";
		try(Connection con = this.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, number);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt(1);
				String written_date = parseDate(rs.getDate(2));
				String title = rs.getString(3);
				String content = rs.getString(4);
				
				DiaryDTO dto = new DiaryDTO(no, written_date, title, content);
				return dto;
			}
			return null;
		}
		
	}
	public ArrayList<DiaryDTO> selectAll() throws Exception{
		String sql = "select * from diary";
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<DiaryDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String written_date = parseDate(rs.getDate(2));
				String title = rs.getString(3);
				String content = rs.getString(4);
				
				list.add(new DiaryDTO(no, written_date, title, content));
			}
			return list;
		}
	}

	public String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		return sdf.format(date);
	}
}
