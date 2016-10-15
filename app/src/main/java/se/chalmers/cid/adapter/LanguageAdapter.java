package se.chalmers.cid.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import se.chalmers.cid.models.Attribute;
import se.chalmers.cid.models.Constants;
import se.chalmers.cid.models.User;

public class LanguageAdapter extends AttributeAdapter {

    public LanguageAdapter(Context context, User user) {
        super(context, user);
    }

    @Override
    public ArrayMap<String, Attribute> attributesForUser(User user) {
        return Constants.LANGUAGES;
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
        img.setImageResource(mAttributes.valueAt(position).getImage());
        img.setImageAlpha(70);
        return img;
    }

}
