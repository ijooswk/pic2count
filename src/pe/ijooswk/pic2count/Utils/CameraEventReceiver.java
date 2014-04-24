package pe.ijooswk.pic2count.Utils;

import java.io.IOException;

import pe.ijooswk.pic2count.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class CameraEventReceiver extends BroadcastReceiver{
	
	Context mContext;
//	ExifInterface exif;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Cursor cursor = context.getContentResolver().query(intent.getData(), null, null, null, null);
	    cursor.moveToFirst();
	    String image_path = cursor.getString(cursor.getColumnIndex("_data"));
	    Toast.makeText(context, "New Photo is Saved as : -" + image_path, 1000).show();
	    mContext = context;
	    
	    //일단 사진을 찍으면 노티피케이션을 띄운다. 이전것이 떠있으면 지워버려야 하나. 그것은 고민중
	    iniNotification(image_path);
	}

	public void iniNotification(String image){
		  int icon = R.drawable.ic_launcher;
	        long when = System.currentTimeMillis();
	        Notification notification = new Notification(icon, "Custom Notification", when);

	        NotificationManager mNotificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);

	        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout);
	        
	        contentView.setImageViewBitmap(R.id.image, getScaledBitmap(image, 300, 300));
//	        contentView.setImageViewResource(R.id.image, R.drawable.ic_launcher);
	        contentView.setTextViewText(R.id.text, "This is a custom layout");
	        notification.contentView = contentView;

	        Intent notificationIntent = new Intent(mContext, getClass());
	        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
	        notification.contentIntent = contentIntent;

	        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
	        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
	        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
	        notification.defaults |= Notification.DEFAULT_SOUND; // Sound

	        mNotificationManager.notify(1, notification);
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
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }

	    return inSampleSize;
	}
}
