package com.kh.cafe;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		
		// == 카페 프로그램 ==
		// 1. 메뉴 등록 2. 메뉴 수정 3. 메뉴 삭제  4. 메뉴 조회  5. 프로그램 종료
		
		Scanner sc = new Scanner(System.in);
		CafeDAO dao = new CafeDAO();
		
		
		System.out.println("== 카페 프로그램 ==");
		System.out.println("1. 메뉴 등록"); // insert
		System.out.println("2. 메뉴 수정"); // update
		System.out.println("3. 메뉴 삭제"); // delete
		System.out.println("4. 메뉴 조회"); // select
		System.out.println("5. 프로그램 종료");
		System.out.print(">> ");
		int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1) {
			System.out.println("등록할 메뉴의 이름을 입력하세요");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.print("등록할 메뉴의 가격을 입력하세요 >> ");
			int price = Integer.parseInt(sc.nextLine());
			
			CafeDTO dto = new CafeDTO(0, name, price, null); // 시퀀스랑 sysdate 를 쓰기때문에 의미없는 값 넣어두기
			//전가받은 예외 처리해줘야함
			try {
				int rs = dao.insert(dto);
				if(rs > 0) {
					System.out.println("메뉴 등록 성공");
				}else {
					System.out.println("메뉴 등록 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
			
		}else if(menu == 2) {
			System.out.print("수정할 메뉴의 번호를 입력하세요. >> ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 제품의 이름을 입력하세요. >> ");
			String name = sc.nextLine();
			System.out.print("수정할 제품의 가격 입력하세요. >> ");
			int price = Integer.parseInt(sc.nextLine());
			
			CafeDTO dto = new CafeDTO(id, name, price, null);
			
			try {
				int rs = dao.update(dto);
				if(rs > 0) {
					System.out.println("메뉴 수정 완료");
				}else {
					System.out.println("메뉴 수정 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
		}else if(menu == 3) {
			System.out.println("삭제할 메뉴의 id 값을 입력하세요");
			System.out.print(">> ");
			int id = Integer.parseInt(sc.nextLine());
			
			try {
				int rs = dao.delete(id);
				if(rs > 0) {
					System.out.println("메뉴 삭제 완료");
				}else {
					System.out.println("메뉴 삭제 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
		}else if(menu == 4) {
//			System.out.print("조회할 제품번호를 입력하세요. >> ");
//			int id = Integer.parseInt(sc.nextLine());
			try {
				
//				CafeDTO rs = dao.select(id);
				 ArrayList<CafeDTO> list= dao.selectAll();
				 
				 if(list != null) {
					 
					 for(CafeDTO dto : list) {
						 System.out.println(dto.toString());
					 }
					 
				 }else {
					 System.out.println("데이터 조회 실패");
				 }
//				if(rs != null) {
//					System.out.println(rs.toString());
//				}else {
//					System.out.println("데이터 조회 실패");
//				}
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정 합니다.");
			}
			
		}else if(menu == 5) {
			System.out.println("프로그램을 종료합니다☆★");
		}
		
		
	}

}
