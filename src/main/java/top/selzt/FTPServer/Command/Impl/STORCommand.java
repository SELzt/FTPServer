package top.selzt.FTPServer.Command.Impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Component.PORTControll;
import top.selzt.FTPServer.Component.ServerThreadInit;
import top.selzt.FTPServer.Thread.ServerThread;

public class STORCommand implements Command{

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		String pathname = serverThread.baseList+"\\"+data;
		File f = new File(pathname);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(serverThread.getFlagPort()==1||serverThread.getEprt()==1) {
			PORTControll.setSon(serverThread, writer);
		}
		try {
			if(serverThread.getModel()==0) {
				char[] buffer = new char[1024*1024*7];
				BufferedReader br = new BufferedReader(new InputStreamReader(serverThread.getSon().getInputStream(),"GB2312"));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GB2312"));
				int len = 0;
				while((len=br.read(buffer))>-1) {
					bw.write(buffer, 0, len);
				}
				bw.flush();
				bw.close();
				br.close();
				serverThread.getSon().close();
				writer.write("226 传输完毕\r\n");
				writer.flush();
				ServerThreadInit.init(serverThread);
				
			}
			else {
				byte[] buffer = new byte[1024*1024*7];
				BufferedInputStream in = new BufferedInputStream(serverThread.getSon().getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
				int len=0;
				while((len=in.read(buffer))>-1) {
					out.write(buffer, 0, len);
				}
				out.flush();
				out.close();
				in.close();
				serverThread.getSon().close();
				writer.write("226 传输完毕\r\n");
				writer.flush();
				ServerThreadInit.init(serverThread);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
