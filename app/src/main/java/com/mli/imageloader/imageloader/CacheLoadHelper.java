package com.mli.imageloader.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by crown on 2016/10/21.
 */
public class CacheLoadHelper {

	private LruCache<String, Bitmap> mLruCache;

	public static CacheLoadHelper mInstance = null;

	public static CacheLoadHelper getInstance() {
		if(mInstance == null) {
			mInstance = new CacheLoadHelper();
		}
		return mInstance;
	}

	public CacheLoadHelper() {
		int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
		mLruCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	public void saveBitmap(String url, final Bitmap bitmap) {
		mLruCache.put(url, bitmap);
	}

	public Bitmap getBitmap(String url) {
		return mLruCache.get(url);
	}

}
