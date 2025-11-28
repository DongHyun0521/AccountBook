// AccountBook Project - file Package - FileProc.java
package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dto.AccountBookDto;
import single.Singleton;

public class FileProc {
	private File file;
	
	public FileProc(String filename) { file = new File("c:/tmp/" + filename + ".txt"); }
	
	public void createNewFile() {
		try {
			if (file.createNewFile()) {
				System.out.println("= 파일 생성 완료 =");
			} else {
				System.out.println("= 파일 이미 존재 =");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileSave() {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
			Singleton s = Singleton.getInstance();
			for (AccountBookDto dto : s.list) pw.println(dto.getData());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		System.out.println("= 파일에 저장 완료 =");
	}
    
    public void fileLoad() {
    	Singleton s = Singleton.getInstance(); // 첫번째 싱글턴 호출
    	if (!file.exists()) { createNewFile(); return; }
    	try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = "";
				while((str = br.readLine()) != null) {
					String data[] = str.split("/");
					AccountBookDto dto = new AccountBookDto(data[0], data[1],
										Integer.parseInt(data[2]), data[3], data[4]);
					s.list.add(dto);
				}
				br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
