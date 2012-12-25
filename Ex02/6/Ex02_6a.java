import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Ex02_6a extends JFrame implements ActionListener {
	JPanel panelw = new JPanel();
	JLabel indinp = new JLabel("输入数字");
	JTextField inumber = new JTextField(5);
	JTextArea numberlist = new JTextArea();
	JScrollPane eastpane = new JScrollPane(numberlist);

	public Ex02_6a() {
		setBounds(100, 100, 500, 400);
		panelw.setPreferredSize(new Dimension(getWidth() / 2 - 20,
				getHeight() / 2 - 40));
		panelw.add(indinp);
		panelw.add(inumber);
		panelw.setLayout(new FlowLayout(4, 5, 155));
		inumber.addActionListener(this);
		setLayout(new GridLayout(1, 2));
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
				int n = JOptionPane.showConfirmDialog(this, "把数加到文本区中?", "判断",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION)
					numberlist.append(inumber.getText() + "\n");
			}
			inumber.setText("");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Ex02_6a();
	}

}