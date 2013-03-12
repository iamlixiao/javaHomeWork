package ImageMan;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
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
public abstract class ImageCollection implements Serializable {
	/*
	 * 初始化函数，主要用于生成父子节点，ImageCollection只有经过初始化才能使用。
	 * 由于每一个ImageCollection都存储着它的子节点和父节点，如果采用在构造时自动初始化的方式，
	 * 则可能会构建出一个庞大的树，而其实树中有用的只是上一层、本层和下一层而已。
	 */
	abstract void init();

	/*
	 * 反初始化，当ImageCollection以后不会再用到时应执行此方法
	 */
	abstract void uninit();

	/*
	 * 返回本ImageCollection是否已被初始化
	 */
	abstract boolean isInited();

	/*
	 * 返回本ImageCollection子文件夹和子文件的总数
	 */
	abstract int length();

	/*
	 * 以文件形式返回本ImageCollection中所有图片
	 */
	abstract HashMap<String, File> getImages();

	/*
	 * 根据文件名获取图片文件
	 */
	abstract File getImage(String s);

	/*
	 * 根据文件名获取图片内容
	 */
	abstract Image getImg(String s);

	/*
	 * 根据编号获取图片内容
	 */
	abstract Image getImg(int s);

	/*
	 * 获取图片文件名列表
	 */
	abstract Vector<String> listImages();

	/*
	 * 返回所有子ImageCollection
	 */
	abstract HashMap<String, ImageCollection> getSubs();

	/*
	 * 根据名称获取子ImageCollection
	 */
	abstract ImageCollection getSub(String s);

	/*
	 * 根据名称获取子ImageCollection，同时反初始化
	 */
	abstract ImageCollection getSubu(String s);

	/*
	 * 获取子ImageCollection名称列表
	 */
	abstract Vector<String> listSubs();

	/*
	 * 获取父ImageCollection
	 */
	abstract ImageCollection getParent();

	/*
	 * 获取父ImageCollection，同时反初始化
	 */
	abstract ImageCollection getParentu();

	/*
	 * 获取本ImageCollection的对应路径
	 */
	abstract String getPath();

	/*
	 * 设置父ImageCollection
	 */
	abstract void setParent(ImageCollection p);
}
