package com.mli.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mli.imageloader.imageloader.ImageLoader;
import com.mli.imageloader.imageloader.NetLoadHelper;

/**
 * Created by crown on 2016/10/22.
 */
public class TestActivity extends Activity {

	private static final String mUrl = "http://y0.ifengimg.com/dbcc8e45854c158f/2012/1229/re_50de53b09d783.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView imageView0 = (ImageView) findViewById(R.id.imageview0);
		final ImageView imageView1 = (ImageView) findViewById(R.id.imageview1);
		final ImageView imageView2 = (ImageView) findViewById(R.id.imageview2);

		ImageLoader.getInstance().display(imageView0, mUrl);

		ImageLoader.getInstance().display(imageView1, mUrl);
		ImageLoader.getInstance().display(imageView2, mUrl);
	}
}
