package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupBasicsActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_basics);
        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra("user");
    }

    private void updateUser(){
        EditText nameEditText = (EditText) findViewById(R.id.basicsNameEditText);
        String name = nameEditText.getText().toString();
        mUser.setName(name);
        mUser.setAge(((EditText) findViewById(R.id.basicsAgeEditText)).getText().toString());
        //mUser.setPrefContactWay(((EditText) findViewById(R.id.basicsContactEditText)).getText().toString());
    }

    public void nextActivity(View v){
        Intent intent = new Intent(this,FirstTimeSetupLanguagesActivity.class);
        updateUser();
        intent.putExtra("user",mUser);
        startActivity(intent);
        finish();
    }

    public void previousActivity(View v) {
        Intent intent = new Intent(this,FirstTimeSetupRoleActivity.class);
        updateUser();
        intent.putExtra("user",mUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
