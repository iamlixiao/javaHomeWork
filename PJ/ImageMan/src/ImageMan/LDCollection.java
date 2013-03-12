package ImageMan;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * LD即LocalDrive
 * 在本地文件系统中移动，给ImageThumbView提供一个本地后端
 */

/**
 * @author lixiao
 * 
 */
public class LDCollection extends ImageCollection {
	File destine;
	String path;
	boolean inited;
	ImageCollection parent;
	HashMap<String, ImageCollection> subcollections;
	Vector<String> subcollnames;
	HashMap<String, File> images;
	Vector<String> imagenames;

	LDCollection(String l) {
		destine = new File(l);
		path = l.replaceAll("\\\\", "/");
		if (l.charAt(l.length() - 1) == '/')
			l = l.substring(0, l.length() - 1);
		inited = false;
		parent = null;
		subcollections = new HashMap<String, ImageCollection>();
		subcollnames = new Vector<String>();
		images = new HashMap<String, File>();
		imagenames = new Vector<String>();
	}

	LDCollection(String l, LDCollection p) {
		destine = new File(l);
		path = l.replaceAll("\\\\", "/");
		if (l.charAt(l.length() - 1) == '/')
			l = l.substring(0, l.length() - 1);
		inited = false;
		parent = p;
		subcollections = new HashMap<String, ImageCollection>();
		subcollnames = new Vector<String>();
		images = new HashMap<String, File>();
		imagenames = new Vector<String>();
	}

	/*
	 * 初始化时生成当前目录的文件和文件夹列表
	 * 
	 * @see ImageCollection#init()
	 */
	@Override
	void init() {
		// TODO Auto-generated method stub
		if (parent == null) {
			String l = destine.getParent();
			if (l != null)
				parent = new LDCollection(destine.getParent());
		}
		uninit();
		if (destine.isDirectory()) {
			File[] contents = destine.listFiles();
			for (File i : contents) {
				String n = i.getName(), ln = n.toLowerCase();
				if (i.isDirectory()) {
					subcollections.put(n, new LDCollection(i.getPath(), this));
					subcollnames.add(n);
				} else if (ln.indexOf(".jpg") == ln.length() - 4
						|| ln.indexOf(".bmp") == ln.length() - 4
						|| ln.indexOf(".png") == ln.length() - 4
						|| ln.indexOf(".wbmp") == ln.length() - 5
						|| ln.indexOf(".jpeg") == ln.length() - 5
						|| ln.indexOf(".gif") == ln.length() - 4) {
					images.put(n, i);
					imagenames.add(n);
				}
			}
		}
		inited = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#uninit()
	 */
	@Override
	void uninit() {
		// TODO Auto-generated method stub
		subcollections.clear();
		subcollnames.clear();
		images.clear();
		imagenames.clear();
		inited = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#isInited()
	 */
	@Override
	boolean isInited() {
		// TODO Auto-generated method stub
		return inited;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#length()
	 */
	@Override
	int length() {
		// TODO Auto-generated method stub
		return images.size() + subcollnames.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getImages()
	 */
	@Override
	HashMap<String, File> getImages() {
		// TODO Auto-generated method stub
		return images;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getImage(java.lang.String)
	 */
	@Override
	File getImage(String s) {
		// TODO Auto-generated method stub
		return images.get(s);
	}

	@Override
	Image getImg(String s) {
		// TODO Auto-generated method stub
		try {
			return (Image) ImageIO.read(images.get(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	Image getImg(int s) {
		// TODO Auto-generated method stub
		try {
			return ImageIO.read(images.get(imagenames.elementAt(s)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#listImages()
	 */
	@Override
	Vector<String> listImages() {
		// TODO Auto-generated method stub
		return imagenames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getSubs()
	 */
	@Override
	HashMap<String, ImageCollection> getSubs() {
		// TODO Auto-generated method stub
		return subcollections;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getSub(java.lang.String)
	 */
	@Override
	ImageCollection getSub(String s) {
		// TODO Auto-generated method stub
		return subcollections.get(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getSubu(java.lang.String)
	 */
	@Override
	ImageCollection getSubu(String s) {
		// TODO Auto-generated method stub
		System.out.println("进入" + s);
		ImageCollection c = subcollections.get(s);
		subcollections.clear();
		subcollnames.clear();
		images.clear();
		imagenames.clear();
		inited = false;
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#listSubs()
	 */
	@Override
	Vector<String> listSubs() {
		// TODO Auto-generated method stub
		return subcollnames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getParent()
	 */
	@Override
	ImageCollection getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getParentu()
	 */
	@Override
	ImageCollection getParentu() {
		// TODO Auto-generated method stub
		System.out.println("离开");
		if (parent != null) {
			subcollections.clear();
			subcollnames.clear();
			images.clear();
			imagenames.clear();
			inited = false;
			return parent;
		} else
			return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ImageCollection#getPath()
	 */
	@Override
	String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	void setParent(ImageCollection p) {
		// TODO Auto-generated method stub
		parent = p;
	}

}
