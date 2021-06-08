package top.selzt.FTPServer.Thread;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

import top.selzt.FTPServer.Command.Command;
import top.selzt.FTPServer.Command.Impl.CommandFactory;
import top.selzt.FTPServer.Command.Impl.PassCommand;
import top.selzt.FTPServer.Command.Impl.UserCommand;
public class ServerThread extends Thread{
	private int count=0;
	public final ThreadLocal<String> USER = new ThreadLocal<>();
	private Thread t;
	private Socket s;
	private Socket son;
	private Writer ascWriter;
	private BufferedReader ascReader;
	private BufferedInputStream binInput;
	private BufferedOutputStream binOut;
	public String baseList = "C:\\FTPTest";//根目录
	private int port = -1; //PASV的标识符以及端口
	private boolean isLogin = false;
	private int model = 0; //0：ASCII模式 ；1：Binary模式
	private int eprt = 0; //eprt标识符
	private int flagPort = 0;//port标识符
	private String PORThost; //port命令的ip
	private int PORTport;//主动连接(eprt/port)的端口
	private InetAddress EPRTIp;//eprt命令的ip
	
	public InetAddress getEPRTIp() {
		return EPRTIp;
	}
	public void setEPRTIp(InetAddress ePRTIp) {
		EPRTIp = ePRTIp;
	}
	public void run() {
		System.out.println("hello");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(s.getInputStream(),"GB2312"));
			Writer writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(),"GB2312"));
			while(true) {
				if(count==0) {
					writer.write("220 Service ready for new user.");
					writer.write("\r\n");
					writer.flush();
					count++;
				}
				else {
					if(!s.isClosed()) {
						String commandTxt = reader.readLine();
						if(commandTxt!=null) {
							System.out.println(commandTxt);
							String[] datas = commandTxt.split(" ");
							Command command = CommandFactory.createCommand(datas[0]);
							if(verify(command)) {
								if(command==null) {
									writer.write("502 命令不存在，请重新输入！");
									writer.write("\r\n");
									writer.flush();
								}
								else {
									String data = "";
									if(datas.length>=2) {
										data = datas[1];
									}
									command.getResult(data, writer, this);
								}
							}
							else {
								writer.write("532 当前尚未登录！");
								writer.write("\r\n");
								writer.flush();
							}
						}
					}
					else {
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public boolean verify(Command command) {
		if(command instanceof UserCommand || command instanceof PassCommand)
			return true;
		else
			return isLogin;
	}
	public String getPORThost() {
		return PORThost;
	}
	public void setPORThost(String pORThost) {
		PORThost = pORThost;
	}
	public int getPORTport() {
		return PORTport;
	}
	public void setPORTport(int pORTport) {
		PORTport = pORTport;
	}
	public int getFlagPort() {
		return flagPort;
	}
	public void setFlagPort(int flagPort) {
		this.flagPort = flagPort;
	}
	public int getEprt() {
		return eprt;
	}
	public void setEprt(int eprt) {
		this.eprt = eprt;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public Writer getAscWriter() {
		return ascWriter;
	}
	public void setAscWriter(Writer ascWriter) {
		this.ascWriter = ascWriter;
	}
	public BufferedReader getAscReader() {
		return ascReader;
	}
	public void setAscReader(BufferedReader ascReader) {
		this.ascReader = ascReader;
	}
	public BufferedInputStream getBinInput() {
		return binInput;
	}
	public void setBinInput(BufferedInputStream binInput) {
		this.binInput = binInput;
	}
	public BufferedOutputStream getBinOut() {
		return binOut;
	}
	public void setBinOut(BufferedOutputStream binOut) {
		this.binOut = binOut;
	}
	
	public Socket getSon() {
		return son;
	}
	public void setSon(Socket son) {
		this.son = son;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ServerThread(Socket s) {
		this.s = s;
		
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public Thread getT() {
		return t;
	}
	public void setT(Thread t) {
		this.t = t;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}
