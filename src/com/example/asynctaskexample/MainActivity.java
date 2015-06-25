package com.example.asynctaskexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.example.asynctaskexample.utils.impl.ImageUtils;

public class MainActivity extends Activity {
	
	private final double CONTRAST_VALUE = 90.0;
	
	// the ImageView where we'll display the brightened image
	private ImageView ivAndroidIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set content view to the main activity layout
		setContentView(R.layout.activity_main);
		
		// get references to our UI controls
		ivAndroidIcon = (ImageView) findViewById(R.id.displayed_image);
	}
	
	public void onClickListener(View target) {
		// get the Android icon bitmap that's included in this project 
		Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

		new BrightenImageTask().execute(originalBitmap);
	}
	
	class BrightenImageTask extends AsyncTask<Bitmap, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(Bitmap... params) {
			// create a new image that's much brighter than the original image
			ImageUtils imageConverter = new ImageUtils();

			return imageConverter.setContrast(params[0], CONTRAST_VALUE);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// display the updated image
			ivAndroidIcon.setImageBitmap(result);
		}
	}
}
