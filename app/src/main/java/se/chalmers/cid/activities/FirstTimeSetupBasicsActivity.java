package se.chalmers.cid.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupBasicsActivity extends BaseActivity {

    private User mNewUser;
    private EditText mNameTextView;
    private EditText mAgeTextView;

    @Override
    protected void onUserDataLoaded() {
        setContentView(R.layout.activity_first_time_setup_basics);

        Intent intent = getIntent();
        mNewUser = (User) intent.getSerializableExtra("user");

        mNameTextView = (EditText) findViewById(R.id.basicsNameEditText);
        mAgeTextView = (EditText) findViewById(R.id.basicsAgeEditText);

        mNameTextView.setText(mFirebaseUser.getDisplayName());
    }

    @Override
    protected void onUserDataDoesNotExist() {
        onUserDataLoaded();
    }

    public void setGender(View v) {
        ImageView maleImage = (ImageView) findViewById(R.id.basicsGenderMaleButton);
        ImageView femaleImage = (ImageView) findViewById(R.id.basicsGenderFemaleButton);
        ImageView neutralImage = (ImageView) findViewById(R.id.basicsGenderNeutralButton);

        int fade = 128;
        int highlight = 255;

        maleImage.setImageAlpha(fade);
        femaleImage.setImageAlpha(fade);
        neutralImage.setImageAlpha(fade);

        maleImage.setTag("Male");
        femaleImage.setTag("Female");
        neutralImage.setTag("Neutral");

        ImageView selectedImage = (ImageView) v;

        selectedImage.setImageAlpha(highlight);

        mNewUser.setGender((String) selectedImage.getTag());
    }

    private void updateUser() {
        mNewUser.setName(mNameTextView.getText().toString());
        mNewUser.setAge(mAgeTextView.getText().toString());
    }

    public void nextActivity(View v) {
        updateUser();
        Intent intent = new Intent(this, FirstTimeSetupContactWaysActivity.class);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

    public void previousActivity(View v) {
        updateUser();
        Intent intent = new Intent(this, FirstTimeSetupRoleActivity.class);
        intent.putExtra("user", mNewUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }

}
