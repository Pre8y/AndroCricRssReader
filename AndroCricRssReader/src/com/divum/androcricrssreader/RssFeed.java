package com.divum.androcricrssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RssFeed {
	private URL url ;
	
	protected RssFeed(String stringUrl)
	{
		try {
			url = new URL(stringUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("wrong url"+e);
		}
	}

	protected InputStream loadPage() {
		InputStream stream = null;
		try{
			stream =  url.openConnection().getInputStream();
			System.out.println("-----loadPage----");
		}catch (IOException e) {
			throw new RuntimeException("IO Exception occurrs"+e);
		}
		return stream;
	}
	

}
