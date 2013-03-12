import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Dialog2 extends JFrame implements ActionListener, Runnable {

	JTextArea content = new JTextArea();
	JPanel southPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JTextField field = new JTextField(20);
	JButton sendMessage = new JButton("发送对话");
	JButton sendFile = new JButton("发送文件");
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	Thread thread;

	Dialog2() {
		final int sw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, sh = java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height, ww = 500, wh = 400;
		setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
		setTitle("对话2");
		add(content, BorderLayout.CENTER);

		southPanel.add(new JLabel("对话内容或文件名"));
		southPanel.add(field);
		add(northPanel, BorderLayout.NORTH);

		northPanel.add(sendMessage);
		northPanel.add(sendFile);
		sendMessage.addActionListener(this);
		sendFile.addActionListener(this);
		add(southPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DatagramPacket pack = null;
		DatagramSocket mail_data = null;
		byte data[] = new byte[8192];
		try {
			pack = new DatagramPacket(data, data.length);
			mail_data = new DatagramSocket(666);
		} catch (Exception e) {
		}

		while (true) {
			try {
				mail_data.receive(pack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String message = new String(pack.getData(), 0, pack.getLength());
			content.append(message + "\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == sendMessage) {
			byte buffer[] = field.getText().getBytes();
			try {
				InetAddress address = InetAddress.getByName("127.0.0.1");
				DatagramPacket data_pack = new DatagramPacket(buffer,
						buffer.length, address, 888);
				DatagramSocket mail_data = new DatagramSocket();
				mail_data.send(data_pack);
			} catch (Exception e) {
			}
		}
		if (arg0.getSource() == sendFile) {
			File file = new File(field.getText().trim());
			int t = 0;
			byte buffer[] = new byte[8192];
			try {
				FileInputStream in = new FileInputStream(file);
				try {
					while((t = in.read(buffer))!=-1)
						try {
							InetAddress address = InetAddress.getByName("127.0.0.1");
							DatagramPacket data_pack = new DatagramPacket(buffer, t,
									address, 888);
							DatagramSocket mail_data = new DatagramSocket();
							mail_data.send(data_pack);
						} catch (Exception e) {
						}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dialog2();
	}

}
