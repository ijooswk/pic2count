package pe.ijooswk.pic2count.DB;

import android.provider.BaseColumns;

public class DataBases {
	public static final class CreateDB implements BaseColumns {
		public static final String _ID = "_id";
		public static final String LOCATION = "location";
		public static final String DATE = "date";
		public static final String PRICE = "price";
		public static final String WITHWHO = "withwho";
		public static final String WHOBUY = "whobuy";
		public static final String GPSINFO = "gpsinfo";
		public static final String _TABLENAME = "buylist";
		
		//DB 생성 최초 한번만 생성
		public static final String _CREATE = 
				"create table " + _TABLENAME + "(" 
				+ _ID+ " integer primary key autoincrement, "
				+ LOCATION + " text not null , "
				+ DATE + " text not null , "
				+ PRICE + " text not null , "
				+ WITHWHO + " text not null , "
				+ WHOBUY + " text not null , "
				+ GPSINFO + " text not null );";
				
	}
}
