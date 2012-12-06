import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Ex02_4 extends JFrame implements ActionListener {
	JButton button1 = new JButton("按钮1"), button2 = new JButton("按钮2");

	public Ex02_4() {
		setLayout(null);
		button1.setBounds(0, 0, 80, 30);
		button1.addActionListener(this);
		button2.setBounds(0, 30, 80, 30);
		button2.addActionListener(this);
		add(button1);
		add(button2);
		setTitle("移动的按钮");
		setBounds(
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 300,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 150,
				600, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == button1)
			button2.setLocation(
					(int) ((this.getWidth() - button2.getWidth()) * Math
							.random()), (int) ((this.getHeight() - button2
							.getHeight()) * Math.random()));
		else
			button1.setLocation(
					(int) ((this.getWidth() - button1.getWidth()) * Math
							.random()), (int) ((this.getHeight() - button1
							.getHeight()) * Math.random()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Ex02_4();
	}
}
