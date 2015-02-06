package com.example.zhangyayan_text6;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {
	
	String path = "http://192.172.10.91:8080/json/new.json";

	List<News> list;
	HttpClient client;
	GridView gv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_main);	
		//��������
		List<News> list = new ArrayList<News>();
		client = new DefaultHttpClient();
		HttpPost post = new HttpPost(path);
		
		try {
			//��������
			client = new DefaultHttpClient();
			HttpPost pos = new HttpPost(path);
			
        	HttpResponse response = client.execute(post);
			//�鿴�����ʽ
			if(response.getStatusLine().getStatusCode()==200){
				//����
				list = new ArrayList<News>();
				
				//���
				InputStream is = response.getEntity().getContent();
				//��ȡ�ֽ�
				byte[] b = new byte[1024 * 100];
				int lenght = 0;
				StringBuilder sb = new StringBuilder();
				while((lenght = is.read(b)) != -1){
					sb.append(new String(b,0,lenght));
				}
				//�����ȡ����
				System.out.println("fghjfgh"+sb.toString());
				
				//���� JSONObject  JSONArray
				JSONObject js = new JSONObject(sb.toString());
				JSONArray ja = js.getJSONArray("data");
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jj = ja.getJSONObject(i);
					// ���ݺ�ʱ�����ݽ���
					String content = jj.optString("content");
					String created_at = jj.optString("created_at");
					// ����--������ͼƬ
					JSONObject jo = jj.getJSONObject("author");
					String username = jo.optString("username");
					String author = jo.optString("avatar");

					JSONArray arr = jj.getJSONArray("last_up_users");
					for (int j = 0; j < arr.length(); j++) {
						JSONObject item = arr.getJSONObject(j);
						String last_up_users = item.optString("username");
//						list.add(new News(avatar, username, content, created_at,
//								username_last));
						list.add(new News(content, created_at, author, username, last_up_users));
						}
					}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		MyListView lv = new MyListView(this);
		setContentView(lv);
		
		Myadapter adapter = new Myadapter(list, MainActivity.this);
		lv.setAdapter(adapter);
		}

		
	}
