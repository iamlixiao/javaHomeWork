package ImageMan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * ͼ�������
 * ������һ���µ��̣߳����ϵؽ���Զ�̵��������׽��֣�Ȼ���ٽ����µ��߳̽���ͨ�š�
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
	 * ֹͣ����
	 */
	void die() {
		isUndead = false;
	}

	boolean isDead() {
		return !isUndead;
	}

	/*
	 * ��û��die������²���ѭ�����������Ӳ���һ�����̴߳���
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
			ManWindow.msg("�޷�ʹ�÷������˿�");
		}
	}

}
