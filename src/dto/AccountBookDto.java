// AccountBook Project - dto Package - AccountBookDto.java
package dto;

public class AccountBookDto {
	private String title, inOutCome, date, memo;
	private int money;

	public AccountBookDto() {}
	
	public AccountBookDto(String title, String inOutCome, int money, String date, String memo) {
		this.title = title;
		this.inOutCome = inOutCome;
		this.money = money;
		this.date = date;
		this.memo = memo;
	}
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getInOutCome() { return inOutCome; }
	public void setInOutCome(String inOutCome) { this.inOutCome = inOutCome; }
	
	public int getMoney() { return money; }
	public void setMoney(int money) { this.money = money; }
	
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }
	
	public String getMemo() { return memo; }
	public void setMemo(String memo) { this.memo = memo; }
	
	@Override
	public String toString() {
		return "[제목: " + title + " / 금액: " + inOutCome + money
				+ " / 날짜: " + date + " / 내용: " + memo + "]";
	}
	
	public String getData() {
		return title + "/" + inOutCome + "/" + money + "/" + date + "/" + memo; 
	}	
}
