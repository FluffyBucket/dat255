package se.chalmers.cid.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupContactWaysActivity extends BaseActivity {

    private User mNewUser;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mFacebookEditText;

    @Override
    protected void onUserDataLoaded() {
    }

    @Override
    protected void onUserDataDoesNotExist() {
        setContentView(R.layout.activity_first_time_setup_contact_ways);

        Intent intent = getIntent();
        mNewUser = (User) intent.getSerializableExtra("user");

        mPhoneEditText = (EditText) findViewById(R.id.basicsPhoneEditText);
        mEmailEditText = (EditText) findViewById(R.id.basicsEmailEditText);
        mFacebookEditText = (EditText) findViewById(R.id.basicsFacebookEditText);

        mEmailEditText.setText(mFirebaseUser.getEmail());
    }

    private void updateUser() {
        HashMap<String, String> contactWays = new HashMap<>();

        String phoneNumber = mPhoneEditText.getText().toString();
        if (!phoneNumber.equals("")) {
            contactWays.put("Phone", phoneNumber);
        }

        String emailAddress = mEmailEditText.getText().toString();
        if (!emailAddress.equals("")) {
            contactWays.put("Email", emailAddress);
        }

        String facebookUsername = mFacebookEditText.getText().toString();
        if (!facebookUsername.equals("")) {
            contactWays.put("Facebook", facebookUsername);
        }

        mNewUser.setContactWays(contactWays);
    }

    public void nextActivity(View v) {
        updateUser();
        Intent intent = new Intent(this, FirstTimeSetupLanguagesActivity.class);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

    public void previousActivity(View v) {
        updateUser();
        Intent intent = new Intent(this, FirstTimeSetupBasicsActivity.class);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

}
