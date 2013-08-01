package cn.jinbi.app;

import java.io.IOException;

import cn.jinbi.app.fileStream.FileService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{
	private TextView headResult;
	private TextView contentRsult;
	private String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		start();
		get();
		show();
	}
	
	private final void start(){
		headResult = (TextView)findViewById(R.id.headresult);
		contentRsult = (TextView)findViewById(R.id.contentresult);
	}
	
	private final void get(){
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		name = data.getString("name");
		System.out.println(name);
	}
	
	private final void show(){
		headResult.setText(name);
		FileService service = new FileService(getApplicationContext());
		try {
			String data = service.read(name);
			contentRsult.setText(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
