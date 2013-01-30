package com.divum.androcricrssreader;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	
	private Handler handler = new Handler();
	private int progressStatus;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(progressStatus<10)
//				{
//					progressStatus = doSomeWork();
//					System.out.println("----thread started----");
//				}
//				handler.post(new Runnable() {
//					
//					@Override
//					public void run() {
//						//---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
//						prg.setVisibility(8);
//					}
//				});
//			}
//			private int doSomeWork()
//			{
//				try{
					startActivity(new Intent(getBaseContext(), RssReaderActivity.class));
//				}catch (Exception e) {
//					e.getMessage();
//				}
//				return ++progressStatus;
//			}
//		}).start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
