import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Confirmer extends JDialog implements ActionListener {
	int message = 0;
	JButton yes = new JButton("是"), no = new JButton("否");
	JPanel confirmbuttons = new JPanel();

	public Confirmer(JFrame o, String t, String m) {
		super(o, t, true);
		yes.addActionListener(this);
		no.addActionListener(this);
		add(new JLabel(m,Label.LEFT), BorderLayout.NORTH);
		confirmbuttons.add(yes);
		confirmbuttons.add(no);
		add(confirmbuttons, BorderLayout.SOUTH);
		setBounds(200, 200, 150, 100);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == yes) {
			message = 1;
			setVisible(false);
		}
		if (arg0.getSource() == no) {
			message = 0;
			setVisible(false);
		}
	}

	public int getMessage() {
		int c=message;
		message=0;
		return c;
	}
}

class Ex02_6 extends JFrame implements ActionListener {
	JPanel panelw = new JPanel();
	JLabel indinp = new JLabel("输入数字");
	JTextField inumber = new JTextField(5);
	JTextArea numberlist = new JTextArea();
	JScrollPane eastpane = new JScrollPane(numberlist);
	Confirmer confirmer = new Confirmer(this, "判断", "把数加到文本区中?");

	public Ex02_6() {
		setBounds(100, 100, 500, 400);
		panelw.setPreferredSize(new Dimension(getWidth() / 2 - 20,
				getHeight() / 2 - 40));
		panelw.add(indinp);
		panelw.add(inumber);
		panelw.setLayout(new FlowLayout(4, 5, 155));
		inumber.addActionListener(this);
		setLayout(new GridLayout(1,2));
		add(panelw);
		numberlist.setVisible(true);
		numberlist.setLineWrap(true);
		eastpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(eastpane);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == inumber) {
			int x = Integer.parseInt(inumber.getText());
			if (x <= 1000)
				numberlist.append(inumber.getText() + "\n");
			else {
				confirmer.setVisible(true);
				if (confirmer.getMessage() == 1)
					numberlist.append(inumber.getText() + "\n");
			}
			inumber.setText("");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Ex02_6();
	}

}
