package se.chalmers.cid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;

public class FirstTimeSetupBasicsActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_basics);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

    }

    public void nextActivity(View v){
        Intent intent = new Intent(this,FirstTimeSetupLanguagesActivity.class);
        EditText nameEditText = (EditText) findViewById(R.id.basicsNameEditText);
        String name = nameEditText.getText().toString();
        user.setName(name);
        user.setAge(((EditText) findViewById(R.id.basicsAgeEditText)).getText().toString());
        user.setPrefContactWay(((EditText) findViewById(R.id.basicsContactEditText)).getText().toString());
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

}
