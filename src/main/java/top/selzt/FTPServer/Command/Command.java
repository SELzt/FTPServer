package top.selzt.FTPServer.Command;


import java.io.Writer;

import top.selzt.FTPServer.Thread.ServerThread;

public interface Command {
	public void getResult(String data,Writer writer,ServerThread serverThread);
}
