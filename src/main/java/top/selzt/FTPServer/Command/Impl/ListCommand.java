package top.selzt.FTPServer.Command.Impl;

import java.io.BufferedWriter;


import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.PORTControll;
import top.selzt.FTPServer.Component.ServerThreadInit;
import top.selzt.FTPServer.Component.VerifyPASV;
import top.selzt.FTPServer.Thread.ServerThread;

public class ListCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		//new PasvCommand().getResult(data, writer, serverThread);
		//默认使用ASCII模式
		if(serverThread.getFlagPort()==0) {
			if(serverThread.getEprt()==0) {
				if(!VerifyPASV.verifyPasv(writer, serverThread))
					return;
			}
		}
		if(serverThread.getFlagPort()==1||serverThread.getEprt()==1) {
			PORTControll.setSon(serverThread, writer);
		}
		try {
			Writer sonWriter = new BufferedWriter(new OutputStreamWriter(serverThread.getSon().getOutputStream(),"GB2312"));
			File f = new File(serverThread.baseList);
			String[] list = f.list();
			System.out.println("正在发送列表");
			for(String temp : list) {
				sonWriter.write(temp);
				sonWriter.write("\r\n");
			}
			sonWriter.flush();
			System.out.println("发送完毕");
			ServerThreadInit.init(serverThread);
			sonWriter.close();
			serverThread.getSon().close();
			writer.write("226 数据发送完毕,关闭数据连接\r\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
