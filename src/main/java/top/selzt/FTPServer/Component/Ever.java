package top.selzt.FTPServer.Component;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class Ever {
	//根目录
	public static String rootDir = "C:\\FTPTest";
	//能够登陆的用户
	public static HashMap<String,String> users = new HashMap<>();
	//已登录的用户
	public static HashSet<String> loginedUser = new HashSet<>();
	//拥有put、get权限的用户
	public static HashSet<String> admin = new HashSet<>();
	
	public static void init() {
		System.out.println("正在进行初始化");
		File r = new File(rootDir);
		if(!r.exists()) {
			r.mkdir();
		}
		String path=System.getProperty("user.dir");
		String userTxt = "/user.txt";
		String adminTxt = "/admin.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path+userTxt)));
			String temp;
			while((temp=reader.readLine())!=null) {
				String[] user = temp.split(" ");
				users.put(user[0], user[1]);
				//System.out.println(temp);
			}
			reader.close();	
			reader = new BufferedReader(new FileReader(new File(path+adminTxt)));
			while((temp=reader.readLine())!=null) {
				admin.add(temp);
			}
			reader.close();
			System.out.println("初始化完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("初始化失败");
		}
	}
}
