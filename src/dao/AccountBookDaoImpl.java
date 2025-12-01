// AccountBook Project - dao Package - AccountBookDaoImpl.java
package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

import dto.AccountBookDto;
import file.FileProc;
import single.Singleton;

public class AccountBookDaoImpl implements AccountBookDao {
	Scanner sc = new Scanner(System.in);
	private FileProc fp;
	
	public AccountBookDaoImpl() {
		fp = new FileProc("accountBook");
		load();
    }
	
	// ====================================================================================

	@Override   // 내역 추가
	public void create() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("제목 입력: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("수입(+)/지출(-) 입력: ");
		String inOutCome = sc.next();
		System.out.print("금액 입력(숫자만 입력): ");
		int money = sc.nextInt();
		System.out.print("날짜 입력(YYYY.MM.DD): ");
		String date = sc.next(); sc.nextLine();
		
		System.out.print("내용 입력: ");
		String memo = "";
		try {
			memo = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AccountBookDto dto = new AccountBookDto(title, inOutCome, money, date, memo);
		Singleton s = Singleton.getInstance();
		s.list.add(dto);
		System.out.println("= 내역 추가 완료 =");
	}
	
	// ====================================================================================

	@Override   // 제목으로 내역 찾기
	public void readTitle() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("검색할 제목 입력: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<AccountBookDto> it = s.list.iterator();
		boolean found = false;
		
		while (it.hasNext()) {
			AccountBookDto ad = it.next();
		    if (title.equals(ad.getTitle())) {
		    	System.out.println(ad);
		    	found = true;
		    }
		}
		if (!found) System.out.println("= 제목 없음 =");
		else System.out.println("= 검색 완료 =");
	}

	@Override  // 날짜로 내역 찾기
	public void readDate() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		System.out.print("검색할 날짜 입력(YYYY.MM.DD): ");
		String date = sc.next();
		
		Iterator<AccountBookDto> it = s.list.iterator();
		boolean found = false;
		
		while (it.hasNext()) {
			AccountBookDto ad = it.next();
		    if (date.equals(ad.getDate())) {
		    	System.out.println(ad);
		    	found = true;
		    }
		}
		if (!found) System.out.println("= 날짜 없음 =");
		else System.out.println("= 검색 완료 =");
	}

	@Override   // 내용으로 내역 찾기
	public void readMemo() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		System.out.print("검색할 내용 입력: ");
		String memo = sc.next();
		
		Iterator<AccountBookDto> it = s.list.iterator();
		boolean found = false;
		
		while (it.hasNext()) {
			AccountBookDto ad = it.next();
		    if (ad.getMemo().contains(memo)) {
		    	System.out.println(ad);
		    	found = true;
		    }
		}
		if (!found) System.out.println("= 내용 없음 =");
		else System.out.println("= 검색 완료 =");
	}
	
	// ====================================================================================

	@Override   // 내역 수정
	public void update() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("수정할 제목 입력: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AccountBookDto dto = search(title);
		if (dto == null) { System.out.println("= 제목 없음 ="); return; }

		Iterator<AccountBookDto> it = s.list.iterator();
		while (it.hasNext()) {
			AccountBookDto ad = it.next();
			if (title.equals(ad.getTitle())) {
				System.out.println(ad);
				System.out.print("수정할 수입(+)/지출(-) 입력: ");
				ad.setInOutCome(sc.next());
				System.out.print("수정할 금액 입력: ");
				ad.setMoney(sc.nextInt());
				System.out.print("수정할 날짜 입력: ");
				ad.setDate(sc.next());
				sc.nextLine();

				System.out.print("수정할 내용 입력: ");
				String memo = "";
				try {
					memo = br.readLine();
					ad.setMemo(memo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("= 수정 완료 =");
	}

	@Override   // 내역 삭제
	public void delete() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("삭제할 제목 입력: ");
		String title = "";
		try {
			title = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AccountBookDto dto = search(title);		
		if(dto == null) {
			System.out.println("= 제목 없음 =");
			return;
		}	
		
		s.list.remove(dto);		
		System.out.println("= 삭제 완료 =");
	}
	
	// ====================================================================================

	@Override   // 월별 결산
	public void monthly() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		System.out.print("결산하고 싶은 년월 입력(YYYY.MM): ");
		String yearMonth = sc.next();
		int income = 0, outcome = 0;
		
		for (AccountBookDto dto: s.list) {
			if (dto.getDate().contains(yearMonth)) {
				if (dto.getInOutCome().equals("+")) {
					income += dto.getMoney();
				}
				else if (dto.getInOutCome().equals("-")) {
					outcome += dto.getMoney();
				}
			}
		}
		System.out.println("= " + yearMonth + "의 월별 결산 =");
		System.out.println("| 수입: +" + income + "원");
		System.out.println("| 지출: -" + outcome + "원");
	}

	@Override   // 기간별 결산
	public void period() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		System.out.print("결산하고 싶은 시작일 입력(YYYY.MM.DD): ");
		String startDate = sc.next();
		System.out.print("결산하고 싶은 종료일 입력(YYYY.MM.DD): ");
		String endDate = sc.next();
		
		String startSplit[] = startDate.split("\\.");
		String endSplit[] = endDate.split("\\.");
		
		int startInt = Integer.parseInt(startSplit[0]) * 10000
				+ Integer.parseInt(startSplit[1]) * 100
				+ Integer.parseInt(startSplit[2]);
		int endInt = Integer.parseInt(endSplit[0]) * 10000
				+ Integer.parseInt(endSplit[1]) * 100
				+ Integer.parseInt(endSplit[2]);
		
		int income = 0, outcome = 0;
		for (AccountBookDto dto: s.list) {
			String dateSplit[] = dto.getDate().split("\\.");
			int dateInt = Integer.parseInt(dateSplit[0]) * 10000
					+ Integer.parseInt(dateSplit[1]) * 100
					+ Integer.parseInt(dateSplit[2]);
			if (dateInt >= startInt && dateInt <= endInt) {
				if (dto.getInOutCome().equals("+")) {
					income += dto.getMoney();
				}
				else if (dto.getInOutCome().equals("-")) {
					outcome += dto.getMoney();
				}
			}
		}
		System.out.println("= " + startDate + "~" + endDate + "의 기간별 결산 =");
		System.out.println("| 수입: +" + income + "원");
		System.out.println("| 지출: -" + outcome + "원");
	}
	
	@Override   // 전체 결산
	public void all() {
		Singleton s = Singleton.getInstance();
		int income = 0, outcome = 0;
		for (AccountBookDto dto: s.list) {
			if (dto.getInOutCome().equals("+")) {
				income += dto.getMoney();
			}
			else if (dto.getInOutCome().equals("-")) {
				outcome -= dto.getMoney();
			}
		}
		System.out.println("| 수입: " + income + "원");
		System.out.println("| 지출: " + outcome + "원");
		System.out.println("| 잔액: " + (income + outcome) + "원");
	}
	
	// ====================================================================================

	@Override   // 파일에 내역 저장
	public void save() { fp.fileSave(); }

	@Override   // 파일에서 내역 불러오기
	public void load() { fp.fileLoad(); }
	
	// ====================================================================================

	// 내역 전체 출력
	public void printAll() {
		Singleton s = Singleton.getInstance();
		if (s.list.isEmpty()) { System.out.println("= 저장 내역 없음 ="); return; }
		
		for (AccountBookDto dto : s.list) {
			System.out.println(dto.toString());
		}
	}
	
	public AccountBookDto search(String name) {
		Singleton s = Singleton.getInstance();
		AccountBookDto dto = null;		
		for (AccountBookDto accountBook : s.list) {
			if(name.equals(accountBook.getTitle())) {
				dto = accountBook;
				break;
			}
		}
		return dto;
	}
}
