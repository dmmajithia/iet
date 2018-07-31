package dhmm.com.listfragment;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhawalmajithia on 7/29/18.
 */

public class Data implements Parcelable{

    String mTitle, mContent, mUrl, mImageUrl;
    Boolean mHasImage, mHasUrl;

    public Data(){

    }

    public Data (JSONObject data){
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

        //return new Data();
    }

    public Data (Parcel in){
        String[] data = new String[3];
        in.readStringArray(data);
        this.mTitle = data[0];
        this.mContent = data[1];
        this.mUrl = data[2];
        this.mImageUrl = data[3];
        this.mHasUrl = data[4].equals("true");
        this.mHasImage = data[5].equals("true");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                this.mTitle,
                this.mContent,
                this.mUrl,
                this.mImageUrl,
                this.mHasUrl?"true":"false",
                this.mHasImage?"true":"false"
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
