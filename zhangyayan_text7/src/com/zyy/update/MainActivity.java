package com.zyy.update;

import com.szy.update.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	SharedPreferences sp;
	Editor editor;
	int total = 0;//jishu
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		sp = this.getSharedPreferences("count", Context.MODE_PRIVATE);
		editor = sp.edit();
		total = read();
		write(++total);

		Log.i("mytag", "次数为：" + read() + "=======" + total);

		if (total <= 3) {
			UpdateManager manager = new UpdateManager(MainActivity.this);
			manager.checkUpdate();
		}

		
		
		
	
		Button updateBtn = (Button) findViewById(R.id.btnUpdate);
		updateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UpdateManager manager = new UpdateManager(MainActivity.this);
				// 检查软件更新
				manager.checkUpdate();
			}
		});

	}

	private void write(int i) {
		// TODO Auto-generated method stub
		
	}

	private int read() {
		// TODO Auto-generated method stub
		return 0;
	}
}