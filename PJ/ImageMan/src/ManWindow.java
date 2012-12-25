import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	JMenuItem startserver;
	JMenu collection;
	JMenuBar mainbar;

	ManWindow() {
		label=new JLabel();
		mainboard=new ImagePanel();
		add(mainboard,BorderLayout.CENTER);
		
		startserver=new JMenuItem("远程传送");
		startserver.addActionListener(this);
		collection=new JMenu("图片集");
		collection.add(startserver);
		mainbar=new JMenuBar();
		mainbar.add(collection);
		setJMenuBar(mainbar);
		
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
		if(arg0.getSource()==startserver){
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManWindow();
	}
static void msg(String s){
	JOptionPane.showMessageDialog(null, s, "提示", JOptionPane.WARNING_MESSAGE);
}
}
