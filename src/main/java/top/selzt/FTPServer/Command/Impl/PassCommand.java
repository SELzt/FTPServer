package top.selzt.FTPServer.Command.Impl;

import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.Ever;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Thread.ServerThread;

public class PassCommand implements Command {

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		String username = serverThread.USER.get();
		String password = Ever.users.get(username);
		String response = "";
		if(data.trim().equals(password)) {
			System.out.println("登陆成功");
			Ever.loginedUser.add(username);
			serverThread.setLogin(true);
			response = "230 User " + username + " successful login";
			
		}
		else {
			System.out.println("登陆失败");
			response = "530 User " + username + " login failed";
		}
		try {
			MyWriter.write(writer, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
