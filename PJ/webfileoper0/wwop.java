import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

class wwop implements Runnable {
	URL root;
	InputStream roots;
	Scanner extracter;
	String[] l1pges;

	@Override
	public void run() {
		try {
			root = new URL("http://c-faq.com/questions.html");
			roots = root.openStream();
			extracter = new Scanner(roots);
			extracter.useDelimiter("\"");
			while (extracter.hasNext()) {
				String s = extracter.next();
				if (s.endsWith("href=")) {
					String str = extracter.next();
					if (!str.startsWith("..")) {
						ant a = new ant(new URL("http://c-faq.com/" + str));
						a.run();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		wwop w = new wwop();
		w.run();
	}

}

class ant implements Runnable {
	URL page;

	ant(URL u) {
		page = u;
	}

	public void run() {
		InputStream contents;
		File out=new File(".");
		try {
			contents = page.openStream();
			Scanner reader = new Scanner(contents);
			String toout = "", tocct = "", title;
			int mode = 0;
			while (reader.hasNextLine()) {
				String t = reader.nextLine();
				if (mode == 0)
					toout = toout.concat(t);
				else if (mode == 1) {
					if (t.indexOf("<h1") != -1)
						mode = -2;
					else
						tocct = tocct.concat(t);
				} else if (mode == -2) {
					if (t.indexOf("</font") != -1)
						mode = 2;
				} else if (mode == 2) {
					if (t.indexOf("</h1") == -1) {
						title = t.trim().replaceFirst("<!.*>", "");
						title = title.concat(" · comp.lang.c FAQ list").concat(".html");
						out=new File("out/",title);
						if(!out.exists())out.delete();
						out.createNewFile();
						System.out.println(title);
					} else
						mode = 1;
				}
				if (t.indexOf("<body") != -1)
					mode = -1;
				else if (t.indexOf("<hr") != -1) {
					toout = toout.concat(tocct);
					tocct="";
					mode = 1;
				}
			}
			FileWriter writer=new FileWriter(out);
			writer.write(toout);
			writer.close();
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
