import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

/**
 * 基础类
 * 抽象集合，是各种图片集合的父类，它们可以互相链接形成一个树状结构。
 */

/**
 * @author lixiao
 * 
 */
public abstract class Collection {
	abstract void init();

	abstract void uninit();

	abstract boolean isInited();

	abstract int length();

	abstract HashMap<String, File> getImages();
	
	abstract File getImage(String s);
	
	abstract Image getImg(String s);

	abstract Vector<String> listImages();

	abstract HashMap<String, Collection> getSubs();

	abstract Collection getSub(String s);

	abstract Collection getSubu(String s);

	abstract Vector<String> listSubs();

	abstract Collection getParent();

	abstract Collection getParentu();

	abstract String getPath();
}
