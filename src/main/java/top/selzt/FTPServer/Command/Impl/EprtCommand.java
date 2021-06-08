package top.selzt.FTPServer.Command.Impl;


import java.io.IOException;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Thread.ServerThread;

public class EprtCommand implements Command{

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		String type = "";
		String host = "";
		int port=-1;
		try {
			data = data.replace("|", ",");
			String[] temp = data.split(",");
			type = temp[1];
			host = temp[2];
			port = Integer.parseInt(temp[3]);
		} catch (Exception e) {
			try {
				writer.write("501 参数格式错误\r\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		InetAddress ip;
		if(type.equals("1")) {
			try {
				ip = Inet4Address.getByName(host);
				serverThread.setEPRTIp(ip);
				serverThread.setPORTport(port);
				serverThread.setEprt(1);
				writer.write("200 EPRT命令正常\r\n");
				writer.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type.equals("2")) {
			try {
				ip = Inet6Address.getByName(host);
				serverThread.setEPRTIp(ip);
				serverThread.setPORTport(port);
				serverThread.setEprt(1);
				writer.write("200 EPRT命令正常\r\n");
				writer.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
