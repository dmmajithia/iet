package dhmm.com.listfragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity
                            implements  BlankFragment.OnFragmentInteractionListener{

    BlankFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList data = new ArrayList<Data>();
        JSONObject item = new JSONObject();
        try{
            item.put("title", "this is a title");
            item.put("content", "this is the content");
            item.put("hasImage", false);
            item.put("hasUrl", false);
            data.add(item);
            fragment = BlankFragment.newInstance(data);
            //Bundle args = new Bundle();
            //args.putSerializable("data", data);
            //fragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();

        }
        catch (JSONException e){
        }


    }

    public void onItemSelected(int position){

    }
}
