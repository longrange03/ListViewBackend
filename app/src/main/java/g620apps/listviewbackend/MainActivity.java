package g620apps.listviewbackend;

//throws error in manifest file.  

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.androstock.populatelistview.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_SONG = "item"; // parent node
    static final String KEY_TITLE = "title";
    static final String KEY_SDETAILS = "sdetails";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView listView;
    ProgressBar loading;


    private DownloadData downloadData = new DownloadData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listHome);
        loading = (ProgressBar)findViewById(R.id.emptyElement);

        listView.setEmptyView(loading);

        downloadData = new DownloadData();
        downloadData.execute();
    }



    private  class DownloadData extends AsyncTask<String, Void, Document> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        Document doc = null;
        protected Document doInBackground(String... args) {
            String URL;
            URL = "http://androstock.com/test/listdata.xml";

            try{
                XMLParser parser = new XMLParser();
                String xml = parser.getXmlFromUrl(URL); // getting XML from URL
                doc = parser.getDomElement(xml); // getting DOM element
            }catch (Exception e) {
                Log.d("Exception", e.getMessage());
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            try{
                XMLParser parser = new XMLParser();
                NodeList nl = doc.getElementsByTagName(KEY_SONG);

                for (int i = 0; i < nl.getLength(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    Element e = (Element) nl.item(i);

                    map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
                    map.put(KEY_SDETAILS, parser.getValue(e, KEY_SDETAILS));
                    map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

                    dataList.add(map);
                }


                IsoAdapter adapter = new IsoAdapter(MainActivity.this, dataList);
                listView.setAdapter(adapter);


            }catch (Exception e) {
                Log.d("Exception", e.getMessage());
            }

        }

    }

}

