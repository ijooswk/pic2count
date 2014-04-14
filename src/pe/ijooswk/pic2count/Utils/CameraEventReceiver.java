package pe.ijooswk.pic2count.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

public class CameraEventReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Cursor cursor = context.getContentResolver().query(intent.getData(), null, null, null, null);
	    cursor.moveToFirst();
	    String image_path = cursor.getString(cursor.getColumnIndex("_data"));
	    Toast.makeText(context, "New Photo is Saved as : -" + image_path, 1000).show();
	}

}
