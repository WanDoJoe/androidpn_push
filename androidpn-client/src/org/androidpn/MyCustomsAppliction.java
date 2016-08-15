package org.androidpn;

import java.io.OutputStream;

import org.litepal.LitePalApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyCustomsAppliction extends LitePalApplication{
	public static RequestQueue queue;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		queue=Volley.newRequestQueue(this);
		try {
//			getRoot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取root权限
	public void getRoot() throws Exception{
		Process process =Runtime .getRuntime().exec("su");
		OutputStream os=process.getOutputStream();
		os.write("ls /system/app".getBytes());
		os.flush();
		os.close();
		
	}
}
