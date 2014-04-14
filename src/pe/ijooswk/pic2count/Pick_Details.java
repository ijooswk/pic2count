package pe.ijooswk.pic2count;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	}
}
