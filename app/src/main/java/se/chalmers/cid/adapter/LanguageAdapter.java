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
    private Integer[] languages = {
        R.drawable.ic_language_swedish,
        R.drawable.ic_language_english,
        R.drawable.ic_language_spanish,
        R.drawable.ic_language_german,
        R.drawable.ic_language_afghanistan,
        R.drawable.ic_language_france,
        R.drawable.ic_language_syria,
        R.drawable.ic_language_iran
    };

    public LanguageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return languages.length;
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
        img.setImageResource(languages[position]);
        img.setImageAlpha(70);
        return img;
    }
}
