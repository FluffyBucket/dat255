package se.chalmers.cid.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.databinding.ActivityProfileBinding;
import se.chalmers.cid.models.User;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onUserDataLoaded() {
        setContentView(R.layout.activity_profile);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        binding.setUser(user);

        if (!mUser.getId().equals(user.getId())) {
            findViewById(R.id.profileName).setFocusable(false);
            findViewById(R.id.prefContactWayText).setFocusable(false);
            findViewById(R.id.biographyText).setFocusable(false);
            findViewById(R.id.profileAge).setFocusable(false);
        }

        fixGenderIcon(user);

        GridView interestGrid = (GridView) findViewById(R.id.interestList);
        interestGrid.setAdapter(new InterestAdapter(this, user));
        interestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;

                if (img.getImageAlpha() == 70) {
                    img.setImageAlpha(255);
                } else {
                    img.setImageAlpha(70);
                }
            }
        });
        setDynamicHeight(interestGrid);
    }

    private void fixGenderIcon(User user) {
        switch (user.getGender()){
            case "male":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_male);
                break;
            case "female":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_female);
                break;
            case "neutral":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_neutral);
                break;
            case "apache":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_apache);
                break;
            default:
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_questionmark);
                break;
        }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            finish();
                        }
                    });
        }
        if (item.getItemId() == R.id.action_mentorlist) {

            Intent intent = new Intent(this, MentorListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
