import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 */

/**
 * @author lixiao
 * 
 */
public class CollectionServer implements Runnable {
	RemoteCollection seed;
	Collection working;
	boolean isUndead;

	CollectionServer(Collection c) {
		working=c;
		seed = new RemoteCollection(c);
		isUndead = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket server;
		CollectionServerThread thread;
		Socket client;
		try {
			server = new ServerSocket(12345);
			while (isUndead) {
				try {
					client = server.accept();
					new CollectionServerThread(client,working).start();
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