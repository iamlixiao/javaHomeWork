import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 */

/**
 * @author lixiao
 * 
 */
public class ImageThumbView extends JPanel implements ActionListener, Runnable {
	Collection current;
	JPanel panel;
	JScrollPane mainsp;
	ImagePanel outerlabel;
	JButton up;
	HashMap<String, ThumbButton> entries;
	HashMap<String, Thread> ct;
	HashMap<String, ThumbButton> subs;
	HashMap<String, Thread> sut;
	Box boxlayout;
	Thread th;

	ImageThumbView(String l, ImagePanel lb) {
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
		add(panel, BorderLayout.NORTH);
		subs = new HashMap<String, ThumbButton>();
		entries = new HashMap<String, ThumbButton>();
		reGen();

		mainsp = new JScrollPane(boxlayout);
		add(mainsp, BorderLayout.CENTER);
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
		// outerlabel.setIcon(new
		// ImageIcon(((ThumbButton)e.getSource()).getPath()));
		if (e.getSource() == up) {
			th.stop();
			current = current.getParentu();
			current.init();
			reGen();
		} else if (((ThumbButton) (e.getSource())).isSub()) {
			th.stop();
			current = current
					.getSubu(((ThumbButton) (e.getSource())).getText());
			current.init();
			reGen();
		} else
			outerlabel
					.show(current.getImg(((JButton) e.getSource()).getText()));
	}

	void reGen() {
		boxlayout.removeAll();

		subs.clear();
		for (int i = 0; i < current.listSubs().size(); i++) {
			String cit = current.listSubs().elementAt(i);
			ThumbButton bt = new ThumbButton(cit);
			subs.put(cit, bt);
			bt.addActionListener(this);
			boxlayout.add(bt);
		}

		entries.clear();
		for (int i = 0; i < current.listImages().size(); i++) {
			String currenttext = current.listImages().elementAt(i);
			ThumbButton bt = new ThumbButton(currenttext, current.getPath()
					+ '/' + currenttext);
			bt.setPreferredSize(new Dimension(128, 128));
			entries.put(currenttext, bt);
			bt.addActionListener(this);
			boxlayout.add(bt);
		}
		th = new Thread(this);
		th.start();
		boxlayout.setPreferredSize(new Dimension(100, 85 * current.length()));
		this.repaint();
		this.revalidate();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Image img;
		BufferedImage _img;
		for (int i = 0; i < current.listImages().size(); i++) {
			img = current.getImg(i);
			_img = new BufferedImage(img.getWidth(this) * 72
					/ img.getHeight(this), 72, BufferedImage.TYPE_INT_ARGB);
			_img.getGraphics().drawImage(img, 0, 0, _img.getWidth(),
					_img.getHeight(), this);
			entries.get(current.listImages().elementAt(i)).setIcon(
					new ImageIcon(_img));
			System.gc();
		}
		return;
	}
}
