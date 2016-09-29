package se.chalmers.cid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/** Called when the user clicks the button
	 * Only implemented to demonstrate the mentorlist view
	 *
	 * */
	public void changeViewToMentors(View view) {
		Intent intent = new Intent(this, MentorListActivity.class);
		startActivity(intent);
	}

}
