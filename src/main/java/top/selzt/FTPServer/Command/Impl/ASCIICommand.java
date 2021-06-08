package top.selzt.FTPServer.Command.Impl;


import java.io.Writer;
import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Thread.ServerThread;

public class ASCIICommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		serverThread.setModel(0);
		MyWriter.write(writer, "200 成功切换为ASCII模式");
		
	}

}
