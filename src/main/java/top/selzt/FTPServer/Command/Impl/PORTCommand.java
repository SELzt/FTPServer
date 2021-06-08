package top.selzt.FTPServer.Command.Impl;

import java.io.Writer;
import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Thread.ServerThread;

public class PORTCommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ServerThread serverThread) {
		// TODO Auto-generated method stub
		
		String[] res = data.split(",");
		String ip="";
		int port=0;
		try {
			ip = res[0]+"."+res[1]+"."+res[2]+"."+res[3];
			port = Integer.parseInt(res[4])*256+Integer.parseInt(res[5]);
			serverThread.setPORThost(ip);
			serverThread.setPORTport(port);
			serverThread.setFlagPort(1);
			writer.write("200 PORT命令正常\r\n");
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				writer.write("501 参数格式错误\r\n");
				writer.flush();
				return;
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
}
