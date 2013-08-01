package cn.jinbi.app;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.jinbi.app.fileStream.FileService;

public class MainActivity extends Activity {
	private Button keep;
	private Button histroy;
	private EditText headline;
	private EditText content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start();
		keep.setOnClickListener(new keepOnClickListener());
		histroy.setOnClickListener(new histroyOnClickListener());
	}

	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*¿Ø¼þ³õÊ¼»¯*/
    private void start(){
    	keep = (Button)findViewById(R.id.keep);
		histroy = (Button)findViewById(R.id.histroy);
		headline = (EditText)findViewById(R.id.headline);
		content = (EditText)findViewById(R.id.content);
    }
	
    private final class keepOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			String filename = headline.getText().toString();
			String cont = content.getText().toString();
			FileService service = new FileService(getApplicationContext());
			try {
				service.save(filename,cont); 
				Toast.makeText(getApplicationContext(),R.string.filesuccess, Toast.LENGTH_LONG).show() ;
			} catch (IOException e) {
 			    Toast.makeText(getApplicationContext(), R.string.filefail, Toast.LENGTH_LONG).show();
			}
		}
    }
    
    private final class histroyOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, HistroyActivity.class);
			startActivity(intent);
		}
    	
    }
}
