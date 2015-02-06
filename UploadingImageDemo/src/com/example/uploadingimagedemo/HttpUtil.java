package com.example.uploadingimagedemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


public class HttpUtil {

	public static String uploadFile(String spec,String path){
		
		String result = null;
		String end = "\r\n";  
	    String twoHyphens = "--";  
	    String boundary = "******";  
		InputStream inputStream = null;
		
		 try  
		    {  
		      URL url = new URL(spec);  
		      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();  
		      // ����ÿ�δ��������С��������Ч��ֹ�ֻ���Ϊ�ڴ治�����  
		      // �˷���������Ԥ�Ȳ�֪�����ݳ���ʱ����û�н����ڲ������ HTTP �������ĵ�����  
		      httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K  
		      // �������������  
		      httpURLConnection.setDoInput(true);  
		      httpURLConnection.setDoOutput(true);  
		      httpURLConnection.setUseCaches(false);  
		      // ʹ��POST����  
		      httpURLConnection.setRequestMethod("POST");  
		      httpURLConnection.setRequestProperty("Connection", "Keep-Alive");  
		      httpURLConnection.setRequestProperty("Charset", "UTF-8");  
		      httpURLConnection.setRequestProperty("Content-Type",  
		          "multipart/form-data;boundary=" + boundary);  
		  
		      DataOutputStream dos = new DataOutputStream(  
		          httpURLConnection.getOutputStream());  
		      dos.writeBytes(twoHyphens + boundary + end);  
		      String name =path.substring(path.lastIndexOf("/") + 1);
		      dos.writeBytes("Content-Disposition: form-data; name=\"f1\"; filename=\""  
		          +   name
		          + "\""  
		          + end);  
		      dos.writeBytes(end);  
		      Log.e("<<<<<<<<<", name);
		      FileInputStream fis = new FileInputStream(path);  
		      byte[] buffer = new byte[1028]; // 8k  
		      int len = 0;  
		      // ��ȡ�ļ�  
		      while ((len = fis.read(buffer)) >= 0)  
		      {  
		        dos.write(buffer, 0, len);  
		      }  
		      Log.e("<<<<<<<<<<<<", "aaaaaaaaaaaaaa");
		      dos.writeBytes(end);  
		      dos.writeBytes(twoHyphens + boundary + twoHyphens + end);  
		      fis.close();  
		      dos.flush();  
		      InputStream is = httpURLConnection.getInputStream();  
		      InputStreamReader isr = new InputStreamReader(is, "utf-8");  
		      BufferedReader br = new BufferedReader(isr);  
		      result = br.readLine();  
		      dos.close();  
		      is.close();  
		    } catch (Exception e) {  
		      e.printStackTrace();  
		    }  
		 Log.e("<<<<<<<<<<", result);
		return result;
	}
}
