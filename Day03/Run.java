package com.kh.member;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MemberDAO dao = MemberDAO.getInstance();
		DiaryDAO diaryDao = DiaryDAO.getInstance();

		while (true) {
			System.out.println("** 멤버 서비스 프로그램 **");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 회원탈퇴");
			System.out.println("4. 프로그램 종료");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());

			if (menu == 1) { // 로그인
				while (true) {
					
					System.out.print("아이디 입력 >> ");
					String id = sc.nextLine();
					System.out.print("비밀번호 입력>> ");
					String pw = sc.nextLine();
					try {
						if (dao.checkLogin(id, pw)) {
							// 로그인한 사용자의 닉네임을 부르면서 문구 띄우기
							String nickname = dao.getNickname(id);
							System.out.println(nickname +"님 환영합니다!");
							System.out.println("오늘의 날씨는 흐림, 평균온도는 12.5도입니다.");
							while(true) {
								System.out.println("=== 서비스 메뉴 ===");
								System.out.println("1. 일기 쓰기");
								System.out.println("2. 일기 조회");
								System.out.print(">> ");
								int service = Integer.parseInt(sc.nextLine());
								
								if(service == 1) {
									System.out.print("날짜 입력(1990/01/01 형식) >> ");
									String written_date = sc.nextLine();
									System.out.print("제목 입력 >> ");
									String title = sc.nextLine();
									System.out.print("내용 입력 >> ");
									String content = sc.nextLine();
									
									DiaryDTO dto = new DiaryDTO(0, written_date, title, content);
									int rs = diaryDao.diaryInsert(dto);
									
									if(rs > 0) {
										System.out.println("다이어리 저장 성공!");
									}else {
										System.out.println("다이어리 저장에 실패했습니다.");
									}
								
								}else if(service == 2) {
									System.out.println("1. 다이어리 no로 조회");
									System.out.println("2. 다이어리 전체 조회");
									System.out.println("3. 로그아웃");
									System.out.print(">> ");
									int input = Integer.parseInt(sc.nextLine());
									
									if(input == 1) {
										System.out.print("다이어리 no 입력 >> ");
										int number = Integer.parseInt(sc.nextLine());
										DiaryDTO dto = diaryDao.diarySelect(number);
										
										if(dto != null) {
											System.out.println("번호\t날짜\t제목\t내용");
											System.out.println(dto.toString());
										}else {
											System.out.println("저장된 다이어리가 없습니다.");
										}
									}else if(input == 2) {
										ArrayList<DiaryDTO> list = diaryDao.selectAll();
										if(list != null) {
											System.out.println("번호\t날짜\t제목\t내용");
											for(DiaryDTO dto :list) {
												System.out.println(dto.toString());
											}
											
										}else {
											System.out.println("저장된 다이어리가 없습니다.");
										}
										
										
									}else if(input == 3) {
										System.out.println("로그아웃 합니다.");
										break;
									}
							}
							
								
							}
							
							break;
						} else {
							System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("DB 접속에 실패하였습니다.");
					}
				}

			} else if (menu == 2) { // 회원가입
				while (true) {
					System.out.print("아이디 입력 >> ");
					String id = sc.nextLine();
					// 중복검사
					try {
						if (dao.checkId(id)) {
							System.out.println("사용가능한 아이디입니다.");
							System.out.print("비밀번호 입력 >> ");
							String pw = sc.nextLine();

							System.out.print("닉네임 입력 >> ");
							String nickname = sc.nextLine();
							
							// 회원정보 추가
							MemberDTO dto = new MemberDTO(id, pw, nickname);

							int rs = dao.insert(dto);
							if (rs > 0) {
								System.out.println("회원가입 성공!");
							} else {
								System.out.println("DB 접속에 실패하였습니다.");
							}
							break;
						} else {
							System.out.println("중복된 아이디 입니다. 다시 입력하세요");
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("DB 접속에 실패하였습니다.");
					}

				}

			} else if (menu == 3) { // 회원 탈퇴
				while (true) {

					System.out.print("탈퇴할 회원의 아이디 입력 >> ");
					String id = sc.nextLine();
					System.out.print("탈퇴할 회원의 비밀번호 입력 >> ");
					String pw = sc.nextLine();
					try {
						if (dao.checkLogin(id, pw)) {
							String nickname = dao.getNickname(id);
							System.out.print(nickname + "님 정말 탈퇴하시겠습니까?(Y/N 입력) >> ");
							String answer = sc.nextLine();
							if (answer.equals("Y")) {

								int rs = dao.delete(id);
								if (rs > 0) {
									System.out.println("그동안 사용해주셔서 감사합니다.");
									break;
								}else {
									System.out.println("회원탈퇴에 실패했습니다.");
								}

							} else if (answer.equals("N")) {
								System.out.println("남아주셔서 감사합니다!");
								break;
							}
						} else {
							System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
						}

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("DB 접속에 실패했습니다.");
					}

				}
			} else if (menu == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

		}

	}

}
