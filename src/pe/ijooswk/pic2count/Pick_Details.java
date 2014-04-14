package pe.ijooswk.pic2count;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class Pick_Details extends LinearLayout {
	private Context mContext;
	
	public Pick_Details(Context context) {
		super(context);
		mContext = context;
		Init();
	}
	
	private void Init(){
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.pic_detail_view, this, true);
	}
}
