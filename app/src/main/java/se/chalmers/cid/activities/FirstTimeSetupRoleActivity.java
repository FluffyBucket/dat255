package se.chalmers.cid.activities;

import android.content.Intent;
import android.view.View;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupRoleActivity extends BaseActivity {

    private User mNewUser;

    @Override
    protected void onUserDataLoaded() {
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
