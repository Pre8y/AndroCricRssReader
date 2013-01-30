package com.divum.androcricrssreader;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FeedAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater inflater;
	private int resourceId;
	private List<EntryEntity> enteries;
	
	public FeedAdapter(Context context, int textViewResourceId, List<EntryEntity> enteries) {
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		resourceId = textViewResourceId;
		this.enteries = enteries;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{	
		EntryEntity entity;
		convertView = (RelativeLayout) inflater.inflate(resourceId, null);
		entity = enteries.get(position);
		ImageView image = (ImageView) convertView.findViewById(R.id.imageRow);
		image.setImageResource(R.drawable.pic);
		image.setBackgroundColor(context.getResources().getColor(android.R.color.black));
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(entity.getTitle());
		TextView pubDate = (TextView) convertView.findViewById(R.id.pubDate);
		pubDate.setText(entity.getPubDate());
		return convertView ;
	}

	@Override
	public int getCount() {
		
		return enteries.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return enteries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
