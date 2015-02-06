package com.example.zhangyayan_text6;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter{
	List<News> list;
	
	Context context;

	public Myadapter(List<News> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Helper h;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.circle_main, null);
			h = new Helper();
			h.avatar  = (ImageView) convertView.findViewById(R.id.avatar);
			h.content = (TextView) convertView.findViewById(R.id.content);
			h.created_at = (TextView) convertView.findViewById(R.id.publish_time);
			h.username = (TextView) convertView.findViewById(R.id.username);
			h.username_last = (TextView) convertView.findViewById(R.id.username_last);
			
			convertView.setTag(h);
		}else{
			 h = (Helper) convertView.getTag();
		}
		News  m = list.get(position);
		h.content.setText(m.getContent());
		h.created_at.setText(m.getCreated_at());
		h.username.setText(m.getUsername());
		h.username_last.setText(m.getLast_up_users());
		
		
		return convertView;
	}
	
	class Helper {
		ImageView avatar;
		TextView username,content,created_at,username_last;
	}
}
