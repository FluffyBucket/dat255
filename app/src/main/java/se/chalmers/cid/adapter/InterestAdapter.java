package se.chalmers.cid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import se.chalmers.cid.R;
import se.chalmers.cid.activities.FirstTimeSetupInterestsActivity;
import se.chalmers.cid.activities.FirstTimeSetupRoleActivity;
import se.chalmers.cid.activities.MainActivity;
import se.chalmers.cid.activities.ProfileActivity;
import se.chalmers.cid.models.Interest;
import se.chalmers.cid.constants.interestsData;
import se.chalmers.cid.models.User;

/**
 * Created by valentin & mårlind on 22/09/2016.
 */

public class InterestAdapter extends BaseAdapter
{
    private Context mContext;
    private User profileUser;
    private User localUser;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ArrayMap<Integer,Interest> interests;

    public InterestAdapter(Context c,User user){
        mContext = c;
        this.profileUser = user;

        interests = getInterests();
    }

    private ArrayMap<Integer,Interest> getInterests(){

        ArrayMap<Integer,Interest> list = new ArrayMap<>();
        /*
        //if(!localUser.getName().equals(profileUser.getName())){
            for (Integer i:localUser.getInterests()
                 ) {
                if(profileUser.getInterests().contains(i))
                {
                    list.put(i,interestsData.getData().get(i));
                }
            }
        //}
        */

        if (mContext.getClass() == FirstTimeSetupInterestsActivity.class)
        {
            list = interestsData.getData();
        }

        return list;
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
        img.setImageAlpha(70);
        return img;
    }
}
