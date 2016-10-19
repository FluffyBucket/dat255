package se.chalmers.cid.activities;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.HashMap;
import java.util.HashSet;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.models.User;

public class FirstTimeSetupInterestsActivity extends BaseActivity {

    private User mNewUser;
    private HashSet<String> mInterestNames = new HashSet<>();
    private InterestAdapter mAdapter;

    @Override
    protected void onUserDataLoaded() {
    }

    @Override
    protected void onUserDataDoesNotExist() {
        setContentView(R.layout.activity_first_time_setup_interests);

        Intent intent = getIntent();
        mNewUser = (User) intent.getSerializableExtra("user");

        mAdapter = new InterestAdapter(this, mNewUser);

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

        if (items > 5) {
            float x = items / 5;
            int rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public void nextActivity(View v) {
        HashMap<String, Boolean> interests = new HashMap<>();
        for (String interestName : mInterestNames) {
            interests.put(interestName, true);
        }
        mNewUser.setInterests(interests);

        mDatabaseReference.child(mFirebaseUser.getUid()).setValue(mNewUser);

        if (mNewUser.isMentor()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", mNewUser);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainNavigationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            finish();
        }
    }

    public void previousActivity(View v) {
        Intent intent = new Intent(FirstTimeSetupInterestsActivity.this, FirstTimeSetupLanguagesActivity.class);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
    }

}
