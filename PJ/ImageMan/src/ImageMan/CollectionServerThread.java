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
 * 服务线程，由CollectionServer创建
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
	 * 接收客户端的请求，并返回相应的对象。 每个客户端的请求以'\0'结尾，当收到该字符时，服务器开始对前面的请求字符串进行分析。
	 * 一类请求是在文件树中移动的请求，这类请求字符串的结尾一律是'/'，服务器返回对应的RemoteCollection对象。
	 * 另一类请求是传输图片的请求
	 * ，服务器通过ImageIO.write(RenderdImage,String,OutputStream)方法将图片传送给客户端。
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
