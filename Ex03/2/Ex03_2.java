import java.io.*;
import java.util.Scanner;
import java.util.*;
class Ex03_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String location=scanner.next();
		try {
			RandomAccessFile file=new RandomAccessFile(location, "r");
			int i = 0;
			long p=0;
			Stack<Long> h=new Stack<Long>();
			while(p<file.length()){
				file.readLine();
				h.push(p);
				p=file.getFilePointer();
				++i;
			}
			while(--i>=0){
				file.seek(h.pop());
				String t=file.readLine();
				byte b[]=t.getBytes("ISO-8859-1");
				String str=new String(b);
				for(int j=str.length()-1;j>=0;j--){
					System.out.print(str.charAt(j));
				}
				System.out.println();
			}
			file.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
