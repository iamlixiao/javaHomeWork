import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Lab_Grep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] filelist = new String[args.length - 1];
		String tosearch = args[args.length - 1];
		Vector<File> files = new Vector<File>();
		for (int i = 0; i < args.length - 1; i++) {
			files.add(new File(args[i]));
		}
		for (int i=0;i<files.size();i++) {
			File f=files.elementAt(i);
			if (f.isDirectory())
				for (File sf : f.listFiles())
					files.add(sf);
			else {
				try {
					FileReader reader=new FileReader(f);
					BufferedReader readerb=new BufferedReader(reader);
					String s;
					int j=1;
					while((s=readerb.readLine())!=null){
						if(s.contains(tosearch))System.out.println(f.getName()+' '+String.valueOf(j)+' '+s);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
