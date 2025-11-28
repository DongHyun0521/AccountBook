// AccountBook Project - main Package - Main.java
package main;

import java.util.Scanner;

import dao.AccountBookDaoImpl;

public class Main {
	public static void main(String[] args) {
		AccountBookDaoImpl dao = new AccountBookDaoImpl();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println(" -------- 가계부 --------");
			System.out.println("| 1. 내역 추가            |");	
			System.out.println("| 2. 내역 검색            |");
			System.out.println("| 3. 내역 수정            |");
			System.out.println("| 4. 내역 삭제            |");
			System.out.println("| 5. 월별 결산            |");
			System.out.println("| 6. 기간별 결산           |");
			System.out.println("| 7. 내역 저장            |");
			System.out.println("| 8. 전체 내역 출력         |");
			System.out.println("| 0. 프로그램 종료          |");
			System.out.println(" ------- 전체 결산 -------");
			dao.all();
			System.out.println(" ----------------------");
			System.out.print("메뉴 번호 입력: ");
			int number = sc.nextInt();
			
			switch(number) {
			case 1:
				dao.create();
				break;
			case 2:
				System.out.println("| 1. 제목으로 검색    |");
				System.out.println("| 2. 날짜로 검색     |");
				System.out.println("| 3. 내용으로 검색    |");
				System.out.print("메뉴 번호 입력: ");
				int num = sc.nextInt();
				switch(num) {
				case 1:
					dao.readTitle();
					break;
				case 2:
					dao.readDate();
					break;
				case 3:
					dao.readMemo();
					break;
				}
				break;
			case 3:
				dao.update();
				break;
			case 4:
				dao.delete();
				break;
			case 5:
				dao.monthly();
				break;
			case 6:			
				dao.period();
				break;
			case 7:
				dao.save();
				break;
			case 8:
				dao.printAll();
				break;
			case 0:
				System.out.println("X 프로그램 종료 X");
				sc.close();
				return;
			}
		}
	}
}
