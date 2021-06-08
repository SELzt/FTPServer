package top.selzt.FTPServer.Thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenThread extends Thread {
	private Thread t;
	private int port;
	private ServerSocket serverSocket;
	public ListenThread(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("端口被占用，程序结束");
			System.exit(0);
			//e.printStackTrace();
		}
	}
	public Thread getT() {
		return t;
	}
	public void setT(Thread t) {
		this.t = t;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public void run() {
		while(true) {
			try {
				Socket s =serverSocket.accept();
				//System.out.println(s.getInetAddress());
				new ServerThread(s).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void start() {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}
