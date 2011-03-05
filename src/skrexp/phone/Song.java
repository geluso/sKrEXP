package skrexp.phone;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Song extends View{
	public String id;
	public String timeStamp;
	
	
	
	public Song(Context context, String id, String timeStamp) {
		super(context);
		this.id = id;
		this.timeStamp = timeStamp;
	}
	
	public String toString(){
		return id + "    " + timeStamp;
	}
}
