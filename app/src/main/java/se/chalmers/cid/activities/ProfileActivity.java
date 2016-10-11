package se.chalmers.cid.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.databinding.ActivityProfileBinding;
import se.chalmers.cid.models.User;

public class ProfileActivity extends AppCompatActivity{
    private User user;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        binding.setUser(user);

        mAuth = FirebaseAuth.getInstance();


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
                //view = img;

                //Toast.makeText(MainActivity.this, "Kebab" + img.getImageAlpha(), Toast.LENGTH_SHORT).show();
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

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = 200;//listItem.getMeasuredHeight();


        float x = 1;
        if (items > 5) {
            x = items / 5;
            rows = (int) (x + 1);
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

        return super.onOptionsItemSelected(item);
    }
}
