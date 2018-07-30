package dhmm.com.listfragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
                            implements  BlankFragment.OnFragmentInteractionListener{

    BlankFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onItemSelected(int position){
        
    }
}
