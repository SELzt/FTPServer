package top.selzt.FTPServer.Command.Impl;

import java.io.IOException;
import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.MyWriter;
import top.selzt.FTPServer.Thread.ServerThread;

public class ByeCommand implements Command{

	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		
			MyWriter.write(writer, "221 Goodbye");
		
	}

}
