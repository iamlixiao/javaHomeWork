import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 * 
 */

/**
 * @author lixiao
 * 
 */
public class ImageThumbView extends JPanel implements ActionListener, Runnable {
	ManWindow boss;
	Collection current, backup;
	JPanel panel, mainp;
	JScrollPane mainsp;
	ImagePanel outerlabel;
	JButton up;
	JToggleButton receive;
	JTextField serverInput, portInput;
	HashMap<String, ThumbButton> entries;
	HashMap<String, Thread> ct;
	HashMap<String, ThumbButton> subs;
	HashMap<String, Thread> sut;
	Box boxlayout;
	Thread th;
	String appname = "ImageMan", currentImg = "",dirStat="[����]";

	ImageThumbView(String l, ImagePanel lb, ManWindow w) {
		boss = w;
		boss.setTitle(appname + " - " + l);

		current = new LDCollection(l);
		current.init();
		outerlabel = lb;
		boxlayout = Box.createVerticalBox();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(260, 1));

		panel = new JPanel();
		up = new JButton("����");
		up.addActionListener(this);
		panel.add(up);
		receive = new JToggleButton("Զ��");
		receive.addActionListener(this);
		panel.add(receive);
		serverInput = new JTextField("127.0.0.1");
		panel.add(serverInput);
		panel.add(new JLabel(":"));
		portInput = new JTextField("12345");
		panel.add(portInput);
		add(panel, BorderLayout.NORTH);

		subs = new HashMap<String, ThumbButton>();
		entries = new HashMap<String, ThumbButton>();
		mainp = new JPanel();
		((FlowLayout) (mainp.getLayout())).setVgap(0);
		reGen();

		mainsp = new JScrollPane(mainp);
		add(mainsp, BorderLayout.CENTER);
	}

	Collection getCurrent() {
		return current;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == up) {
			th.stop();
			current = current.getParentu();
			current.init();
			reGen();
		} else if (e.getSource() == receive) {
			if (receive.isSelected()) {
				backup = current;
				dirStat="["+serverInput.getText()+":"+portInput.getText()+"]";
				try {
					String str = serverInput.getText();
					int prt = Integer.parseInt(portInput.getText());
					Socket s = new Socket(str, prt);
					ObjectInputStream is = new ObjectInputStream(
							s.getInputStream());
					DataOutputStream os = new DataOutputStream(
							s.getOutputStream());
					os.writeChars("./");
					os.writeChar(0);
					RemoteCollection tc = (RemoteCollection) is.readObject();
					tc.setParent(current);
					tc.setServer(str, prt);
					is.close();
					s.close();
					current.uninit();
					current = tc;
					current.init();
					reGen();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					ManWindow.msg("����ʧ��");
					receive.setSelected(false);
					current = backup;
					dirStat="[����]";
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				dirStat="[����]";
				current = backup;
				current.init();
				reGen();
			}
		} else if (((ThumbButton) (e.getSource())).isSub()) {
			th.stop();
			current = current
					.getSubu(((ThumbButton) (e.getSource())).getText());
			current.init();
			reGen();
		} else {
			outerlabel
					.show(current.getImg(((JButton) e.getSource()).getText()));
			currentImg = ((JButton) e.getSource()).getText();
			boss.setTitle(appname + " - " +dirStat+ current.getPath() + "["
					+ currentImg + "]");
		}
	}

	void jump(String s) {
		current.uninit();
		current = new LDCollection(s);
		current.init();
		reGen();
	}

	void reGen() {
		if (th != null)
			th.stop();
		mainp.removeAll();

		subs.clear();
		for (int i = 0; i < current.listSubs().size(); i++) {
			String cit = current.listSubs().elementAt(i);
			ThumbButton bt = new ThumbButton(cit);
			subs.put(cit, bt);
			bt.addActionListener(this);
			bt.setPreferredSize(new Dimension(260, 72));
			bt.setHorizontalAlignment(SwingConstants.LEFT);
			mainp.add(bt);
		}

		entries.clear();
		for (int i = 0; i < current.listImages().size(); i++) {
			String currenttext = current.listImages().elementAt(i);
			ThumbButton bt = new ThumbButton(currenttext, current.getPath()
					+ '/' + currenttext);
			bt.setPreferredSize(new Dimension(128, 128));
			entries.put(currenttext, bt);
			bt.addActionListener(this);
			bt.setPreferredSize(new Dimension(260, 72));
			bt.setHorizontalAlignment(SwingConstants.LEFT);
			mainp.add(bt);
		}
		th = new Thread(this);
		th.start();
		mainp.setPreferredSize(new Dimension(100, 72 * current.length()));
		this.repaint();
		this.revalidate();

		boss.setTitle(appname + " - " +dirStat+ current.getPath() + "["
				+ currentImg + "]");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Image img;
		BufferedImage _img;
		for (int i = 0; i < current.listImages().size(); i++) {
			img = current.getImg(i);
			if (img != null) {
				_img = new BufferedImage(img.getWidth(this) * 72
						/ img.getHeight(this), 72, BufferedImage.TYPE_INT_ARGB);
				_img.getGraphics().drawImage(img, 0, 0, _img.getWidth(),
						_img.getHeight(), this);
				entries.get(current.listImages().elementAt(i)).setIcon(
						new ImageIcon(_img));
			}
			System.gc();
		}
		return;
	}
}