package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.HashSet;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.models.User;

public class FirstTimeSetupInterestsActivity extends AppCompatActivity {

    private User mUser;
    private HashSet<String> mInterestNames = new HashSet<>();
    private InterestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_interests);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra("user");

        mAdapter = new InterestAdapter(this, mUser);

        GridView interestGrid = (GridView) findViewById(R.id.interestList);
        interestGrid.setAdapter(mAdapter);
        interestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;
                String interestName = mAdapter.getItem(position);
                if (mInterestNames.contains(interestName)) {
                    mInterestNames.remove(interestName);
                    img.setImageAlpha(70);
                } else {
                    mInterestNames.add(interestName);
                    img.setImageAlpha(255);
                }
            }
        });

        setDynamicHeight(interestGrid);
    }

    public void nextActivity(View v) {
        HashMap<String, Boolean> interests = new HashMap<>();
        for (String interestName : mInterestNames) {
            interests.put(interestName, true);
        }
        mUser.setInterests(interests);

        saveUser(mUser);

        if (mUser.isMentor()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", mUser);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MentorListActivity.class);
            intent.putExtra("user", mUser);
            startActivity(intent);
            finish();
        }
    }

    private void saveUser(User user) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        usersRef.child(firebaseUser.getUid()).setValue(user);
    }

    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        int totalHeight = 200; //listItem.getMeasuredHeight();

        int items = gridViewAdapter.getCount();

        if( items > 5 ){
            float x = items / 5;
            int rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

}
