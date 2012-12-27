import java.awt.Image;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

public class RemoteCollection extends Collection {
	File target;
	String targetPath, server;
	boolean inited;
	Collection mirror, parent;
	HashMap<String, Collection> subs;
	Vector<String> subNames;
	HashMap<String, File> images;
	Vector<String> imageNames;
	int length, serverPort;

	RemoteCollection(Collection c) {
		mirror = c;
		targetPath = c.getPath();
		inited = false;
		subNames = c.listSubs();
		imageNames = c.listImages();
		length = c.length();
		server = "127.0.0.1";
		serverPort = 12345;
	}

	@Override
	void init() {
		// TODO Auto-generated method stub
		Socket socket = new Socket();
		try {
			InetAddress addr = InetAddress.getByName(server);
			InetSocketAddress saddr = new InetSocketAddress(addr, serverPort);
			socket.connect(saddr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	void uninit() {
	}

	@Override
	boolean isInited() {
		// TODO Auto-generated method stub
		return inited;
	}

	@Override
	int length() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	HashMap<String, File> getImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	File getImage(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 读取远程图片 在服务器端利用ImageIO通过流将图片编码并发送，在客户端用ImageIO接收并解码
	 * 
	 * @see Collection#getImg(int)
	 */
	@Override
	Image getImg(int s) {
		Socket socket = socketUp();
		ObjectInputStream in;
		DataOutputStream out;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			if (targetPath.lastIndexOf('/') != targetPath.length())
				out.writeChars(targetPath + '/' + imageNames.elementAt(s));
			else
				out.writeChars(targetPath + imageNames.elementAt(s));
			out.writeChar(0);
			Image c = ImageIO.read(socket.getInputStream());
			socket.close();
			return c;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	Image getImg(String s) {
		Socket socket = socketUp();
		ObjectInputStream in;
		DataOutputStream out;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			if (targetPath.lastIndexOf('/') != targetPath.length())
				out.writeChars(targetPath + '/' + s);
			else
				out.writeChars(targetPath + s);
			out.writeChar(0);
			Image c = ImageIO.read(socket.getInputStream());
			socket.close();
			return c;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	Vector<String> listImages() {
		// TODO Auto-generated method stub
		return imageNames;
	}

	@Override
	HashMap<String, Collection> getSubs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collection getSub(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collection getSubu(String str) {
		Socket s = socketUp();
		ObjectInputStream in;
		DataOutputStream out;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			if (targetPath.lastIndexOf('/') != targetPath.length())
				out.writeChars(targetPath + '/' + str + '/');
			else
				out.writeChars(targetPath + str + '/');
			out.writeChar(0);
			RemoteCollection c = (RemoteCollection) in.readObject();
			c.setParent(parent);
			s.close();
			c.setServer(server, serverPort);
			return c;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	Vector<String> listSubs() {
		// TODO Auto-generated method stub
		return subNames;
	}

	@Override
	Collection getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collection getParentu() {
		Socket s = socketUp();
		ObjectInputStream in;
		DataOutputStream out;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			String t = targetPath.substring(0, targetPath.length() - 1);
			out.writeChars(t.substring(0, t.lastIndexOf('/') + 1));
			out.writeChar(0);
			RemoteCollection c = (RemoteCollection) in.readObject();
			s.close();
			c.setServer(server, serverPort);
			return c;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	String getPath() {
		// TODO Auto-generated method stub
		return targetPath;
	}

	@Override
	void setParent(Collection p) {
		// TODO Auto-generated method stub
		parent = p;
	}

	void setServer(String s, int p) {
		server = s;
		serverPort = p;
	}

	Socket socketUp() {
		Socket socket = new Socket();
		InetAddress addr;
		try {
			addr = InetAddress.getByName(server);
			InetSocketAddress saddr = new InetSocketAddress(addr, serverPort);
			socket.connect(saddr);
			return socket;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
