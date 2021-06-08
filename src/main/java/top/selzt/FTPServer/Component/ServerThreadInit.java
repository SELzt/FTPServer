package top.selzt.FTPServer.Component;

import top.selzt.FTPServer.Thread.ServerThread;

public class ServerThreadInit {
	public static void init(ServerThread serverThread) {
		serverThread.setPort(-1);
		serverThread.setEprt(0);
		serverThread.setFlagPort(0);
	}
}
