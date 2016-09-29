package se.chalmers.cid.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FirstTimeSetupRoleActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        user = new User();
        setContentView(R.layout.activity_first_time_setup_role);


    }

    public void nextActivityRefugee(View v){
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        user.setRole(2);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void nextActivityMentor(View v){
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        user.setRole(1);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
