package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupRoleActivity extends BaseActivity {

    private User mNewUser;


    @Override
    protected  void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_first_time_setup_role);
    }

    @Override
    protected void onUserDataLoaded() {
        setContentView(R.layout.activity_first_time_setup_role);
        Intent intent = getIntent();
        mNewUser = (User) intent.getSerializableExtra("user");
    }

    @Override
    protected void onUserDataDoesNotExist() {
        setContentView(R.layout.activity_first_time_setup_role);
        mNewUser = new User();
        mNewUser.setId(mFirebaseUser.getUid());
    }

    public void nextActivityRefugee(View v) {
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        mNewUser.setMentor(false);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

    public void nextActivityMentor(View v) {
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        mNewUser.setMentor(true);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

}
