package com.mli.imageloader.imageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by crown on 2016/10/21.
 */
public class ImageLoader {

	private static final int THREAD_COUNT = 5;
	private static final String TAG = "ImageLoaderTag";

	private ExecutorService mExecutor;
	private Handler handler = new Handler();

	private static ImageLoader mInstance = null;

	public ImageLoader() {
		mExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
	}

	public static ImageLoader getInstance() {
		if(mInstance == null) {
			mInstance = new ImageLoader();
		}
		return mInstance;
	}

	public void display(final ImageView imageView, final String url) {

		Future<Void> future = (Future<Void>) mExecutor.submit(new NetLoadHelper(handler, imageView, url));

//		future.cancel(true);
//		handler.post(new Runnable() {
//			@Override
//			public void run() {
//
//				Bitmap cacheBitmap;
//		if((cacheBitmap = CacheLoadHelper.getInstance().getBitmap(url)) != null) {
//			imageView.setImageBitmap(cacheBitmap);
//			Log.i(TAG, "display from cache");
//		}else
//				if((cacheBitmap = DiskLoadHelper.getInstance().get(url)) != null) {
//					imageView.setImageBitmap(cacheBitmap);
//			CacheLoadHelper.getInstance().saveBitmap(url, cacheBitmap);
//			Log.i(TAG, "display from disk");
//		}else{
//			new NetLoadHelper(imageView, url);
//			Log.i(TAG, "display from net");
//				}
//			}
//		});
	}

}
