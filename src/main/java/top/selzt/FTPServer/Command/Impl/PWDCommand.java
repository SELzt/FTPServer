package top.selzt.FTPServer.Command.Impl;

import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.Ever;
import top.selzt.FTPServer.Thread.ServerThread;

public class PWDCommand implements Command{

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		try {
			if(serverThread.baseList.equals(Ever.rootDir)) {
				writer.write("257 当前目录是\"\\\"");
				writer.write("\r\n");
				writer.flush();
			}
			else {
				writer.write("257 当前目录是"+"\""+serverThread.baseList.substring(Ever.rootDir.length())+"\"");
				writer.write("\r\n");
				writer.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
