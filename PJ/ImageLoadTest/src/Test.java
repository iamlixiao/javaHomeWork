import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

class Test extends JFrame implements Runnable {
	Vector<JButton> buttons = new Vector<JButton>();

	public Test() {
		setLayout(new FlowLayout());
		for (int i = 0; i < 20; i++) {
			buttons.add(new JButton());
			add(buttons.elementAt(i));
		}
		setBounds(100, 100, 800, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread t = new Thread(this);
		t.start();
	}

	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void run() {
		File f = new File("/d/Pht/20120817/");
		File[] c = f.listFiles();
		File t;
		Image img;
		ImageIcon ico;
		for (int i = 0; i < 20; i++) {
			t = c[i];
			try {
				img = ImageIO.read(t);
				img = img.getScaledInstance(-1, 72, Image.SCALE_FAST);
				ico = new ImageIcon(img);
				buttons.elementAt(i).setIcon(ico);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
