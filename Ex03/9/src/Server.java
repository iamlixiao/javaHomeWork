import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Server extends JFrame implements ActionListener, Runnable {
	JTextArea chatBoard = new JTextArea();
	JPanel chatInput = new JPanel();
	JButton chat = new JButton("发送");
	JTextField chatContent = new JTextField(15);
	Socket socket;
	ServerSocket server;
	DataInputStream in;
	DataOutputStream out;

	Server() {
		final int sw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, sh = java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height, ww = 500, wh = 400;
		setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
		setTitle("Server");
		add(new JScrollPane(chatBoard), BorderLayout.CENTER);
		chatInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		chatInput.add(chatContent);
		chatInput.add(chat);
		chat.addActionListener(this);
		add(chatInput, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			server = new ServerSocket(5432);
			try {
				socket = server.accept();
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				Thread thread = new Thread(this);
				thread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String s;
		while (true) {
			try {
				s = in.readUTF();
				chatBoard.append(s + "\n");
			} catch (IOException e) {
				try {
					//重新连接
					socket = server.accept();
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			out.writeUTF(chatContent.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Server();
	}
}
