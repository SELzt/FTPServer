package top.selzt.FTPServer.Command.Impl;

import java.io.IOException;
import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Thread.ServerThread;

public class TYPECommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		switch(data) {
			case "A":
				serverThread.setModel(0);
			case "I":
				serverThread.setModel(1);
		}
		try {
			writer.write("200 TYPE命令成功执行\r\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
