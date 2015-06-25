package com.example.asynctaskexample.utils.impl;

import com.example.asynctaskexample.utils.IImageUtils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImageUtils implements IImageUtils {

	@Override
	//notice: the code for this implementation came from this article:
	//http://stackoverflow.com/questions/12891520/how-to-programmatically-change-contrast-of-a-bitmap-in-android
	public Bitmap setContrast(Bitmap image, double contrastValue) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    // image size
	    int width = image.getWidth();
	    int height = image.getHeight();

	    // create a mutable empty bitmap
	    Bitmap bmOut = Bitmap.createBitmap(width, height, image.getConfig());

	    // create a canvas so that we can draw the bmOut Bitmap from source bitmap
	    Canvas c = new Canvas();
	    c.setBitmap(bmOut);

	    // draw bitmap to bmOut from src bitmap so we can modify it
	    c.drawBitmap(image, 0, 0, new Paint(Color.BLACK));

	    // color information
	    int A, R, G, B;
	    int pixel;
	    // get contrast value
	    double contrast = Math.pow((100 + contrastValue) / 100, 2);

	    // scan through all pixels
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get pixel color
	            pixel = image.getPixel(x, y);
	            A = Color.alpha(pixel);
	            // apply filter contrast for every channel R, G, B
	            R = Color.red(pixel);
	            R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(R < 0) { R = 0; }
	            else if(R > 255) { R = 255; }

	            G = Color.green(pixel);
	            G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(G < 0) { G = 0; }
	            else if(G > 255) { G = 255; }

	            B = Color.blue(pixel);
	            B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(B < 0) { B = 0; }
	            else if(B > 255) { B = 255; }

	            // set new pixel color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	    return bmOut;
	}
}
