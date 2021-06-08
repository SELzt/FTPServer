package top.selzt.FTPServer.Component;

import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import top.selzt.FTPServer.Thread.ServerThread;

public class SonSocket extends Thread{
	private Thread t;
	private ServerThread serverThread;
	private Writer writer;
	public SonSocket(ServerThread serverThread,Writer writer) {
		this.writer = writer;
		this.serverThread = serverThread;
	}
	public void run() {
		try {
			System.out.println("正在监听");
			Socket son = new ServerSocket(serverThread.getPort()).accept();
			System.out.println("连接成功");
			writer.write("150 数据连接已打开\r\n");
			writer.flush();
			serverThread.setSon(son);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void start() {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}

}
