package org.androidpn.client.receiver;

import org.androidpn.client.Constants;
import org.androidpn.client.ServiceManager;
import org.androidpn.demoapp.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Boot Complete Receiver", Toast.LENGTH_SHORT).show();
		SharedPreferences sp=context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, context.MODE_PRIVATE);
		if(sp.getBoolean(Constants.SETTINGS_AUTO_START, true)){
			ServiceManager serviceManager = new ServiceManager(context);
	        serviceManager.setNotificationIcon(R.drawable.notification);
	        serviceManager.startService();
		}
	}

}
