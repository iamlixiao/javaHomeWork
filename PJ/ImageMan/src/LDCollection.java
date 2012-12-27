import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author lixiao
 * 
 */
public class LDCollection extends Collection {
	File destine;
	String path;
	boolean inited;
	Collection parent;
	HashMap<String, Collection> subcollections;
	Vector<String> subcollnames;
	HashMap<String, File> images;
	Vector<String> imagenames;

	LDCollection(String l) {
		destine = new File(l);
		path = l;
		inited = false;
		parent = null;
		subcollections = new HashMap<String, Collection>();
		subcollnames = new Vector<String>();
		images = new HashMap<String, File>();
		imagenames = new Vector<String>();
	}

	LDCollection(String l, LDCollection p) {
		destine = new File(l);
		path = l;
		inited = false;
		parent = p;
		subcollections = new HashMap<String, Collection>();
		subcollnames = new Vector<String>();
		images = new HashMap<String, File>();
		imagenames = new Vector<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#init()
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
	 * @see Collection#uninit()
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
	 * @see Collection#isInited()
	 */
	@Override
	boolean isInited() {
		// TODO Auto-generated method stub
		return inited;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#length()
	 */
	@Override
	int length() {
		// TODO Auto-generated method stub
		return images.size() + subcollnames.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getImages()
	 */
	@Override
	HashMap<String, File> getImages() {
		// TODO Auto-generated method stub
		return images;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getImage(java.lang.String)
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
	 * @see Collection#listImages()
	 */
	@Override
	Vector<String> listImages() {
		// TODO Auto-generated method stub
		return imagenames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getSubs()
	 */
	@Override
	HashMap<String, Collection> getSubs() {
		// TODO Auto-generated method stub
		return subcollections;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getSub(java.lang.String)
	 */
	@Override
	Collection getSub(String s) {
		// TODO Auto-generated method stub
		return subcollections.get(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getSubu(java.lang.String)
	 */
	@Override
	Collection getSubu(String s) {
		// TODO Auto-generated method stub
		System.out.println("进入" + s);
		Collection c = subcollections.get(s);
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
	 * @see Collection#listSubs()
	 */
	@Override
	Vector<String> listSubs() {
		// TODO Auto-generated method stub
		return subcollnames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getParent()
	 */
	@Override
	Collection getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Collection#getParentu()
	 */
	@Override
	Collection getParentu() {
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
	 * @see Collection#getPath()
	 */
	@Override
	String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	void setParent(Collection p) {
		// TODO Auto-generated method stub
		parent = p;
	}

}
