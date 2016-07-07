package g620apps.listviewbackend;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androstock.populatelistview.R;

import java.util.ArrayList;
import java.util.HashMap;

// A project by Ferdousur Rahman Shajib, Founder of www.androstock.com

public class IsoAdapter extends BaseAdapter {


    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    public ImageLoader imageLoader;


    public IsoAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        imageLoader=new ImageLoader(activity, activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderVideo holder = null;
        if (convertView == null) {
            holder = new ViewHolderVideo();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_row, null);

            holder.title = (TextView) convertView
                    .findViewById(R.id.title);
            holder.sdetails = (TextView) convertView
                    .findViewById(R.id.sdetails);

            holder.list_image = (ImageView) convertView
                    .findViewById(R.id.list_image);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolderVideo) convertView.getTag();
        }
        holder.title.setId(position);
        holder.sdetails.setId(position);
        holder.list_image.setId(position);


        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        holder.title.setText(song.get(MainActivity.KEY_TITLE));
        holder.sdetails.setText(song.get(MainActivity.KEY_SDETAILS));

        imageLoader.DisplayImage(song.get(MainActivity.KEY_THUMB_URL), holder.list_image);

        return convertView;

    }
}

class ViewHolderVideo {
    TextView title, sdetails;
    ImageView list_image;

}