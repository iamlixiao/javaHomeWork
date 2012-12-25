import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

public class RemoteCollection extends Collection {
	File target;
	String targetPath;
	boolean inited;
	Collection mirror;
	HashMap<String, Collection> subs;
	Vector<String> subNames;
	HashMap<String, File> images;
	Vector<String> imageNames;
	int length;

	Socket socket;
	ObjectInputStream in;
	OutputStream out;

	RemoteCollection(Collection c) {
		mirror = c;
		inited = false;
		subNames = c.listSubs();
		imageNames = c.listImages();
		length = c.length();
		socket = new Socket();
	}

	@Override
	void init() {
		// TODO Auto-generated method stub
		if (!socket.isConnected()) {
			try {
				InetAddress addr = InetAddress.getByName("127.0.0.1");
				InetSocketAddress saddr = new InetSocketAddress(addr, 12345);
				socket.connect(saddr);
				in = new ObjectInputStream(socket.getInputStream());
				out = socket.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	void uninit() {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	Image getImg(int s) {
		// TODO Auto-generated method stub
		try {
			out.write(imageNames.elementAt(s).getBytes());
			return (Image) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	Image getImg(String s) {
		// TODO Auto-generated method stub
		try {
			out.write(s.getBytes());
			return (Image) in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
	Collection getSubu(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Vector<String> listSubs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collection getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collection getParentu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

}