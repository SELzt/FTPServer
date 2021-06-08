package top.selzt.FTPServer.Command.Impl;

import java.io.File;

import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.Ever;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Thread.ServerThread;

public class CdCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		if(data.equals("..")) {
			if(serverThread.baseList.equals(Ever.rootDir)) {
				MyWriter.write(writer, "250 当前已处于文件根目录");
				return;
			}
			serverThread.baseList = serverThread.baseList.substring(0, serverThread.baseList.lastIndexOf("\\"));
			MyWriter.write(writer, "250 目录切换成功");
			return;
		}
		String temp = serverThread.baseList+"\\"+data;
		File f = new File(temp);
		if(!f.exists()) {
			MyWriter.write(writer, "550 路径不存在");
			return;
		}
		if(f.isFile()) {
			MyWriter.write(writer, "550 目录名称无效");
			return;
		}
		if(f.isDirectory()) {
			serverThread.baseList = temp;
			MyWriter.write(writer, "250 成功进入"+data+"目录");
		}
	}

}
