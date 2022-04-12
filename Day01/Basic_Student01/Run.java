package com.kh.student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		
	 student : while(true) {
		System.out.println("== 학생 관리 프로그램 ==");
		System.out.println("1. 학생 데이터 입력");
		System.out.println("2. 학생 데이터 수정");
		System.out.println("3. 학생 데이터 삭제");
		System.out.println("4. 학생 데이터 조회");
		System.out.println("5. 프로그램 종료");
		System.out.print(">> ");
		int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1) {
			System.out.print("학생의 이름을 입력하세요. >> ");
			String name = sc.nextLine();
			System.out.print("학생의 전화번호를 입력하세요. >> ");
			String phone = sc.nextLine();
			System.out.print("학생의 생년월일을(yyyy-mm-dd) 입력하세요. >> ");
			Date date = Date.valueOf(sc.nextLine());
		
			
			
			StudentDTO dto = new StudentDTO(0, name, phone, date);
			try {
				int rs = dao.insert(dto);
				if(rs > 0) {
					System.out.println("데이터 입력 완료");
				}else {
					System.out.println("데이터 입력 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
		}else if(menu == 2) {
			System.out.print("수정할 학생의 번호를 입력하세요. >> ");
			int no = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 학생의 이름을 입력하세요. >> ");
			String name = sc.nextLine();
			System.out.print("수정할 학생의 전화번호를 입력하세요. >> ");
			String phone = sc.nextLine();
			System.out.print("수정할 학생의 생년월일(yyyy-mm-dd)을 입력하세요. >> ");
			Date date = Date.valueOf(sc.nextLine());
			
			StudentDTO dto = new StudentDTO(no, name, phone, date);
			
			try {
				int rs = dao.update(dto);
				if(rs > 0) {
					System.out.println("데이터 수정 완료");
				}else {
					System.out.println("데이터 수정 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
		}else if(menu == 3) {
			System.out.print("삭제할 학생의 번호를 입력하세요. >> ");
			int no = Integer.parseInt(sc.nextLine());
			try {
				int rs = dao.delete(no);
				if(rs > 0) {
					System.out.println("데이터 삭제 완료");
				}else {
					System.out.println("데이터 삭제 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
		}else if(menu == 4) {
			System.out.println("1. 개별 조회");
			System.out.println("2. 전체 조회");
			System.out.print(">> ");
			int input = Integer.parseInt(sc.nextLine());
	
				if(input == 1) {
					
					System.out.print("조회할 학생의 번호를 입력하세요. >> ");
					int number = Integer.parseInt(sc.nextLine());
					try {
						StudentDTO rs = dao.select(number);
						if(rs != null) {
							System.out.println(rs);
						}else {
							System.out.println("데이터 조회 실패");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("DB접속이 불안정 합니다.");
					}
				}else if(input == 2) {
					try {
						
						ArrayList<StudentDTO> list = dao.selectAll();
						
						if(list != null) {
							
							for(StudentDTO dto : list) {
								System.out.println(dto.toString());
							}
						}else {
							System.out.println("데이터를 조회 실패");
						}
						
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("DB접속이 불안정 합니다.");
					}
				
		}else if(menu == 5) {
			System.out.println("프로그램을 종료합니다.");
			break student;
		}
	}
	 }
	}
}
	 


