import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class Lab_db extends JFrame implements ActionListener {
	Connection con;
	Statement sql;
	ResultSet rs;
	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("图书管理");
	JMenuItem addData = new JMenuItem("添加");
	JMenuItem editData = new JMenuItem("修改");
	JMenuItem deleteData = new JMenuItem("删除");
	JMenuItem queryData = new JMenuItem("查询");
	JMenuItem countData = new JMenuItem("统计");
	JTextField t1 = new JTextField(15);
	JTextField t2 = new JTextField(15);
	JTextField t3 = new JTextField(15);
	JTextField t4 = new JTextField(15);
	JTextField t5 = new JTextField(15);
	JTextField t6 = new JTextField(15);
	Object[] headers = { "编号", "书名", "作者", "出版社", "分类", "价格" };
	JButton accept = new JButton("提交");
	JPanel mainPanel = new JPanel();
	CardLayout card = new CardLayout();
	JTable table = new JTable(new Object[1][6], headers);
	JScrollPane tablesp = new JScrollPane(table);
	boolean mode;

	public Lab_db() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:bookinfo", "stu1",
					"ok");
			sql = con.createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}
		this.setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(addData);
		addData.addActionListener(this);
		menu.add(editData);
		editData.addActionListener(this);
		menu.add(deleteData);
		deleteData.addActionListener(this);
		menu.add(queryData);
		queryData.addActionListener(this);
		menu.add(countData);
		countData.addActionListener(this);

		mainPanel.setLayout(card);

		mainPanel.add("s", tablesp);

		JPanel dataOps = new JPanel();
		Box b0 = Box.createVerticalBox();
		JPanel b1 = new JPanel();
		b1.add(new JLabel("书编号"));
		b1.add(t1);
		b0.add(b1);
		JPanel b2 = new JPanel();
		b2.add(new JLabel("书名    "));
		b2.add(t2);
		b0.add(b2);
		JPanel b3 = new JPanel();
		b3.add(new JLabel("作者    "));
		b3.add(t3);
		b0.add(b3);
		JPanel b4 = new JPanel();
		b4.add(new JLabel("出版社"));
		b4.add(t4);
		b0.add(b4);
		JPanel b5 = new JPanel();
		b5.add(new JLabel("分类    "));
		b5.add(t5);
		b0.add(b5);
		JPanel b6 = new JPanel();
		b6.add(new JLabel("价格    "));
		b6.add(t6);
		b0.add(b6);
		dataOps.add(b0);
		dataOps.add(accept);
		accept.addActionListener(this);
		mainPanel.add("t", dataOps);
		card.first(mainPanel);

		add(mainPanel, BorderLayout.CENTER);
		update();
		this.setTitle("图书信息管理");
		final int sw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, sh = java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height, ww = 600, wh = 300;
		setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	void update() {
		try {
			rs = sql.executeQuery("SELECT * FROM bookinfo");
			String ans = "";
			String tmp = "";
			int j = 0;
			Vector<String[]> set = new Vector<String[]>();
			while (rs.next()) {
				String[] resultline = new String[6];
				for (int i = 1; i <= 6; i++) {
					if (i < 6)
						resultline[i - 1] = rs.getString(i);
					else {
						resultline[i - 1] = String.valueOf(rs.getInt(6));
					}
				}
				j++;
				set.add(resultline);
			}
			String[][] dt = new String[set.size()][6];
			int i = 0;
			for (String[] s : set) {
				dt[i] = s;
				i++;
			}
			table = new JTable(dt, headers);
			tablesp.setViewportView(table);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == queryData) {
			update();
			card.first(mainPanel);
		}

		else if (arg0.getSource() == deleteData) {
			String str = JOptionPane.showInputDialog(this, "输入要删除的书的编号", "删除",
					JOptionPane.PLAIN_MESSAGE);
			if (str != null) {
				str = "DELETE  FROM bookinfo WHERE book_no='" + str + "'";
				try {
					sql.execute(str);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
			}
		}

		else if (arg0.getSource() == addData) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
			mode = true;
			card.last(mainPanel);
		}

		else if (arg0.getSource() == editData) {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
			mode = false;
			card.last(mainPanel);
		} else if (arg0.getSource() == accept) {
			card.first(mainPanel);
			if (mode) {
				String str = "INSERT into bookinfo values ('" + t1.getText()
						+ "','" + t2.getText() + "','" + t3.getText() + "','"
						+ t4.getText() + "','" + t5.getText() + "',"
						+ t6.getText() + ")";
				try {
					sql.execute(str);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
			} else {
				String str = "UPDATE bookinfo set book_name='" + t2.getText()
						+ "',book_author='" + t3.getText()
						+ "',book_publisher='" + t4.getText()
						+ "',book_class='" + t5.getText() + "',book_price="
						+ t6.getText() + " where book_no='" + t1.getText()
						+ "'";
				try {
					sql.execute(str);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update();
			}
		} else if (arg0.getSource() == countData) {
			String str = "SELECT COUNT(*) FROM bookinfo";
			try {
				rs = sql.executeQuery(str);
				rs.next();
				String ans = "一共有" + Integer.toString(rs.getInt(1)) + "本书！";
				JOptionPane.showMessageDialog(this, ans, "统计",
						JOptionPane.PLAIN_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Lab_db();
	}

}
