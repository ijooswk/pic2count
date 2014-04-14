package pe.ijooswk.pic2count;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Pick_Details extends LinearLayout {
	private Context mContext;
	public static int RESULT_LOAD_IMAGE = 2004;
	private Activity mActivity;
	private ImageView imageView;
	
	public Pick_Details(Context context) {
		super(context);
		mContext = context;
		mActivity = (Activity)mContext;
		Init();
	}
	
	private void Init(){
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.pic_detail_view, this, true);

		Button galleryBtn = (Button)findViewById(R.id.pic_gallery_btn);
		galleryBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				mActivity.startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
		imageView = (ImageView)findViewById(R.id.pic_gallery_image);
	}
	
	public void setImage(String picturePath){
//		imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		imageView.setImageBitmap(getScaledBitmap(picturePath, 800, 800));
	}
	
	private Bitmap getScaledBitmap(String picturePath, int width, int height) {
	    BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
	    sizeOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(picturePath, sizeOptions);

	    int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

	    sizeOptions.inJustDecodeBounds = false;
	    sizeOptions.inSampleSize = inSampleSize;

	    return BitmapFactory.decodeFile(picturePath, sizeOptions);
	}
	
	private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        // Calculate ratios of height and width to requested height and
	        // width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);

	        // Choose the smallest ratio as inSampleSize value, this will
	        // guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }

	    return inSampleSize;
	}
}
