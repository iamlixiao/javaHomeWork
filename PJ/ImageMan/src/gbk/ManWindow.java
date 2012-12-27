import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
	CollectionServer server;
	JScrollPane mainsp;
	JLabel label;
	JMenuItem startServer,changeDir;
	JMenu collection,serverMenu;
	JMenuBar mainbar;

	ManWindow() {
		try {
			this.setIconImage(ImageIO.read(new File("ImageMan.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		label = new JLabel();
		mainboard = new ImagePanel();
		add(mainboard, BorderLayout.CENTER);

		collection = new JMenu("图片集");
		changeDir=new JMenuItem("进入文件夹");
		changeDir.addActionListener(this);
		collection.add(changeDir);
		startServer = new JMenuItem("启动");
		startServer.addActionListener(this);
		serverMenu=new JMenu("网络服务");
		serverMenu.add(startServer);
		mainbar = new JMenuBar();
		mainbar.add(collection);
		mainbar.add(serverMenu);
		setJMenuBar(mainbar);

		mainview = new ImageThumbView(System.getProperty("user.home"), mainboard,this);
		add(mainview, BorderLayout.WEST);
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
		if (arg0.getSource() == startServer) {
			if(startServer.getText().equals("启动")){
			server = new CollectionServer(mainview.getCurrent());
			server.start();
			startServer.setText("停止");
			}
			else{
				server.die();
			}
		}
		else if(arg0.getSource()==changeDir){
			JFileChooser ch=new JFileChooser();
			ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(ch.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
				mainview.jump(ch.getSelectedFile().getPath());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ManWindow();
	}

	static void msg(String s) {
		JOptionPane.showMessageDialog(null, s, "提示",
				JOptionPane.WARNING_MESSAGE);
	}
}
