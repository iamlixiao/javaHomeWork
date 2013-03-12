package ImageMan;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * �����̣߳���CollectionServer����
 */

/**
 * @author lixiao
 * 
 */
public class CollectionServerThread extends Thread {
	Socket socket;
	ObjectOutputStream out;
	DataInputStream in;
	StringBuffer read;
	ImageCollection current;
	boolean isUndead;

	CollectionServerThread(Socket s, ImageCollection c) {
		socket = s;
		current = c;
		isUndead = true;
		read = new StringBuffer();
		System.out.println("Started");
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void die() {
		isUndead = false;
	}

	public boolean isDead() {
		return isUndead;
	}

	/*
	 * ���տͻ��˵����󣬲�������Ӧ�Ķ��� ÿ���ͻ��˵�������'\0'��β�����յ����ַ�ʱ����������ʼ��ǰ��������ַ������з�����
	 * һ�����������ļ������ƶ����������������ַ����Ľ�βһ����'/'�����������ض�Ӧ��RemoteCollection����
	 * ��һ�������Ǵ���ͼƬ������
	 * ��������ͨ��ImageIO.write(RenderdImage,String,OutputStream)������ͼƬ���͸��ͻ��ˡ�
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		System.out.println("running");
		while (isUndead) {
			try {
				char c = in.readChar();
				if (c == '\0' && read.length() > 0) {
					System.out.println(read);
					if (read.charAt(read.length() - 1) == '/') {
						if (read.toString().equals("../")) {
							current = current.getParentu();
							current.init();
							RemoteCollection cl = new RemoteCollection(current);
							out.writeObject(cl);
						} else if (read.toString().equals("./")) {
							out.writeObject(new RemoteCollection(current));
						} else {
							current = new LDCollection(read.substring(0,
									read.length() - 1));
							current.init();
							out.writeObject(new RemoteCollection(current));
						}
					} else {
						Image img = ImageIO.read(new File(read.toString()));
						ImageIO.write((BufferedImage) img, "BMP",
								socket.getOutputStream());
					}
					return;
				} else
					read.append(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}
}
