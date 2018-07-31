package dhmm.com.listfragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonLoaderTask task = new JsonLoaderTask();
        task.setOnJsonLoadListener(new JsonLoaderTask.onJsonLoadCompleteListener() {
            @Override
            public void onJsonLoad(ArrayList<Data> data) {
                mData = data;
                fragment = BlankFragment.newInstance(data);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, fragment);
                ft.commit();
            }
        });
        task.execute("");
    }

    public void onItemSelected(int position){
        Intent intent = new Intent(this, DetailActivity.class);
        String dataStr = (new Gson()).toJson(mData.get(position));
        intent.putExtra("data", dataStr);
        //intent.putExtra("data", dataStr);
        startActivity(intent);
    }
}
