package top.selzt.FTPServer.Command.Impl;

import top.selzt.FTPServer.Command.Command;

public class CommandFactory {
	public static Command createCommand(String type) {
		type = type.toUpperCase();
		switch(type) {
			//登陆
			case "USER": return new UserCommand();
			case "PASS": return new PassCommand();
			//获取当前目录
			case "PWD":	return new PWDCommand();
			case "XPWD": return new PWDCommand();
			
			//断开连接
			case "BYE": return new ByeCommand();
			case "QUIT": return new ByeCommand();
			
			//更改目录
			case "CD": return new CdCommand();
			case "CWD": return new CdCommand();
			
			//下载文件
			case "GET": return new GetCommand();
			case "RETR": return new GetCommand();
			
			//获取文件列表
			case "LIST": return new ListCommand();
			case "NLST": return new ListCommand();
			
			//上传文件
			case "PUT": return new STORCommand();
			case "STOR": return new STORCommand();
			
			//选择传输模式
			case "TYPE": return new TYPECommand();
			case "ASCII": return new ASCIICommand();
			case "BIN": return new BinCommand();
			
			//主动模式
			case "EPRT": return new EprtCommand();
			case "PORT": return new PORTCommand();
			//被动模式
			case "PASV": return new PasvCommand();
			default:
				return null;
		}
	}
}
