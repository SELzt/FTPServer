package top.selzt.FTPServer.Component;

import java.io.Writer;

import top.selzt.FTPServer.Thread.ServerThread;

public class VerifyPASV {
	public static boolean verifyPasv(Writer writer,ServerThread serverThread) {
		try {
			if(serverThread.getPort()!=-1&&(serverThread.getSon()==null||serverThread.getSon().isClosed())) {
				MyWriter.write(writer, "202 请先连接端口："+serverThread.getPort());
				return false;
			}
			else if(serverThread.getSon()==null||serverThread.getSon().isClosed()) {
				writer.write("202 请先执行PASV命令或EPRT命令");
				writer.write("\r\n");
				writer.flush();
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
