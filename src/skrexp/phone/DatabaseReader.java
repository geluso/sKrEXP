package skrexp.phone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skrexp.phone.R.layout;
 
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class DatabaseReader extends ListActivity{
 
	private Map<Song,TextView> songToView = new HashMap<Song,TextView>();
	private Map<TextView,Song> viewToSong = new HashMap<TextView,Song>();
	
    /** Called when the activity is first created. */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.main);

        ArrayList<Song> listItems = this.fetchTwitterPublicTimeline();
        
        //System.out.println("Hello");
        //TextView tv = new TextView(this);
        //tv.setText("hello");
        //setContentView(tv);
		setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
    }
 
	public ArrayList<Song> fetchTwitterPublicTimeline()
    {
    	ArrayList<Song> listItems = new ArrayList<Song>();
 
    	try {
			//URL twitter = new URL("http://twitter.com/statuses/public_timeline.json");
    		URL twitter = new URL("http://mooncolony.org/json");
			URLConnection tc = twitter.openConnection();
			tc.connect();
			tc.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));
 
			String line;
			while ((line = in.readLine()) != null) {
				JSONArray ja = new JSONArray(line);
 
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					JSONObject jo2 = (JSONObject) jo.get("fields");
					
					Song s = new Song(this,jo2.getString("song"),jo2.getString("time"));
					//TextView tv = (TextView) this.findViewById(R.layout.song);
					TextView tv = new TextView(this);
					tv.setText(s.toString());
					songToView.put(s,tv);
					viewToSong.put(tv, s);
					
					listItems.add(s);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItems;
    }
	
	public void test(View v){
		//Test method
	}
}