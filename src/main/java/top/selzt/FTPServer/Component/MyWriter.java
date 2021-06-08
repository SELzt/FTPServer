package top.selzt.FTPServer.Component;

import java.io.IOException;
import java.io.Writer;

public class MyWriter {
	public static void write(Writer writer,String data) {
		try {
			writer.write(data);
			writer.write("\r\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
