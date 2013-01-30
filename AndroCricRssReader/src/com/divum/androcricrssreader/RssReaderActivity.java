package com.divum.androcricrssreader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class RssReaderActivity extends Activity {
	private String url = "http://live-feeds.cricbuzz.com/CricbuzzFeed";
	private List<EntryEntity> enteries;
	private InputStream stream;
	private RssFeed rssFeed ;
	private ProgressBar prg ;
	private String link;
	private ProgressBar progress2;

	private RssReaderParser rssReaderParser=new RssReaderParser();
	List<String> values=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_reader);
		
		
		prg= (ProgressBar) findViewById(R.id.progress);
		new Parsing().execute();
	}
	
	class Parsing extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {		
			
			// TODO Auto-generated method stub
			System.out.println("--------parsing started------");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rssFeed = new RssFeed(url);
			stream = rssFeed.loadPage();
			enteries = rssReaderParser.parse(stream);
			System.out.println("-----parsed-----");
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			System.out.println("size------"+enteries.size());
			values = new ArrayList<String>();
			for(EntryEntity en: enteries){
				values.add(en.getTitle());
			}
			ListView list = (ListView)findViewById(R.id.list);
			
			
			
			list.setAdapter(new FeedAdapter(getBaseContext(), R.layout.feed_row, enteries));
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					
					displayInBrowser(enteries.get(position).getLink());
				}
			});
			
			prg.setVisibility(8);
			super.onPostExecute(result);
		}
		
	}
	
	
	protected void displayInBrowser(String link) {
		this.link = link;
//		progress2 = (ProgressBar)findViewById(R.id.progress2);
//		new DisplayInBrowser().execute();
		Intent intent = new Intent(getBaseContext(), WebPageActivity.class);
		Bundle b = new Bundle();
		b.putString("link", link);
   	 	intent.putExtra("android.intent.extra.INTENT", b);
		startActivity(intent);
	}
//	class DisplayInBrowser extends AsyncTask<Void, String, Void>{
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			Intent intent = new Intent(getBaseContext(), WebPageActivity.class);
//			Bundle b = new Bundle();
//			b.putString("link", link);
//	   	 	intent.putExtra("android.intent.extra.INTENT", b);
//			startActivity(intent);
//			return null;
//		}
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			progress2.setVisibility(8);
//		}
//		
//	}
}
