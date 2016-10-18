package se.chalmers.cid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onUserDataLoaded() {
        saveUser();
        if (mUser.isMentor()) {
            // Show profile activity
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("user", mUser);
            startActivity(intent);
            finish();
        } else {
            // Show list activity
            Intent intent = new Intent(MainActivity.this, MainNavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onUserDataDoesNotExist() {
        startActivity(new Intent(this, FirstTimeSetupRoleActivity.class));
        finish();
    }

    @Override
    protected void onUserSignedOut() {
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setProviders(
                        AuthUI.FACEBOOK_PROVIDER,
                        AuthUI.GOOGLE_PROVIDER)
                .build();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN && resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mUser);
        editor.putString("user",json);
        editor.commit();
    }

}
