package org.androidpn.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDataAndTime {

	
	public static String getDataAndTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String data=df.format(new Date());// new Date()为获取当前系统时间
		
		
		return data;
		
	}
}
