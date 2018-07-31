package dhmm.com.listfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dhawalmajithia on 7/31/18.
 */

public class IETArrayAdapter extends ArrayAdapter<Data> {

    LayoutInflater mInflater;
    Context mContext;
    ArrayList<Data> mData;

    public IETArrayAdapter(Context context, int listItemResourceId, ArrayList<Data> data){
        super(context, listItemResourceId, data);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View view = convertView==null ? mInflater.from(mContext).inflate(R.layout.list_item, parent, false) : convertView;
        ((TextView) view.findViewById(R.id.title)).setText(mData.get(position).mTitle);
        ((TextView) view.findViewById(R.id.content)).setText(mData.get(position).mContent);

        return view;
    }
}
