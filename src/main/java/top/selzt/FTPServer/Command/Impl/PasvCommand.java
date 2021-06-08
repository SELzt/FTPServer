package top.selzt.FTPServer.Command.Impl;

import java.io.Writer;
import java.net.InetAddress;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Component.SonSocket;
import top.selzt.FTPServer.Thread.ServerThread;

public class PasvCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		String response = "";
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			int base = (int)(Math.random()*98+1);
			int p = (int)(Math.random()*98+1);
			String port = ","+base+","+p;
			host = host.replace('.', ',');
			System.out.println(base*256+p);
			serverThread.setPort(base*256+p);
			response = "("+host+port+")";
			MyWriter.write(writer,"227 "+"Entering Passive Mode "+response);
			
			new SonSocket(serverThread,writer).start();
			/*writer.write(response);
			writer.flush();*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
