package se.chalmers.cid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.adapter.LanguageAdapter;
import se.chalmers.cid.models.User;

public class FirstTimeSetupInterestsActivity extends AppCompatActivity {
    private User user;
    private List<Integer> userInterests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_interests);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        GridView interestGrid = (GridView) findViewById(R.id.interestList);
        interestGrid.setAdapter(new InterestAdapter(this, user));
        interestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;
                if (userInterests.contains(position)) {
                    userInterests.remove((Integer) position);
                    img.setImageAlpha(70);
                } else {
                    userInterests.add(position);
                    img.setImageAlpha(255);
                }
                //view = img;

                //Toast.makeText(MainActivity.this, "Kebab" + img.getImageAlpha(), Toast.LENGTH_SHORT).show();
            }
        });

        setDynamicHeight(interestGrid);
    }

    public void nextActivity(View v) {
        user.setInterests(userInterests);
        if (user.getRole() == 1) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user);
            saveUser(user);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MentorListActivity.class);
            intent.putExtra("user",user);
            saveUser(user);
            startActivity(intent);
        }
    }

    private void saveUser(User user){
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

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = 200;//listItem.getMeasuredHeight();


        float x = 1;
        if( items > 5 ){
            x = items/5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }
}
