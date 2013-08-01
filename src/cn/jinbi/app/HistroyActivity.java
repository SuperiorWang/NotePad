package cn.jinbi.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HistroyActivity extends Activity{
	private EditText search;
	private Button chick;
	private ListView listView;
	private ArrayList<HashMap<String,Object>> list;
	private final String pathname = "../data/data/cn.jinbi.app/files";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_histroy);
		startInit();
		showList(pathname);
		chick.setOnClickListener(new chickOnClickListener());
		listView.setOnItemClickListener(new ItemClickListener());
		listView.setOnItemLongClickListener(new ItemLongClickListener());
	}
	
	private void startInit(){
		search = (EditText)findViewById(R.id.filename);
		chick = (Button)findViewById(R.id.search);
		listView = (ListView)findViewById(R.id.listhistroy);
	}
 
   /* ��ʾlist�б�*/
	private final void showList(String passname){
		list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map ;
		
		File file = new File(passname);
		File[] childFiles = file.listFiles();
		/*��δ������Ҫ��������һ�죬��ʾ����ļ�������û���ļ��ͷ��أ����û����δ���Ļ��ᱨ��ָ���쳣*/
		if (childFiles == null)  
				return ;
		
		for(File files:childFiles){
			 map = new HashMap<String,Object>();
			 /*文件的名字*/
			 map.put("name", files.getName());
			 /*文件创建的时间*/
			 Date date = new Date(files.lastModified());
			 String time = (date.getMonth()+1)+"/"+date.getDate();
			 map.put("time", time); 
			 list.add(map);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, 
				list, R.layout.list_histroy, new String[]{"time","name"}, new int[]{R.id.time,R.id.name});	
		listView.setAdapter(simpleAdapter);
	}
	
	public class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			TextView text = (TextView)arg1.findViewById(R.id.name);
			Bundle data = new Bundle();
			data.putString("name", text.getText().toString());
			Intent intent = new Intent();
			intent.putExtras(data);
			intent.setClass(HistroyActivity.this, ResultActivity.class);
			startActivity(intent);
		}
	}
	
	
	private class chickOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			list.clear();
			HashMap<String, Object>map;
			String textName = search.getText().toString();
			File file = new File(pathname);
			File[] childFiles = file.listFiles();
			if (childFiles == null)
					return ;
			
			for(File files:childFiles){
				if(files.getName().contains(textName)){
					map = new HashMap<String,Object>();
					String filename = files.getName().toString();
					map.put("name", filename);
					Date date = new Date(files.lastModified());
					String time = date.getMonth()+"/"+date.getDate();
					map.put("time", time); 
					list.add(map);
				}
			}
			SimpleAdapter adapter= (SimpleAdapter)listView.getAdapter();
			adapter.notifyDataSetChanged();
		}
	}
	
	private class ItemLongClickListener implements OnItemLongClickListener{
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			TextView text = (TextView)arg1.findViewById(R.id.name);
			String name = text.getText().toString();
			HashMap<String, Object>map;
			
			list.clear();
			File file = new File(pathname);
			File[] childFiles = file.listFiles();
			if (childFiles == null)
				return false;
			
			for (File files:childFiles){
				if (files.getName().equals(name))
				{
					files.delete();
				}
				else{
					map = new HashMap<String, Object>();
					map.put("name", files.getName());
					Date date =new Date(files.lastModified());
					String time = date.getMonth()+"/"+date.getDay();
					map.put("time", time);
					list.add(map);
				}
			}
			SimpleAdapter adapter = (SimpleAdapter)listView.getAdapter();
			adapter.notifyDataSetChanged();
			return true;
		}
		
	}
}
