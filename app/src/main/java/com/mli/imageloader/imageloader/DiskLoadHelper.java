package com.mli.imageloader.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by crown on 2016/10/22.
 */
public class DiskLoadHelper {

	private static DiskLoadHelper mInstance;

	public static DiskLoadHelper getInstance() {
		if(mInstance == null) {
			mInstance = new DiskLoadHelper();
		}
		return mInstance;
	}
	private String mParentPath;

	private String getFileName(String url) {
		return mParentPath + MD5Encoder.encode(url) + ".jpg";
	}

	public void save(String url, Bitmap bitmap) {
		createParentFileIfNeed();
		File file = new File(getFileName(url));
		if(!file.exists()) {
			createFile(file, url, bitmap);
		}
	}

	public Bitmap get(String url) {
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			createParentFileIfNeed();
			File file = new File(getFileName(url));
			if(file.exists()){
				Bitmap bitmap = BitmapFactory.decodeFile(getFileName(url));
				return bitmap;
			}
		}
		return null;
	}

	private void createParentFileIfNeed() {
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File sdcardDir = Environment.getExternalStorageDirectory();
			mParentPath = sdcardDir.getPath() + "/Dobelity/";
			File path = new File(mParentPath);
			if(!path.exists()) {
				path.mkdirs();
			}
		}
	}

	private void createFile(File file, String url, Bitmap bitmap) {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String getSDCardPath() {
		File SDDir = null;
		boolean exist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if(exist) {
			SDDir = Environment.getExternalStorageDirectory();
		}
		if(SDDir != null) {
			return SDDir.toString();
		}
		return null;
	}

}
