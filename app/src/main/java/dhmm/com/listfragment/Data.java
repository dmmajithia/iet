package dhmm.com.listfragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhawalmajithia on 7/29/18.
 */

public class Data {

    String mTitle, mContent, mUrl, mImageUrl;
    Boolean mHasImage, mHasUrl;

    public Data(){

    }

    public Data Data(JSONObject data){
        try{
            mTitle = data.getString("title");
            mContent = data.getString("content");
            mHasImage = data.getBoolean("hasImage");
            mHasUrl = data.getBoolean("hasUrl");
            mImageUrl = mHasImage ? data.getString("imageUrl") : "";
            mUrl = mHasUrl ? data.getString("url") : "";
        }
        catch(JSONException e) {
            System.out.println(e.getMessage());
        }

        return new Data();
    }

}
