package ImageMan;

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
public abstract class ImageCollection implements Serializable {
	/*
	 * ��ʼ����������Ҫ�������ɸ��ӽڵ㣬ImageCollectionֻ�о�����ʼ������ʹ�á�
	 * ����ÿһ��ImageCollection���洢�������ӽڵ�͸��ڵ㣬��������ڹ���ʱ�Զ���ʼ���ķ�ʽ��
	 * ����ܻṹ����һ���Ӵ����������ʵ�������õ�ֻ����һ�㡢�������һ����ѡ�
	 */
	abstract void init();

	/*
	 * ����ʼ������ImageCollection�Ժ󲻻����õ�ʱӦִ�д˷���
	 */
	abstract void uninit();

	/*
	 * ���ر�ImageCollection�Ƿ��ѱ���ʼ��
	 */
	abstract boolean isInited();

	/*
	 * ���ر�ImageCollection���ļ��к����ļ�������
	 */
	abstract int length();

	/*
	 * ���ļ���ʽ���ر�ImageCollection������ͼƬ
	 */
	abstract HashMap<String, File> getImages();

	/*
	 * �����ļ�����ȡͼƬ�ļ�
	 */
	abstract File getImage(String s);

	/*
	 * �����ļ�����ȡͼƬ����
	 */
	abstract Image getImg(String s);

	/*
	 * ���ݱ�Ż�ȡͼƬ����
	 */
	abstract Image getImg(int s);

	/*
	 * ��ȡͼƬ�ļ����б�
	 */
	abstract Vector<String> listImages();

	/*
	 * ����������ImageCollection
	 */
	abstract HashMap<String, ImageCollection> getSubs();

	/*
	 * �������ƻ�ȡ��ImageCollection
	 */
	abstract ImageCollection getSub(String s);

	/*
	 * �������ƻ�ȡ��ImageCollection��ͬʱ����ʼ��
	 */
	abstract ImageCollection getSubu(String s);

	/*
	 * ��ȡ��ImageCollection�����б�
	 */
	abstract Vector<String> listSubs();

	/*
	 * ��ȡ��ImageCollection
	 */
	abstract ImageCollection getParent();

	/*
	 * ��ȡ��ImageCollection��ͬʱ����ʼ��
	 */
	abstract ImageCollection getParentu();

	/*
	 * ��ȡ��ImageCollection�Ķ�Ӧ·��
	 */
	abstract String getPath();

	/*
	 * ���ø�ImageCollection
	 */
	abstract void setParent(ImageCollection p);
}
