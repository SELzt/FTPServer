package top.selzt.FTPServer.Command.Impl;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Component.PORTControll;
import top.selzt.FTPServer.Component.ServerThreadInit;
import top.selzt.FTPServer.Component.VerifyPASV;
import top.selzt.FTPServer.Thread.ServerThread;

public class GetCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		if(serverThread.getFlagPort()==0) {
			if(serverThread.getEprt()==0) {
				if(!VerifyPASV.verifyPasv(writer, serverThread))
					return;
			}
		}
		if(serverThread.getFlagPort()==1||serverThread.getEprt()==1) {
			PORTControll.setSon(serverThread, writer);
		}
		File f = new File(serverThread.baseList+"\\"+data.trim());
		if(!f.exists()) {
			MyWriter.write(writer, "550 文件不存在");
			try {
				serverThread.getSon().close();
				ServerThreadInit.init(serverThread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(f.isDirectory()) {
			MyWriter.write(writer, "550 文件名无效");
			try {
				serverThread.getSon().close();
				ServerThreadInit.init(serverThread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(serverThread.getModel()==0) {
			try {
				Writer ascWriter = new BufferedWriter(new OutputStreamWriter(serverThread.getSon().getOutputStream(),"GB2312"));
				char[] needSend = new char[1024*1024*7];
				BufferedReader reader = new BufferedReader(new FileReader(f));
				int len=0;
				while((len=reader.read(needSend))>-1) {
					ascWriter.write(needSend,0,len);
				}
				ascWriter.flush();
				reader.close();
				ascWriter.close();
				serverThread.getSon().close();
				MyWriter.write(writer, "226 传输完毕");
				ServerThreadInit.init(serverThread);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				byte[] needSend = new byte[1024*1024*7];
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
				BufferedOutputStream out = new BufferedOutputStream(serverThread.getSon().getOutputStream());
				int len=0;
				while((len=in.read(needSend))>-1) {
					out.write(needSend,0,len);
				}
				out.flush();
				in.close();
				out.close();
				serverThread.getSon().close();
				MyWriter.write(writer, "226 传输完毕");
				ServerThreadInit.init(serverThread);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
