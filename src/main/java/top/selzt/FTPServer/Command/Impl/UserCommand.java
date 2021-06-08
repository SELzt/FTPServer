package top.selzt.FTPServer.Command.Impl;

import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.Ever;
import top.selzt.FTPServer.Thread.ServerThread;


public class UserCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		String response = "";
		data = data.trim();
		if(Ever.users.get(data)!=null) {
			serverThread.USER.set(data);
			response = "331 帐号存在";
		}
		else {
			response = "501 帐号不存在";
		}
		try {
			writer.write(response);
			writer.write("\r\n");
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
