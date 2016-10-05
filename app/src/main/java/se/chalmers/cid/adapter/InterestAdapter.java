package se.chalmers.cid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import se.chalmers.cid.models.Interest;
import se.chalmers.cid.constants.InterestsData;

/**
 * Created by valentin & mårlind on 22/09/2016.
 */

public class InterestAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<Interest> interests = InterestsData.getData();

    public InterestAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return interests.size();
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
        img.setImageResource(interests.get(position).getImage());
        return img;
    }
}
