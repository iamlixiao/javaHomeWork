import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static java.awt.BorderLayout.*;
import java.awt.event.*;
class SelectWord extends JFrame implements ActionListener{
	JTextArea areal,arear;
	JButton confirm;
	JPanel btmpanel;
	JScrollPane lscroll,rscroll;
	public SelectWord(){
		areal=new JTextArea();
		lscroll=new JScrollPane(areal);
		areal.setLineWrap(true);
		areal.setPreferredSize(new Dimension(250,9000));
		lscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		arear=new JTextArea();
		rscroll=new JScrollPane(arear);
		arear.setLineWrap(true);
		arear.setPreferredSize(new Dimension(250,9000));
		arear.setAutoscrolls(true);
		rscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		confirm=new JButton("确定");
		confirm.addActionListener(this);
		btmpanel=new JPanel();
		setSize(600,400);
		add(lscroll,WEST);
		add(rscroll,EAST);
		add(btmpanel,SOUTH);
		btmpanel.add(confirm);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		if(arear.getSelectedText()!=null)areal.setText(areal.getText()+arear.getSelectedText());
	}
	public static void main(String args[]){
		SelectWord sw=new SelectWord();
	}
}
