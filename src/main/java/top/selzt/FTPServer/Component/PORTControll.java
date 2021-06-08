package top.selzt.FTPServer.Component;

import java.io.Writer;
import java.net.Socket;

import top.selzt.FTPServer.Thread.ServerThread;

public class PORTControll {
	public static void setSon(ServerThread serverThread,Writer writer) {
		try {
			writer.write("150 打开数据连接\r\n");
			writer.flush();
			Socket s;
			if(serverThread.getFlagPort()==1) {
				s = new Socket(serverThread.getPORThost(),serverThread.getPORTport());
			}
			else {
				s = new Socket(serverThread.getEPRTIp(),serverThread.getPORTport());
			}
			
			serverThread.setSon(s);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
