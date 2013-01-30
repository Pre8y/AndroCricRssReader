package com.divum.androcricrssreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class RssReaderParser {
	public List<EntryEntity> parse(InputStream stream)
	{
		List<EntryEntity> entities = new ArrayList<EntryEntity>();
		XmlPullParser parser = Xml.newPullParser();
		try{
			parser.setInput(stream, null);
			parser.nextTag();
			entities = feedReader(parser);
		}catch(XmlPullParserException e){
			throw new RuntimeException("parser error"+e);
		}catch(IOException e)
		{
			throw new RuntimeException("IO error while parsing"+e);
		}
		return entities;
	}

	private List<EntryEntity> feedReader(XmlPullParser parser) throws XmlPullParserException, IOException {
		int entityType = parser.getEventType();
		EntryEntity entity=null;
		boolean  done = false;
		List<EntryEntity> entities = new ArrayList<EntryEntity>();
		while(entityType!=XmlPullParser.END_DOCUMENT||!done)
		{
			
			
			switch(entityType)
			{
			case XmlPullParser.START_TAG:
			{	
				String name = parser.getName().toLowerCase();
				System.out.println("-----"+name);
				if(name.equals("item"))
				{
					entity = new EntryEntity();
				}
				else if(entity!=null)
				{
					if(name.equals("title"))
						entity.setTitle(this.readText(parser));
					else if(name.equals("link"))
						entity.setLink(this.readText(parser));
					else if(name.equals("description"))
						entity.setDescripotion(this.readText(parser));
					else if(name.equals("pubdate"))
						entity.setPubDate(this.readText(parser));
				}
			}
			break;
			case XmlPullParser.END_TAG:
			{
				if(parser.getName().equals("item")&& entity!=null)
				{
					entities.add(entity);
					done=true;
				}
			}
			break;
//			default:
				//
			}
			entityType=parser.next();
		}
		System.out.println("------entities size-----"+entities.size());
		return entities;
	}

	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		 if (parser.getEventType() != XmlPullParser.START_TAG) {
		        throw new IllegalStateException();
		    }
		    int depth = 1;
		    while (depth != 0) {
		    	System.out.println("-----skipped--"+parser.getName());
		        switch (parser.next()) {
		        
		        case XmlPullParser.END_TAG:
		            depth--;
		            break;
		        case XmlPullParser.START_TAG:
		            depth++;
		            break;
		        }
		    }
		
		
	}

	private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
		String result;
		result = parser.nextText();
		if(parser.getEventType()!=XmlPullParser.END_TAG)
			parser.nextTag();
		return result;
	}
}
