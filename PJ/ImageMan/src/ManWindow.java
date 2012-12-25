import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * 
 */

/**
 * @author lixiao
 * 
 */
public class ManWindow extends JFrame implements ActionListener {

	ImageThumbView mainview;
	ImagePanel mainboard;
	JScrollPane mainsp;
	JLabel label;

	ManWindow() {
		label=new JLabel();
		mainboard=new ImagePanel();
		add(mainboard,BorderLayout.CENTER);
		
		mainview = new ImageThumbView(
				"/d/Pht/20120817/",mainboard);
		add(mainview,BorderLayout.WEST);
		setBounds(100, 100, 500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManWindow();
	}

}
