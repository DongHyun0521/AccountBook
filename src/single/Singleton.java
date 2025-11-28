// AccountBook Project - single Package - Singleton.java
package single;

import java.util.ArrayList;
import java.util.List;

import dto.AccountBookDto;

public class Singleton {
	private static Singleton sc = null;
	public List<AccountBookDto> list = null;
	
	private Singleton() {
		list = new ArrayList<AccountBookDto>();
	}
	
	public static Singleton getInstance() {
		if (sc == null)
			sc = new Singleton();
		return sc;
	}
}
