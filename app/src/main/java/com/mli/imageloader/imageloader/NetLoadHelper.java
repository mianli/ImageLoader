package com.mli.imageloader.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by crown on 2016/10/21.
 */
public class NetLoadHelper implements Runnable{

	private ImageView mImageView;
	private String mUrl;
	private Handler mHandler;

	public NetLoadHelper(Handler handler, ImageView imageView, String url) {
		this.mHandler = handler;
		this.mImageView = imageView;
		this.mUrl = url;
	}

	@Override
	public void run() {
		final Bitmap bitmap = getNetBitmap(mUrl);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if(bitmap != null){
					mImageView.setImageBitmap(bitmap);
				}
			}
		});
	}

	private Bitmap getNetBitmap(String url) {
		Bitmap bitmap = null;
		try {

			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			connection.setReadTimeout(5 * 1000);
			connection.setConnectTimeout(5 * 1000);
			connection.connect();
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(is);

				CacheLoadHelper.getInstance().saveBitmap(url, bitmap);
				DiskLoadHelper.getInstance().save(url, bitmap);
			}else {
				Log.i("ImageLoader", "网络请求失败， code :" + connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
