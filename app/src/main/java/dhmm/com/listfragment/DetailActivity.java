package dhmm.com.listfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetailActivity extends Activity {

    Data mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mData = (new Gson()).fromJson((String) intent.getExtras().get("data"), Data.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WebView webView = (WebView) findViewById(R.id.webView);
        TextView titleView = (TextView) findViewById(R.id.textView);
        TextView contentView = (TextView) findViewById(R.id.textView2);
        //webView.setLayoutParams();

        if(mData.mHasUrl){
            titleView.setVisibility(View.GONE);
            contentView.setVisibility(View.GONE);
            webView.loadUrl(mData.mUrl);
        }
        else{
            webView.setVisibility(View.GONE);
            titleView.setText(mData.mTitle);
            contentView.setText(mData.mContent);
        }
    }
}
