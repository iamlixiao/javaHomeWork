import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener, Runnable {
	JTextArea chatBoard = new JTextArea();
	JPanel chatInput = new JPanel();
	JButton chat = new JButton("发送");
	JTextField chatContent = new JTextField(15);
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	Thread thread;

	Client() {
		final int sw = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, sh = java.awt.Toolkit
				.getDefaultToolkit().getScreenSize().height, ww = 500, wh = 400;
		setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
		setTitle("Client");
		add(new JScrollPane(chatBoard), BorderLayout.CENTER);
		chatInput.setLayout(new FlowLayout(FlowLayout.RIGHT));
		chatInput.add(chatContent);
		chatInput.add(chat);
		chat.addActionListener(this);
		add(chatInput, BorderLayout.SOUTH);
		setVisible(true);
		boolean p = true;
		while (p) {
			//为了防止服务器比客户端后打开时无法连接，让客户端不断尝试
			try {
				socket = new Socket("127.0.0.1", 5432);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				thread = new Thread(this);
				thread.start();
				p = false;
			} catch (UnknownHostException e) {
				break;
			} catch (IOException e) {
				p = true;
			}
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chat) {
			try {
				out.writeUTF(chatContent.getText());
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Client();

	}

}
