package ImageMan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * 图像服务器
 * 本身是一个新的线程，不断地接受远程的请求建立套接字，然后再建立新的线程进行通信。
 */

/**
 * @author lixiao
 * 
 */
public class CollectionServer extends Thread {
	ImageCollection working;
	Vector<CollectionServerThread> serverThreads;
	boolean isUndead;

	CollectionServer(ImageCollection c) {
		working = c;
		isUndead = true;
	}

	/*
	 * 停止服务
	 */
	void die() {
		isUndead = false;
	}

	boolean isDead() {
		return !isUndead;
	}

	/*
	 * 在没有die的情况下不断循环，接受连接并开一个新线程处理
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket server;
		CollectionServerThread thread;
		Socket client;
		System.out.println("Server up");
		try {
			server = new ServerSocket(12345);
			while (isUndead) {
				try {
					client = server.accept();
					thread = new CollectionServerThread(client, working);
					thread.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ManWindow.msg("无法使用服务器端口");
		}
	}

}
