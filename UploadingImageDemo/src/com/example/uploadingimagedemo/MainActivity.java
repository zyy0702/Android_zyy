package com.example.uploadingimagedemo;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static String requestURL = "http://www.yasite.net/api/upload.php";
	private ImageView iv;
	private PopupWindow popupWindow;
	private String picPath = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView) findViewById(R.id.iv);
		View view = getLayoutInflater().inflate(R.layout.popupwindow, null);
		TextView tv = (TextView) view.findViewById(R.id.tv);
		TextView tv1 = (TextView) view.findViewById(R.id.tv1);
		popupWindow =new PopupWindow(view,100,100,true);
		
		iv.setOnClickListener(this);
		tv.setOnClickListener(this);
		tv1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.iv:
			popupWindow.showAsDropDown(v);
			break;
		case R.id.tv:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivity(intent);
			break;
		case R.id.tv1:
			
			Intent intent1 = new Intent();
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent1, 1);
            
			break;

		}
		
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  
	  if(resultCode == Activity.RESULT_OK && null != data){
		  
		  Uri uri = data.getData();
          Log.e("<<<<<<<<<<<<<<", "uri = " + uri);
          try {
              String[] pojo = { MediaStore.Images.Media.DATA };

              Cursor cursor = getContentResolver().query(uri, pojo, null, null, null);
              if (cursor != null) {
                  ContentResolver cr = this.getContentResolver();
                  int colunm_index = cursor
                          .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                  cursor.moveToFirst();
                  String path = cursor.getString(colunm_index);
             
                  if (path.endsWith("jpg") || path.endsWith("png")) {
                      picPath = path;
                      Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                      iv.setImageBitmap(bitmap);
                      if (picPath == null) {
                          Toast.makeText(MainActivity.this, "请选择图片！", 1000).show();
                      } else {
                      	new Thread(){
                      		public void run() {
                      			String result = HttpUtil.uploadFile(requestURL,picPath);
                                  Log.e("<<<<<<<<<<<<<<", result);
                      		};
                      	}.start();
                      }
                  } else {
                      alert();
                  }
              } else {
                  alert();
              }

          } catch (Exception e) {}
      }
}
	
	 private void alert() {
	        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")
	                .setMessage("您选择的不是有效的图片")
	                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        picPath = null;
	                    }
	                }).create();
	        dialog.show();
	    }
}
