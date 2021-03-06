package se.chalmers.cid.adapter;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import se.chalmers.cid.activities.FirstTimeSetupInterestsActivity;
import se.chalmers.cid.activities.ProfileActivity;
import se.chalmers.cid.models.Attribute;
import se.chalmers.cid.models.Attributes;
import se.chalmers.cid.models.User;

public class InterestAdapter extends AttributeAdapter {

    public InterestAdapter(Context context, User user) {
        super(context, attributesForUser(user, context));
    }

    private static ArrayMap<String, Attribute> attributesForUser(User user, Context context) {
        ArrayMap<String, Attribute> interests = new ArrayMap<>();
        if (context.getClass() == FirstTimeSetupInterestsActivity.class) {
            interests = Attributes.INTERESTS;
        } else {
            for (String interestName : user.getInterests().keySet()) {
                interests.put(interestName, Attributes.INTERESTS.get(interestName));
            }
        }
        return interests;
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

        if(mContext.getClass() != ProfileActivity.class) {
            img.setImageAlpha(70);
        }

        return img;
    }

}
