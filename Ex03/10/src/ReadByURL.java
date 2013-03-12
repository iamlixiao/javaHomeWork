import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextArea;

class ReadByURL extends JFrame {
	JTextArea area = new JTextArea();

	ReadByURL() {
		final int sw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, sh = java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height, ww = 500, wh = 400;
		setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
		add(area);
		setVisible(true);
		try {
			URL url = new URL("http", "10.12.8.18", "/java2/myprog.java");
			try {
				InputStream in = url.openStream();
				int n;
				byte b[] = new byte[256];
				while ((n = in.read(b)) != -1) {
					String s = new String(b, 0, n);
					area.append(s);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReadByURL();
	}

}
