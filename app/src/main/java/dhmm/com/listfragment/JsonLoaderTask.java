package dhmm.com.listfragment;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dhawalmajithia on 7/31/18.
 */



public class JsonLoaderTask extends AsyncTask<String,Void,ArrayList<Data>> {


    onJsonLoadCompleteListener mListener;

    public interface onJsonLoadCompleteListener{
        void onJsonLoad(ArrayList<Data> data);
    }

    public void setOnJsonLoadListener(onJsonLoadCompleteListener listener){
        mListener = listener;
    }

    @Override
    protected ArrayList<Data> doInBackground(String... urls) {
        try{
            ArrayList<Data> data;
            URL url = new URL("https://aggiefeed.ucdavis.edu/api/v1/activity/public?s=0?l=10");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            try {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
                String responseStr = total.toString();
                JSONArray responseArray = new JSONArray(responseStr.trim());
                data = new ArrayList<Data>(responseArray.length());
                for(int i = 0; i < responseArray.length(); i++){
                    JSONObject item = responseArray.getJSONObject(i);
                    JSONObject dataItem = new JSONObject();
                    dataItem.put("title", item.getString("title"));
                    dataItem.put("content", item.getJSONObject("object").getString("content"));
                    boolean hasUrl = item.getJSONObject("object").getString("ucdSrcId").contains("www");
                    dataItem.put("hasUrl", hasUrl);
                    dataItem.put("url", item.getJSONObject("object").getString("ucdSrcId"));
                    dataItem.put("hasImage", false);
                    data.add(new Data(dataItem));

                }
                return data;
                //loadListView();
            } finally {
                urlConnection.disconnect();
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Data> data) {
        super.onPostExecute(data);
        if(mListener != null){
            mListener.onJsonLoad(data);
        }
    }
}
