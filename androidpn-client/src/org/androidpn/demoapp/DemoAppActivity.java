/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.demoapp;

import java.util.ArrayList;
import java.util.List;

import org.androidpn.aidl.TestAidl;
import org.androidpn.client.ServiceManager;
import org.androidpn.client.activity.NotificationHistoryActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This is an androidpn client demo application.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class DemoAppActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("DemoAppActivity", "onCreate()...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
//        TestAidl
        
        try {
        	String msg=getIntent().getStringExtra("msg");
            new AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("确定", null)
            .show();
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        // Settings
        Button okButton = (Button) findViewById(R.id.btn_settings);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ServiceManager.viewNotificationSettings(DemoAppActivity.this);
            }
        });
        
        Button historyButton=(Button) findViewById(R.id.btn_history);
        historyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(DemoAppActivity.this,NotificationHistoryActivity.class);
				startActivity(intent);
			}
		});
        // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.notification);
        serviceManager.startService();
        serviceManager.setAlias("asd123");
        List<String> tagsList=new ArrayList<String>();
        tagsList.add("sports");
        tagsList.add("music");
        serviceManager.setTags(tagsList);
    }

}