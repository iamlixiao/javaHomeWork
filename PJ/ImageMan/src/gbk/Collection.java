import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

/**
 * ������
 * ���󼯺ϣ��Ǹ���ͼƬ���ϵĸ��࣬���ǿ��Ի��������γ�һ����״�ṹ��
 */

/**
 * @author lixiao
 * 
 */
public abstract class Collection implements Serializable {
	abstract void init();

	abstract void uninit();

	abstract boolean isInited();

	abstract int length();

	abstract HashMap<String, File> getImages();

	abstract File getImage(String s);

	abstract Image getImg(String s);

	abstract Image getImg(int s);

	abstract Vector<String> listImages();

	abstract HashMap<String, Collection> getSubs();

	abstract Collection getSub(String s);

	abstract Collection getSubu(String s);

	abstract Vector<String> listSubs();

	abstract Collection getParent();

	abstract Collection getParentu();

	abstract String getPath();

	abstract void setParent(Collection p);
}
