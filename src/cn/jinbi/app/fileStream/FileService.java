package cn.jinbi.app.fileStream;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileService {
	private Context context;

	public FileService(Context context) {
		super();
		this.context = context;
	}

	public void save(String filename, String content) throws IOException {
 		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
 		outStream.write(content.getBytes());
 		outStream.close();
	}
	
	public String read(String filename) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		FileInputStream input = context.openFileInput(filename);
		int len = 0;
		while ((len = input.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		return new String(data);
	}
}
