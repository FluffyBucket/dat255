package se.chalmers.cid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import se.chalmers.cid.R;

/**
 * Created by valentin & mårlind on 22/09/2016.
 */

public class LanguageAdapter extends BaseAdapter
{
    private Context mContext;
    private Integer[] interests = {
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag,
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag,
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag,
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag,
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag,
            R.drawable.ic_apache,
            R.drawable.ic_phone,
            R.drawable.ic_default_flag
    };

    public LanguageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return interests.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if(convertView==null){
            img = new ImageView(mContext);
            img.setLayoutParams(new GridView.LayoutParams(192,192));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8,8,8,8);
        } else {
            img = (ImageView) convertView;
        }
        img.setImageResource(interests[position]);
        return img;
    }
}
