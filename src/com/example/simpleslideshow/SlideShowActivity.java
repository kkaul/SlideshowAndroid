package com.example.simpleslideshow;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SlideShowActivity extends Activity implements Runnable, OnTouchListener {

	Handler handler;
	ImageView slide;
	int currSlide;
	Bitmap[] all_images;
	String[] img_files = { "bear", "elephant", "giraffe", "lion", "monkey",
			"tiger" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_show);
		//Load all bitmaps
		loadImages();
		slide = (ImageView) findViewById(R.id.imageView1);
		
		//What to do when the image is touched?
		slide.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {//What to do when the image is touched?
				if ( arg1.getActionMasked() == MotionEvent.ACTION_UP){
					animate();
				}
				return true;
			}
		});
		currSlide = -1;
		animate();
	}

	private void loadImages() {
		// Load in all the images by using a loop and invoking loadImage
		try {
			all_images = new Bitmap[img_files.length];
			for (int i = 0; i < all_images.length; i++) {
				all_images[i] = loadImage(img_files[i] + ".jpg");
			}
		} catch (IOException e) {
			Toast.makeText(this, "Unable to load images", Toast.LENGTH_LONG)
					.show();
			finish();
		}
	}

	private Bitmap loadImage(String filename) throws IOException {
		AssetManager am = this.getAssets();
		InputStream img_stream = null;
		img_stream = am.open(filename);
		return BitmapFactory.decodeStream(img_stream);
	}

	private void animate() {
		currSlide = (currSlide + 1) % all_images.length;
		slide.setImageBitmap(all_images[currSlide]);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
