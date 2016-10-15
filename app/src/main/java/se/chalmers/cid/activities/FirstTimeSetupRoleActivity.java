package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupRoleActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
        mUser.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        setContentView(R.layout.activity_first_time_setup_role);
    }

    public void nextActivityRefugee(View v){
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        mUser.setMentor(false);
        intent.putExtra("user", mUser);
        startActivity(intent);
        finish();
    }

    public void nextActivityMentor(View v){
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        mUser.setMentor(true);
        intent.putExtra("user", mUser);
        startActivity(intent);
        finish();
    }


}
