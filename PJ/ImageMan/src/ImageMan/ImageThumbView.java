package ImageMan;

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
 * 显示在窗口左边的文件视图，通过Collection进行文件操作，因此可以利用RemoteCollection像访问本地文件一样访问远程文件。
 * 文件和文件夹的列表是一排按钮，本别存储在entries和subs中
 * 本视图有一个特殊状态“atRoot”，由于使用File.getParent无法返回到驱动器根以上，为了在Windows下方便地切换磁盘分区，只能手动取得分区的列表。
 */

/**
 * @author lixiao
 * 
 */
public class ImageThumbView extends JPanel implements ActionListener, Runnable {
	ManWindow boss;
	ImageCollection current, backup;
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
	Vector<ThumbButton> drvs;
	Box boxlayout;
	Thread th;
	String appname = "ImageMan", currentImg = "", dirStat = "[本地]";
	boolean atRoot;

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
		up = new JButton("向上");
		up.addActionListener(this);
		panel.add(up);
		receive = new JToggleButton("远程");
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

		drvs = new Vector<ThumbButton>();
		for (File f : File.listRoots()) {
			ThumbButton t = new ThumbButton(f.getPath());
			drvs.add(t);
			t.addActionListener(this);
		}
		atRoot = false;
	}

	ImageCollection getCurrent() {
		return current;
	}

	/*
	 * 处理按钮动作事件，当代表文件或文件夹的按钮被点击时，可以利用按钮上的文字取得对应的图片或进入对应的文件夹。
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
			if (current.getParent() == null) {
				mainp.removeAll();
				for (ThumbButton t : drvs) {
					mainp.add(t);
					t.setPreferredSize(new Dimension(260, 72));
					t.setHorizontalAlignment(SwingConstants.LEFT);
				}
				atRoot = true;
				mainp.repaint();
				mainp.revalidate();
			} else {
				current = current.getParentu();
				current.init();
				reGen();
			}
		} else if (e.getSource() == receive) {
			if (receive.isSelected()) {
				backup = current;
				dirStat = "[" + serverInput.getText() + ":"
						+ portInput.getText() + "]";
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
					ManWindow.msg("连接失败");
					receive.setSelected(false);
					current = backup;
					dirStat = "[本地]";
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				dirStat = "[本地]";
				current = backup;
				current.init();
				reGen();
			}
		} else if (((ThumbButton) (e.getSource())).isSub()) {
			th.stop();
			if (!atRoot) {
				current = current.getSubu(((ThumbButton) (e.getSource()))
						.getText());
				current.init();
				reGen();
			} else {
				current.uninit();
				current = new LDCollection(
						((ThumbButton) e.getSource()).getText());
				current.init();
				reGen();
				atRoot = false;
			}
		} else {
			outerlabel
					.show(current.getImg(((JButton) e.getSource()).getText()));
			currentImg = ((JButton) e.getSource()).getText();
			boss.setTitle(appname + " - " + dirStat + current.getPath() + "["
					+ currentImg + "]");
		}
	}

	/*
	 * 直接跳转到某一文件夹，意味着要换一个新的Collection
	 */
	void jump(String s) {
		receive.setSelected(false);
		dirStat = "[本地]";
		current.uninit();
		current = new LDCollection(s);
		current.init();
		reGen();
	}

	/*
	 * 重新生成按钮列表中的按钮，在当前视图内容发生改变后执行
	 */
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

		boss.setTitle(appname + " - " + dirStat + current.getPath() + "["
				+ currentImg + "]");
	}

	/*
	 * 在新的线程中生成图片缩略图，防止卡住界面
	 */
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
