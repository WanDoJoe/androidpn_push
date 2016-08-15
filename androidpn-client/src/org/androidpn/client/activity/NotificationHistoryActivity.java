package org.androidpn.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.androidpn.client.Constants;
import org.androidpn.client.NotificationDetailsActivity;
import org.androidpn.client.model.NotificationHistory;
import org.androidpn.demoapp.R;
import org.litepal.crud.DataSupport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NotificationHistoryActivity extends Activity {
	private ListView listView;
	private List<NotificationHistory> list=new ArrayList<NotificationHistory>();
	MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationhistory_layout);
		list=DataSupport.findAll(NotificationHistory.class);//查询表中数据
		
		listView=(ListView) findViewById(R.id.notificationhistory_lv);
		adapter=new MyAdapter(this,0, list);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int postion,
					long arg3) {
				NotificationHistory history=list.get(postion);
				Intent intent = new Intent(NotificationHistoryActivity.this,NotificationDetailsActivity.class);
	            intent.putExtra(Constants.NOTIFICATION_API_KEY, history.getApiKey());
	            intent.putExtra(Constants.NOTIFICATION_TITLE, history.getTitle());
	            intent.putExtra(Constants.NOTIFICATION_MESSAGE, history.getMessage());
	            intent.putExtra(Constants.NOTIFICATION_URI, history.getUri());
	            intent.putExtra(Constants.NOTIFICATION_IMAGEURL, history.getImageUrl());
	            intent.putExtra(Constants.NOTIFICATION_TIMA, history.getTime());
	            startActivity(intent);
			}
		});
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//上下文菜单
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 0, 0, "删除");//第二个为id
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getItemId()==0){
			//告诉用户 在那个子项进行长按
			AdapterContextMenuInfo adapterContextMenuInfo=(AdapterContextMenuInfo) item.getMenuInfo();
			int index=adapterContextMenuInfo.position;
			NotificationHistory history=list.get(index);
			history.delete();
			list.remove(index);
			adapter.notifyDataSetChanged();
		}
		
		return super.onContextItemSelected(item);
	}
	
	class MyAdapter extends ArrayAdapter<NotificationHistory>{

		public MyAdapter(Context context, int resource,
				List<NotificationHistory> objects) {
			super(context, resource, objects);
			
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			NotificationHistory history=getItem(position);
			if(convertView==null){
				view =LayoutInflater.from(getContext()).inflate(R.layout.notificationhistory_item, null);
			}else{
				view=convertView;
			}
			
			TextView title=(TextView) view.findViewById(R.id.notification_item_title);
			TextView time=(TextView) view.findViewById(R.id.notification_item_time);
			title.setText(history.getTitle());
			time.setText(history.getTime());
			
			
			return view;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}

}
