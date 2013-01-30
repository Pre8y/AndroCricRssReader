package com.divum.androcricrssreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_page);
		String url;
		Bundle b = getIntent().getBundleExtra("android.intent.extra.INTENT");
		if(b==null)
			url = "not a valid url.";
			else url = b.getString("link");
		System.out.println("-----url is -----"+url);
		WebView wv = (WebView)findViewById(R.id.web);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new HelloWebViewClient());
		wv.loadUrl(url);
	}
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    view.loadUrl(url);
	    return true;
	    }
	}
}
