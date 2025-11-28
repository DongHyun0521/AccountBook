// AccountBook Project - dao Package - AccountBookDao.java
package dao;

public interface AccountBookDao {
	void create();
	void readTitle();
	void readDate();
	void readMemo();
	void update();
	void delete();
	
	void monthly();
	void period();
	
	void save();
	void load();
}
