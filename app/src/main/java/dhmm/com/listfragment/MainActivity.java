package dhmm.com.listfragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity
                            implements  BlankFragment.OnFragmentInteractionListener {

    BlankFragment fragment;
    ArrayList<Data> mData;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonLoaderTask task = new JsonLoaderTask();
        task.setOnJsonLoadListener(new JsonLoaderTask.onJsonLoadCompleteListener() {
            @Override
            public void onJsonLoad(ArrayList<Data> data) {
                mData = data;
                fragment = BlankFragment.newInstance(mData);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, fragment);
                ft.commit();
            }
        });
        task.execute("");
    }

    public void loadListView(){

        try{


            ArrayList data = new ArrayList<Data>();
            JSONObject item = new JSONObject();

            item.put("title", "this is a title");
            item.put("content", "this is the content");
            item.put("hasImage", false);
            item.put("hasUrl", false);
            //data.add(item);
            fragment = BlankFragment.newInstance(mData);
            //Bundle args = new Bundle();
            //args.putSerializable("data", data);
            //fragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();

        }
        catch (Exception e){
        }

    }

    public void onItemSelected(int position){

    }
}
