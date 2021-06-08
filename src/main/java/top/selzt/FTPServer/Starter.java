package top.selzt.FTPServer;

import top.selzt.FTPServer.Component.Ever;
import top.selzt.FTPServer.Thread.ListenThread;

public class Starter {
	public static void main(String[] args) {
		Ever.init();
		ListenThread listenThread = new ListenThread(2122);
		listenThread.start();
		System.out.println("运行成功");
	}
}
