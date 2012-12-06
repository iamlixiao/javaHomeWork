import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static java.awt.BorderLayout.*;

public class TextStyle extends JFrame implements ActionListener, ItemListener {
	JPanel panel1 = new JPanel();
	String[] fontnames = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getAvailableFontFamilyNames();
	String[] colornames = { "黑色", "红色", "蓝色", "绿色" };
	Color[] colors = { Color.BLACK, Color.RED, Color.BLUE, Color.GREEN };
	JComboBox fontselector, colorselector;
	JCheckBox checkbox1 = new JCheckBox("粗体"), checkbox2 = new JCheckBox("斜体");
	JTextArea area = new JTextArea();
	JTextField field;

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == fontselector) {
			String name = (String) fontselector.getSelectedItem();

			Font f = new Font(name, getstyle(), Integer.parseInt(field
					.getText()));
			area.setFont(f);
		}
		if (arg0.getSource() == colorselector) {
			int idx = colorselector.getSelectedIndex();
			area.setForeground(colors[idx]);

		}
		if (arg0.getSource() == checkbox1 || arg0.getSource() == checkbox2) {
			Font f = new Font(area.getFont().getFontName(), getstyle(),
					Integer.parseInt(field.getText()));
			area.setFont(f);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Font f = new Font(area.getFont().getFontName(), getstyle(),
				Integer.parseInt(field.getText()));
		area.setFont(f);

	}

	public TextStyle() {
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.setBackground(Color.CYAN);
		fontselector = new JComboBox(fontnames);
		fontselector.addItemListener(this);
		colorselector = new JComboBox(colornames);
		colorselector.addItemListener(this);
		checkbox1.addItemListener(this);
		checkbox1.setBackground(Color.CYAN);
		checkbox2.addItemListener(this);
		checkbox2.setBackground(Color.CYAN);
		field = new JTextField(3);
		field.setText("20");
		field.addActionListener(this);
		panel1.add(new JLabel("字体"));
		panel1.add(fontselector);
		panel1.add(new JLabel("字号"));
		panel1.add(field);
		panel1.add(checkbox1);
		panel1.add(checkbox2);
		panel1.add(colorselector);
		add(panel1, NORTH);
		add(new JScrollPane(area), CENTER);
		setTitle("文字风格");
		setSize(600, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	int getstyle() {
		int x = 0;
		if (checkbox1.isSelected())
			x += Font.BOLD;
		else
			x += Font.PLAIN;
		if (checkbox2.isSelected())
			x += Font.ITALIC;
		return x;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TextStyle();
	}
}
