import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Lab_play extends JFrame implements ActionListener, Runnable {
	JLabel ttl, rbt;
	JButton stt;
	Thread rabbit, turtle;
	int intervalr = 2000, intervalt = 300, speedr = 60, speedt = 10,
			dist = 900;

	Lab_play() {
		ttl = new JLabel();
		ttl.setIcon(new ImageIcon("turtle.png"));
		rbt = new JLabel();
		rbt.setIcon(new ImageIcon("rabbit.png"));
		JPanel main = new JPanel();
		main.setLayout(null);
		main.add(ttl);
		main.add(rbt);
		ttl.setBounds(250 - ttl.getIcon().getIconWidth(), 20, ttl.getIcon()
				.getIconWidth(), ttl.getIcon().getIconHeight());
		rbt.setBounds(250 - rbt.getIcon().getIconWidth(), 40 + ttl.getIcon()
				.getIconHeight(), rbt.getIcon().getIconWidth(), rbt.getIcon()
				.getIconHeight());
		JPanel start = new JPanel(), end = new JPanel(),split=new JPanel();
		start.setBounds(250, 0, 1, 40 + ttl.getIcon().getIconHeight()
				+ rbt.getIcon().getIconHeight());
		start.setBackground(Color.BLACK);
		end.setBounds(250 + dist, 0, 2, start.getHeight());
		end.setBackground(Color.BLACK);
		split.setBounds(250, ttl.getIcon().getIconHeight()+20, dist, 1);
		split.setBackground(Color.BLACK);
		main.add(start);
		main.add(end);
		main.add(split);
		add(main, BorderLayout.CENTER);
		setBounds(100, 100, 1200, 421);
		stt = new JButton("��ʼ����");
		stt.addActionListener(this);
		add(stt, BorderLayout.NORTH);
		setVisible(true);
		setTitle("��������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ttl.setBounds(250 - ttl.getIcon().getIconWidth(), 20, ttl.getIcon()
				.getIconWidth(), ttl.getIcon().getIconHeight());
		rbt.setBounds(250 - rbt.getIcon().getIconWidth(), 40 + ttl.getIcon()
				.getIconHeight(), rbt.getIcon().getIconWidth(), rbt.getIcon()
				.getIconHeight());
		rabbit = new Thread(this);
		turtle = new Thread(this);
		rabbit.start();
		turtle.start();
		stt.setEnabled(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Lab_play();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JLabel controllee = ttl;
		int speed = speedt, interval = intervalt;
		try {
			stt.setText("3");
			Thread.sleep(1000);
			stt.setText("2");
			Thread.sleep(1000);
			stt.setText("1");
			Thread.sleep(1000);
			stt.setText("����!");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (Thread.currentThread() == rabbit) {
			controllee = rbt;
			speed = speedr;
			interval = intervalr;
		}
		while (controllee.getIcon().getIconWidth()
				+ controllee.getBounds().getX() < dist + 250) {
			controllee.setBounds((int) controllee.getBounds().getX() + speed,
					(int) controllee.getBounds().getY(), (int) controllee
							.getBounds().getWidth(), (int) controllee
							.getBounds().getHeight());
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Thread.currentThread() == rabbit) {
			turtle.stop();
			stt.setText("����Ӯ�ˣ�����һ�֣�");
		} else {
			rabbit.stop();
			stt.setText("�ڹ�Ӯ�ˣ�����һ�֣�");
		}
		stt.setEnabled(true);
	}
}